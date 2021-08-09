package content;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vocabulary {
    private ArrayList<Word> words = new ArrayList<>();
    private Date date;
    private String name;

    public Vocabulary(String name, Date date) {
        this.date = date;
        this.name = name;
    }

    public Vocabulary(Date date) {
        this.date = date;
    }

    public Vocabulary() {
    }

    public void addWord(String foreignWord, String nativeWord) {
        words.add(new Word(foreignWord, nativeWord));
    }

    public void addWord(String foreignWord, String nativeWord, String transcription) {
        words.add(new Word(foreignWord, nativeWord, transcription));
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public List<Word> findWord(String pattern) {
        List<Word> resultWords = new ArrayList<>();

        for (Word word : words) {
            if(word.getForeignWord().matches(".*?" + pattern + ".*?")) {
                resultWords.add(word);
            }
        }

        return resultWords;
    }

    //    public Word getWordByForeignWord(String foreignWord) {
//        words.get()
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
