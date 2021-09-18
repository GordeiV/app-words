package other;

import dao.DaoException;
import dao.VocabularyDao;
import entity.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.PoolConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "WordAppServlet", urlPatterns = {"/safeVocabulary"})
public class WordAppServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(WordAppServlet.class);
    private VocabularyDao vocabularyDao;

    @Override
    public void init() throws ServletException {
        logger.info("Servlet is created");
        vocabularyDao = new VocabularyDao();
        PoolConnectionManager poolConnectionManager = new PoolConnectionManager();
        vocabularyDao.setConnectionManager(poolConnectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String word = req.getParameter("word_1");
        PrintWriter writer = resp.getWriter();
        System.out.println(word);
        try {
            List<Vocabulary> vocabularies = vocabularyDao.findVocabulary(word);
            if(!vocabularies.isEmpty()) {
                for(Vocabulary vocabulary : vocabularies) {
                    writer.println(vocabulary.getName());
                }
            } else {
                writer.println("Not found");
            }
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
    }
}
