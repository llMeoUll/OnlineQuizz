package util;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import com.lambdaworks.crypto.SCryptUtil;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Email {
    static Dotenv dotenv = Dotenv.configure().load();
    static String from = dotenv.get("EMAIL_ADDRESS");
    static String password = dotenv.get("EMAIL_PASSWORD");
    public static boolean sendEmail(String to, String tieuDe, String noiDung) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject(tieuDe);

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung
            msg.setContent(noiDung, "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
//            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    public void sendVerifyCode (HttpServletRequest request, String email, String subject, String content, String type) {
        HttpSession session = request.getSession();
        // Tạo mã xác nhận
        Random random = new Random();
        String code = String.valueOf(random.nextInt(900000) + 100000);
        // Hash mã xác nhận
        String hashCode = SCryptUtil.scrypt(code, 16, 16, 16);
        session.setAttribute("code", hashCode);
        // Tạo URL xác nhận
        Dotenv dotenv = Dotenv.configure().load();
        String rootUrl = dotenv.get("ROOT_URL");
        String url = rootUrl + "verify-code?verify-type="+type+"&email=" + email + "&code=" + hashCode;
        MailTemplate mailTemplate = new MailTemplate();
        String mailContent = mailTemplate.generateEmail(subject, content, url, code);
        // Gửi email xác nhận
        Email verifyEmail = new Email();
        verifyEmail.sendEmail(email, subject, mailContent);
    }

}