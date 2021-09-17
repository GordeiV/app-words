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
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(StartSiteServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("here");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            User user = userService.logInUser(new User(login, password));
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/start");
        } catch (NoUserFound noUserFound) {
            request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
    }
}
