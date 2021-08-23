package dao;

import entity.User;
import entity.Vocabulary;
import entity.VocabularyStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ConnectionManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class VocabularyDaoTest {
    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }


    @Test
    public void saveVocabulary1() throws SQLException {
        Vocabulary vocabulary = new Vocabulary("noway");
        User user = new User("testUser1", "testPassword", 1L);
        Long id1 = new UserDao().saveUser(user);
        Long id2 = new VocabularyDao().saveVocabulary(vocabulary, user);
    }

    @Test
    public void saveVocabulary2() throws SQLException {
        Vocabulary vocabulary = new Vocabulary("number2");
        User user = new User("testUser2", "testPassword", 1L);
        Long id1 = new UserDao().saveUser(user);
        Long id2 = new VocabularyDao().saveVocabulary(vocabulary, user);
    }

    @Test
    public void saveVocabulary3() throws SQLException {
        Vocabulary vocabulary = new Vocabulary("key");
        User user = new User("testUser3", "testPassword", 1L);
        Long id1 = new UserDao().saveUser(user);
        Long id2 = new VocabularyDao().saveVocabulary(vocabulary, user);
    }

    @Test
    public void findVocabulary1() {
        List<Vocabulary> list = new VocabularyDao().findVocabulary("ay");
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void findVocabulary2() {
        List<Vocabulary> list = new VocabularyDao().findVocabulary("e");
        Assert.assertTrue(list.size() == 11);
    }

    @Test
    public void findVocabulary3() {
        List<Vocabulary> list = new VocabularyDao().findVocabulary("number");
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void deleteVocabulary1() {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.deleteVocabulary(5L);
        Assert.assertTrue(vocabularyDao.findVocabulary("ability").size() == 0);
    }

    @Test
    public void deleteVocabulary2() {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.deleteVocabulary(10L);
        Assert.assertTrue(vocabularyDao.findVocabulary("money").size() == 0);
    }

    @Test
    public void deleteVocabulary3() {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.deleteVocabulary(12L);
        Assert.assertTrue(vocabularyDao.findVocabulary("water").size() == 0);
    }

    @Test
    public void updateVocabulary() {
        Vocabulary vocabulary = new Vocabulary(
                "forTest",
                LocalDateTime.of(2002, 3, 3, 20, 0, 0),
                LocalDateTime.now(),
                VocabularyStatus.FIFTH_REPEAT,
                16L);
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.updateVocabulary(vocabulary);
        Assert.assertTrue(vocabularyDao.findVocabulary("question").size() == 0);
        Assert.assertTrue(vocabularyDao.findVocabulary("forTest").size() == 1);
    }



}