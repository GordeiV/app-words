package web;

import business.UserService;
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

@WebServlet(name = "user-register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        req.removeAttribute("error");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserService();
        String userName = req.getParameter("user");
        String password = req.getParameter("psw");
        User user = new User(userName, password);

        String repeatedPassword = req.getParameter("psw-repeat");
        if(!password.equals(repeatedPassword)) {
            req.setAttribute("error", "The passwords are not equal");
            doGet(req, resp);
            return;
        }

        if(service.isUserExist(user.getLogin())) {
            req.setAttribute("error", "Username already exists");
            doGet(req, resp);
            return;
        }
        Long id = 0L;
        try {
            id = service.createUser(user);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
        user.setId(id);
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/start");
    }
}
