package controller.user.authenticate;

import com.lambdaworks.crypto.SCryptUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class VerifyEmail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String email = request.getParameter("email");
        if (code != null && email != null) {
            checkCode(request, response, code);
        } else {
            request.getRequestDispatcher("./view/user/authenticate/Verify.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            checkCode(request, response, code);

        } else {
            request.setAttribute("error", "Mã xác nhận không đúng");
            request.getRequestDispatcher("./view/user/authenticate/Verify.jsp").forward(request, response);
        }
    }

    private void checkCode(HttpServletRequest request, HttpServletResponse response, String code) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("code");
        if (sessionCode.equals(code) || SCryptUtil.check(code, sessionCode)) {
            response.sendRedirect("./");
        } else {
            request.setAttribute("error", "Mã xác nhận không đúng");
            request.getRequestDispatcher("./view/user/authenticate/Verify.jsp").forward(request, response);
        }
    }
}
