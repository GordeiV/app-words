package dao;

import content.Word;
import util.ConnectionManager;

import java.sql.*;

public class WordDao {
    public static final String SAVE_WORD = "INSERT INTO words(foreign_word, native_word, transcription, id_vocabulary) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_WORD = "UPDATE words SET foreign_word = ?, native_word = ?, transcription = ? WHERE id_word = ?";
    public static final String DELETE_WORD = "DELETE FROM words WHERE id_word = ?;";

    public Long saveWord(Word word, Long vocabularyId) {
        Long id = -1L;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(SAVE_WORD, new String[]{"id_word"}))
        {
            stmt.setString(1, word.getForeignWord());
            stmt.setString(2, word.getNativeWord());
            stmt.setString(3, word.getTranscription());
            stmt.setLong(4, vocabularyId);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return id;
    }

    /**
     * id - id of word which must be updated
     * newWord - word, which must be inserted instead old one
     * */
    public boolean updateWord(Long id, Word newWord) {
        boolean change = false;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_WORD))
        {
            stmt.setString(1, newWord.getForeignWord());
            stmt.setString(2, newWord.getNativeWord());
            stmt.setString(3, newWord.getTranscription());
            stmt.setLong(4, id);
            int i = stmt.executeUpdate();
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return change;
    }

    public boolean deleteWord(Long id) {
        boolean change = false;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_WORD))
        {
            stmt.setLong(1, id);
            int i = stmt.executeUpdate();
            if(i > 0) {
                change = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return change;
    }
}
