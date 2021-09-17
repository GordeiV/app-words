package web;

import business.VocabularyService;
import dao.DaoException;
import entity.User;
import entity.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "vocabularies", urlPatterns = {"/vocabularies"})
public class VocabulariesServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(VocabulariesServlet.class);
    private List<Vocabulary> vocabularies;
    private VocabularyService vocabularyService;

    @Override
    public void init() throws ServletException {
        vocabularyService = new VocabularyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            vocabularies = vocabularyService.getUsersVocabularies(user);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
        req.setAttribute("login", user.getLogin());
        req.setAttribute("vocabularies", vocabularies);
        req.getRequestDispatcher("/WEB-INF/show-vocabularies.jsp").forward(req, resp);
    }
}
