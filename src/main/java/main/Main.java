package main;

import content.User;
import content.Vocabulary;
import content.VocabularyStatus;
import content.Word;
import dao.VocabularyDao;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        VocabularyDao vocabularyDao = new VocabularyDao();
//        List<Vocabulary> vocabularies = checkFindVocabulary(vocabularyDao);
//        System.out.println("-----------------");
//        List<Word> words = checkFindWord(vocabularies);

        Vocabulary vocabulary = new Vocabulary("testVocabulary", VocabularyStatus.DO_NOTHING);
        User user = new User(2L, "testUser", "testPassword");
        Long id = vocabularyDao.saveVocabulary(vocabulary, user);
        System.out.println(id);
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
        List<Word> list=  vocabularies.get(0).findWord("d");
        for(Word word : list) {
            System.out.println(word.getForeignWord() + " : " + word.getNativeWord());
        }
        return list;
    }
}
