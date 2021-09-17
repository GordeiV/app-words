package web;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "start_site", urlPatterns = {"/start"})
public class StartSiteServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(StartSiteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("here");
        String login = request.getParameter("login");
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("login", user.getLogin());
//        response.getWriter().println("<html><h1><a href=user></a>" + user.getLogin() + "</h1></html>");
        request.getRequestDispatcher("/WEB-INF/start.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("here");
        doGet(request, response);
    }

}
