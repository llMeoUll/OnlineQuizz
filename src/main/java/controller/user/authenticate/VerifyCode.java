package controller.user.authenticate;

import com.lambdaworks.crypto.SCryptUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class VerifyCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String email = request.getParameter("email");
        String verifyType = request.getParameter("verify-type");
        if (code != null && email != null && verifyType != null) {
            checkCode(request, response, code, verifyType);
        } else {
            request.getRequestDispatcher("./view/user/authenticate/Verify.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            String verifyType = request.getParameter("verify-type");
            checkCode(request, response, code, verifyType);

        } else {
            request.setAttribute("error", "Mã xác nhận không đúng");
            request.getRequestDispatcher("./view/user/authenticate/Verify.jsp").forward(request, response);
        }
    }

    private void checkCode(HttpServletRequest request, HttpServletResponse response, String code, String verifyType) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("code");
        if(sessionCode != null) {
            if (sessionCode.equals(code) || SCryptUtil.check(code, sessionCode)) {
                if(verifyType.equals("verify-email")){
                    response.sendRedirect("./");
                } else if(verifyType.equals("reset-password")){
                    response.sendRedirect("./reset-password");
                }
            } else {
                request.setAttribute("error", "Mã xác nhận không đúng");
                request.getRequestDispatcher("./view/user/authenticate/Verify.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "You have not requested a verification code");
            request.getRequestDispatcher("./view/user/authenticate/Verify.jsp").forward(request, response);
        }

    }
}
