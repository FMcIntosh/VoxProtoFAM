import java.util.ArrayList;

/**
 * Created by Fraser McIntosh on 13/09/2016.
 */
public class QuizModel {

    private int _wordsInQuiz;
    private int _levelSelected;
    private ArrayList<String> _quizWords;
    private boolean _isReview;
    private int _curruntWordIndex;

    QuizModel(boolean isReview, int levelSelected) {
        _isReview = isReview;
        _levelSelected = levelSelected;
        _curruntWordIndex = 0;
       _quizWords = generateQuizWords();
        _wordsInQuiz = _quizWords.size();

    }

    /*
     * Helper method for constructor that generates the words for a quiz, utilisting FileModels
     * methods for getting words.
     */
    private ArrayList<String> generateQuizWords() {
        ArrayList<String> quizWords = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            // Get uniqueWords returns null if there are no more unique words
            String word = FileModel.getUniqueWord(_isReview, _levelSelected);
            if(word == null) {
                break;
            } else {
                quizWords.add(word);
            }
        }
        return quizWords;
    }

    // Getters -----------------------------------------------------------------------------------------

    public int getWordsInQuiz() {
        return _wordsInQuiz;
    }

    public int getLevelSelected() {
        return _levelSelected;
    }

    public ArrayList<String> getQuizWords() {
        return _quizWords;
    }

    public boolean getIsReview() {
        return _isReview;
    }

    public int getCurruntWordIndex() {
        return _curruntWordIndex;
    }

    public void setCurruntWordIndex(int curruntWordIndex) {
        _curruntWordIndex = curruntWordIndex;
    }
    
    // End of getters ------------------------------------------------------------------------------------------


}
