package main;

import dao.DaoException;
import entity.Vocabulary;
import entity.Word;
import dao.VocabularyDao;
import util.DirectConnectionManager;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, DaoException {
        VocabularyDao vocabularyDao = new VocabularyDao();
        vocabularyDao.setConnectionManager(new DirectConnectionManager());
        checkFindVocabulary(vocabularyDao);
    }

    public static List<Vocabulary>  checkFindVocabulary(VocabularyDao directoryDao) throws DaoException {
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
