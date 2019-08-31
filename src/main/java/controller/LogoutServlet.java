package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logoutServlet",urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private static final String COOKIE_LOGIN = "sparrow_login_Cookie";
    private static final String COOKIE_PASSWORD = "sparrow_pass_Cookie";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        for (Cookie cookie : req.getCookies()) {
            cookie.setMaxAge(0); // = Kill Cookie
            resp.addCookie(cookie);

        }

        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
}
