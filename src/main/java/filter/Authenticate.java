package filter;

import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Authenticate implements Filter {

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
       return user != null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;

        // Kiểm tra URL của request
        String requestURI = request.getRequestURI();
        String requestContextPath = request.getContextPath();
        System.out.println(requestURI);
        if (isAuthenticated(request)) {
            // Nếu có quyền truy cập, cho phép tiếp tục xử lý request.
            fc.doFilter(sr, sr1);
        } else {
            response.sendRedirect(requestContextPath + "/login");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}