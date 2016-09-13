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
    private QuizState _quizState;

    QuizModel(boolean isReview, int levelSelected) {
        _isReview = isReview;
        _levelSelected = levelSelected;
        _curruntWordIndex = 0;
       _quizWords = generateQuizWords();
        _wordsInQuiz = _quizWords.size();
        _quizState = QuizState.STARTED;
    }

    /*
     * Helper method for constructor that generates the words for a quiz, utilisting FileModel's
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

   public QuizState getQuizState() {
        return _quizState;
    }

    // End of getters ------------------------------------------------------------------------------------------

    public void updateState(boolean isCorrectAnswer) {
        switch(_quizState) {
            case STARTED:
                if(isCorrectAnswer) {
                    _quizState = QuizState.MASTERED;
                } else {
                    _quizState = QuizState.INCORRECT;
                }
                break;
            case INCORRECT:
                if(isCorrectAnswer) {
                    _quizState = QuizState.FAULTED;
                } else {
                    _quizState = QuizState.FAILED;
                }
                break;
        }
    }
}
