package web;

import dao.DaoException;
import dao.VocabularyDao;
import entity.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.PoolConnectionManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/find")
@Singleton
public class FindVocabularyService {
    private static final Logger logger = LoggerFactory.getLogger(FindVocabularyService.class);
    private VocabularyDao vocabularyDao;

    @PostConstruct
    public void init() {
        logger.info("Servlet is created");
        vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new PoolConnectionManager());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Vocabulary findVocabulary(Vocabulary vocabulary) throws DaoException {
        logger.info(vocabulary.toString());
        List<Vocabulary> resultVocabulary = vocabularyDao.findVocabulary(vocabulary.getName());
        return resultVocabulary.get(0);
    }
}

//    Vocabulary vocabulary = new Vocabulary(
//            "school",
//            LocalDateTime.now(),
//            LocalDateTime.now().plusDays(3),
//            VocabularyStatus.FIFTH_REPEAT,
//            88L);
//    ArrayList<Word> words = new ArrayList<>();
//        words.add(new Word("pen", "pen", "pen"));
//                words.add(new Word("desk", "desk", "desk"));
