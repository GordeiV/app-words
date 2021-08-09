package content;

public class Word {
    private String foreignWord;
    private String nativeWord;
    private String transcription;

    public Word(String foreignWord, String nativeWord, String transcription) {
        this.foreignWord = foreignWord;
        this.nativeWord = nativeWord;
        this.transcription = transcription;
    }

    public Word(String foreignWord, String nativeWord) {
        this.foreignWord = foreignWord;
        this.nativeWord = nativeWord;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public String getNativeWord() {
        return nativeWord;
    }

    public void setNativeWord(String nativeWord) {
        this.nativeWord = nativeWord;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
