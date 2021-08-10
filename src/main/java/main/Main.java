package main;

import content.User;
import content.Vocabulary;
import content.VocabularyStatus;
import content.Word;
import dao.UserDao;
import dao.VocabularyDao;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        UserDao userDao = new UserDao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Vocabulary> vocabularies = vocabularyDao.getVocabulariesForRepeat();
        for(Vocabulary vocabulary : vocabularies) {
            System.out.println(vocabulary.getName() + " : Date - " + vocabulary.getDate().format(formatter));
        }
        System.out.println("-----------------");
        List<Word> words = checkFindWord(vocabularies);

//        Vocabulary vocabulary = new Vocabulary("testVocabulary");
//        User user = new User("testUser", "testPassword", 1L);
//        Long id1 = userDao.saveUser(user);
//        System.out.println(id1);
//        Long id2 = vocabularyDao.saveVocabulary(vocabulary, user);
//        System.out.println(id2);
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
            System.out.println(word.getForeignWord() + " : " + word.getNativeWord());
        }
        return list;
    }
}
