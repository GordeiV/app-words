package business;

import dao.DaoException;
import dao.VocabularyDao;
import entity.User;
import entity.Vocabulary;
import util.PoolConnectionManager;

import java.util.List;

public class VocabularyService {
    private VocabularyDao vocabularyDao;

    public VocabularyService() {
        vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new PoolConnectionManager());
    }

    public List<Vocabulary> getUsersVocabularies(User user) throws DaoException {
        return vocabularyDao.getAllVocabularies(user.getId());
    }
}
