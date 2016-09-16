import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Fraser McIntosh on 20/08/2016.
 */
public class WordStatistic {
    private SimpleStringProperty word;
    private SimpleIntegerProperty faulted;
    private SimpleIntegerProperty failed;
    private SimpleIntegerProperty mastered;

    /*
     * Pass in the word to the constructor and sets the fields
     */
    WordStatistic(String word) {
        this.word = new SimpleStringProperty(word);
        faulted = new SimpleIntegerProperty(FileLogic.countOccurences(FileLogic.faulted_stats, word));
        failed = new SimpleIntegerProperty(FileLogic.countOccurences(FileLogic.failed_stats, word));
        mastered = new SimpleIntegerProperty(FileLogic.countOccurences(FileLogic.mastered_stats, word));
    }

    public String getWord() {
        return word.get();
    }

    public void setWord(String word) {
       this.word.set(word);
    }

    public int getFaulted() {
        return faulted.get();
    }

    public void setFaulted(int faulted) {
        this.faulted.set(faulted);
    }

    public int getFailed() {
        return failed.get();
    }

    public void setFailed(int failed) {
        this.failed.set(failed);
    }

    public int getMastered() {
         return mastered.get();
    }

    public void setMastered(int mastered) {
       this.mastered.set(mastered);
    }
}
