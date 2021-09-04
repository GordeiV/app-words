package web;

import dao.DaoException;
import dao.VocabularyDao;
import dao.WordDao;
import entity.Vocabulary;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String word = req.getParameter("word_1");
        PrintWriter writer = resp.getWriter();
        System.out.println(word);
        try {
            VocabularyDao vocabularyDao = new VocabularyDao();
            List<Vocabulary> vocabularies = vocabularyDao.findVocabulary(word);
            if(!vocabularies.isEmpty()) {
                for(Vocabulary vocabulary : vocabularies) {
                    writer.println(vocabulary.getName());
                }
            } else {
                writer.println("Not found");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
