package util;

public class MailTemplate {
    public String generateEmail(String title, String content, String url, String code) {
        return "<head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html charset=UTF-8\" />\n" +
                "  <link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">\n" +
                "  <style>\n" +
                "    a,\n" +
                "    a:link,\n" +
                "    a:visited {\n" +
                "      text-decoration: none;\n" +
                "      color: #00788a;\n" +
                "    }\n" +
                "\n" +
                "    a:hover {\n" +
                "      text-decoration: underline;\n" +
                "    }\n" +
                "\n" +
                "    h2,\n" +
                "    h2 a,\n" +
                "    h2 a:visited,\n" +
                "    h3,\n" +
                "    h3 a,\n" +
                "    h3 a:visited,\n" +
                "    h4,\n" +
                "    h5,\n" +
                "    h6,\n" +
                "    .t_cht {\n" +
                "      color: #000 !important;\n" +
                "    }\n" +
                "\n" +
                "    .ExternalClass p,\n" +
                "    .ExternalClass span,\n" +
                "    .ExternalClass font,\n" +
                "    .ExternalClass td {\n" +
                "      line-height: 100%;\n" +
                "    }\n" +
                "\n" +
                "    .ExternalClass {\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "\t\n" +
                "\t.num {\n" +
                "  background-color: #1755F5;\n" +
                "    color: white;\n" +
                "    padding: 8px 24px;\n" +
                "    border-radius: 4px;\n" +
                "    font-size: 24px;\n" +
                "    letter-spacing: 10px;\n" +
                "    text-align: center;\n" +
                "    max-width: max-content;\n" +
                "    margin: 0 auto;\n" +
                "}\n" +
                "\n" +
                "p {\n" +
                "  font-size: 15px;\n" +
                "}\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-size: 1.25rem;font-family: 'Roboto', sans-serif;padding-left:20px;padding-right:20px;padding-top:20px;padding-bottom:20px; background-color: #FAFAFA; width: 75%; max-width: 1280px; min-width: 600px; margin-right: auto; margin-left: auto\">\n" +
                "  <table cellpadding=\"12\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#FAFAFA\" style=\"border-collapse: collapse;margin: auto\">\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <td style=\"padding-left: 0; padding-right: 0\">\n" +
                "          <img src=\"https://uploads-ssl.webflow.com/5e96c040bda7162df0a5646d/5f91d2a4d4d57838829dcef4_image-blue%20waves%402x.png\" style=\"width:80%; max-width:750px\" />\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td style=\"text-align:center; padding-bottom: 20px\">\n" +
                "          <img src=\"https://firebasestorage.googleapis.com/v0/b/online-quizz-system.appspot.com/o/static%2Flogo1250x1250.png?alt=media&token=381b146a-c98c-4044-b510-db5b345af2bd\" style=\"width: 15%;\" />\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td style=\"background-color: #fff\">\n" +
                "          <table width=\"100%\">\n" +
                "            <tr>\n" +
                "              <td style=\"text-align:center\">\n" +
                "                <h1 style=\"font-size: 30px; color: #202225; margin-top: 0;\">"+title+"</h1>\n" +
                "                <p style=\"font-size: 18px; margin-bottom: 30px; color: #202225; max-width: 60ch; margin-left: auto; margin-right: auto\">"+content+"</p>\n" +
                "                <a href=\""+url+"\" style=\"background-color: #1755F5; color: #fff; padding: 8px 24px; border-radius: 4px; border-style: solid; border-color: #1755F5; font-size: 14px; text-decoration: none; cursor: pointer\">Confirm and continue</a>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "\t<tr>\n" +
                "        <td style=\"background-color: #fff\">\n" +
                "          <h1 style=\"text-align: center;color: #202225;font-size: 30px;\">Or your verification code</h1>\n" +
                "\t\t<h1 class=\"num\">"+code+"</h1>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "    <tfoot>\n" +
                "      <tr>\n" +
                "        <td style=\"padding-bottom: 0\">\n" +
                "          <img src=\"https://uploads-ssl.webflow.com/5e96c040bda7162df0a5646d/5f91d2a48c6acf149ef407de_image-girl%402x.png\" style=\"width: 100%;\"/>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td style=\"text-align: center;color:#B6B6B6; font-size: 18px\">You received this email because you signed up for Quizzicle.<br> © 2024 Quizzicle, All rights reserved.</td>\n" +
                "      </tr>\n" +
                "    </tfoot>\n" +
                "  </table>\n" +
                "</body>";
    }
}
