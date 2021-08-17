package main;

import entity.User;
import entity.Vocabulary;
import entity.Word;
import dao.UserDao;
import dao.VocabularyDao;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        VocabularyDao vocabularyDao = new VocabularyDao();
        UserDao userDao = new UserDao();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        List<Vocabulary> vocabularies = vocabularyDao.getVocabulariesForRepeat();
//        for(Vocabulary vocabulary : vocabularies) {
//            System.out.println(vocabulary.getName() + " : Date - " + vocabulary.getDate().format(formatter));
//        }
//        System.out.println("-----------------");
//        List<Word> words = checkFindWord(vocabularies);

        User user = new User("test2", "test", 3L);
        System.out.println(userDao.updateUser(user));

//        Vocabulary vocabulary = new Vocabulary("testVocabulary");
//        User user = new User("testUser", "testPassword", 1L);
//        Long id1 = userDao.saveUser(user);
//        System.out.println(id1);
//        Long id2 = vocabularyDao.saveVocabulary(vocabulary, user);
//        System.out.println(id2);

//        Word word = new Word("test1", "test2", "test3");
//        WordDao wd = new WordDao();
//        System.out.println(wd.delete(5L));

//        Vocabulary vocabulary = new Vocabulary("kek");
//        VocabularyDao vd = new VocabularyDao();
//        System.out.println(vd.updateVocabulary(2L, vocabulary));

//        UserDao ud = new UserDao();
//        User user1 = new User("test021", "test021");
//        User user2 = new User("test202", "test022");
//        boolean b = ud.updateUser(2L, user1);
//        boolean sam = ud.updateUser("sam", user2);
//        System.out.println(b + " : " + sam);
    }

    public static List<Vocabulary>  checkFindVocabulary(VocabularyDao directoryDao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Vocabulary> vocabularies = directoryDao.findVocabulary("o");
        for(Vocabulary vocabulary : vocabularies) {
            System.out.println(vocabulary.getName() + " : Date - " + vocabulary.getDate().format(formatter));
        }
        return vocabularies;
    }

    public static List<Word> checkFindWord(List<Vocabulary> vocabularies) {
        List<Word> list=  vocabularies.get(0).findWord("");
        for(Word word : list) {
            System.out.println(word.getForeignWord() + " : " + word.getNativeWord() + " : " + word.getId());
        }
        return list;
    }
}
