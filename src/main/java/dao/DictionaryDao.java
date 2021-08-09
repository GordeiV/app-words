package dao;


import config.Config;
import content.Vocabulary;
import content.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DictionaryDao {
    public static final String FIND_VOCABULARY = "SELECT * FROM vocabulary WHERE v_name REGEXP ?";
    public static final String GET_WORDS_FROM_VOCABULARY = "SELECT * FROM words WHERE id_vocabulary = ?";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return connection;
    }

    public List<Vocabulary> findVocabulary(String pattern) {
        List<Vocabulary> vocabularies = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmtFindVocabulary = con.prepareStatement(FIND_VOCABULARY);
             PreparedStatement stmtFindWord = con.prepareStatement(GET_WORDS_FROM_VOCABULARY))
        {
            stmtFindVocabulary.setString(1, ".*?" + pattern + ".*?");
            ResultSet rsWithVocabularies = stmtFindVocabulary.executeQuery();

            while (rsWithVocabularies.next()) {
                String name = rsWithVocabularies.getString("v_name");
                Date date = rsWithVocabularies.getDate("v_date");
                Vocabulary vocabulary = new Vocabulary(name, date);

                Long id = rsWithVocabularies.getLong("id_vocabulary");
                stmtFindWord.setLong(1, id);
                ResultSet rsWithWords = stmtFindWord.executeQuery();

                while (rsWithWords.next()) {
                    String foreignWord = rsWithWords.getString("foreign_word");
                    String nativeWord = rsWithWords.getString("native_word");
                    String transcription = rsWithWords.getString("transcription");
                    vocabulary.addWord(foreignWord, nativeWord, transcription);
                }
                vocabularies.add(vocabulary);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vocabularies;
    }

}
