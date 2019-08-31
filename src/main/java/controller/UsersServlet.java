package controller;


import dao.UserDAO;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "usersServlet", urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {

    private UserDAO userDao = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Set<String> followedUsers = userDao.getFollowingUsers((String) request.getSession().getAttribute("username"));
        //Set<String> followedUsers = userDao.getFollowingUsers2String((String) request.getSession().getAttribute("username"));


        //Set<String> notFollowedUsers = userDao.getNotFollowedUsers((String) request.getSession().getAttribute("username"));
        //request.setAttribute("followedUsers", followedUsers);
        //request.setAttribute("notFollowedUsers", notFollowedUsers);
        //userDao.deleteUser(1L);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
