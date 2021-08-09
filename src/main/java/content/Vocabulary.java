package content;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vocabulary {
    private ArrayList<Word> words = new ArrayList<>();
    private String name;
    private LocalDateTime date;
    private VocabularyStatus vocabularyStatus;

    public Vocabulary(String name, LocalDateTime date, VocabularyStatus vocabularyStatus) {
        this.name = name;
        this.date = date;
        this.vocabularyStatus = vocabularyStatus;
    }

    public Vocabulary(String name, VocabularyStatus vocabularyStatus) {
        this.name = name;
        this.vocabularyStatus = vocabularyStatus;
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


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VocabularyStatus getVocabularyStatus() {
        return vocabularyStatus;
    }

    public void setVocabularyStatus(VocabularyStatus vocabularyStatus) {
        this.vocabularyStatus = vocabularyStatus;
    }
}
