package web;

import business.UserService;
import business.exceptions.NoUserFound;
import dao.DaoException;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Log in", urlPatterns = {"/login"})
public class SignUpServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(StartSite.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("here");
        request.getRequestDispatcher("/html/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        try {
            User user = userService.logInUser(new User(login, password));
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/start?login=" + user.getLogin());
        } catch (NoUserFound noUserFound) {
            request.getRequestDispatcher("/html/false-login.html").forward(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
