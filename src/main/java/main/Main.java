package main;

import content.Vocabulary;
import content.Word;
import dao.VocabularyDao;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        VocabularyDao directoryDao = new VocabularyDao();
        List<Vocabulary> vocabularies = directoryDao.findVocabulary("o");
        for(Vocabulary vocabulary : vocabularies) {
            System.out.println(vocabulary.getName() + " : Date - " + vocabulary.getDate());
        }

        System.out.println("-----------------");

        List<Word> list=  vocabularies.get(0).findWord("d");
        for(Word word : list) {
            System.out.println(word.getForeignWord() + " : " + word.getNativeWord());
        }




    }
}
