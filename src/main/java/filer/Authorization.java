package filer;

import entity.Feature;
import entity.Role;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter implements Filter {

    private boolean hasAccess(ServletRequest request) {
        HttpServletRequest res = (HttpServletRequest) request;
        HttpSession session = res.getSession();
        User loggedUser = (User) session.getAttribute("user");
        String url = res.getServletPath();

        ArrayList<Role> roles = loggedUser.getRoles();
        if(roles == null) return false;
        for (Role role : roles) {
            ArrayList<Feature> features = role.getFeatures();
            for (Feature feature : features) {
                if(feature.getUrl().equals(url)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        // Kiểm tra quyền truy cập của người dùng dựa trên vai trò và tính năng
        if (hasAccess(sr)) {
            // Nếu có quyền truy cập, cho phép tiếp tục xử lý request.
            fc.doFilter(sr, sr1);
        } else {
            sr1.getWriter().println("Access denied");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

