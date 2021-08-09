package dao;


import config.Config;
import content.User;
import content.Vocabulary;
import content.VocabularyStatus;
import content.Word;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class VocabularyDao {
    public static final String FIND_VOCABULARY = "SELECT * FROM vocabulary WHERE v_name REGEXP ?";
    public static final String GET_WORDS_FROM_VOCABULARY = "SELECT * FROM words WHERE id_vocabulary = ?";
    public static final String INSERT_VOCABULARY = "INSERT INTO vocabulary(v_name, v_date, id_user, v_status) VALUES (?, ?, ?, ?);";

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
                LocalDateTime date = rsWithVocabularies.getTimestamp("v_date").toLocalDateTime();
                VocabularyStatus status = VocabularyStatus.values()[rsWithVocabularies.getInt("v_status")];

                Vocabulary vocabulary = new Vocabulary(name, date, status);

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

    public Long saveVocabulary(Vocabulary vocabulary, User user) {
        Long result = -1L;

        try (Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_VOCABULARY, new String[]{"id_vocabulary"}))
        {
            stmt.setString(1, vocabulary.getName());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3, user.getId());
            stmt.setInt(4, VocabularyStatus.DO_NOTHING.ordinal());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()) {
                result = generatedKeys.getLong(1);
            }
            generatedKeys.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
