import java.util.ArrayList;

/**
 * Created by Fraser McIntosh on 13/09/2016.
 */
public class QuizModel {

    private int _numWordsInQuiz;
    private int _numCorrectWords;
    private int _levelSelected;
    private ArrayList<String> _quizWords;
    private boolean _isReview;
    private int _curruntWordIndex;
    private QuizState _quizState;
    private boolean _quizFinished;

    QuizModel(boolean isReview, int levelSelected) {
        _isReview = isReview;
        _levelSelected = levelSelected;
        _curruntWordIndex = 0;
        _quizWords = generateQuizWords();
        _numWordsInQuiz = _quizWords.size();
        _numCorrectWords = 0;
        _quizFinished = (_numWordsInQuiz == _curruntWordIndex);
        if (_numCorrectWords > 0) {
            _quizState = QuizState.STARTED;
        } else _quizState = QuizState.NO_WORDS;
    }

    /*
     * Helper method for constructor that generates the words for a quiz, utilisting FileModel's
     * methods for getting words.
     */
    private ArrayList<String> generateQuizWords() {
        ArrayList<String> quizWords = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            // Get uniqueWords returns null if there are no more unique words
            String word = FileModel.getUniqueWord(_isReview, quizWords, _levelSelected);
            if(word == null) {
                break;
            } else {
                quizWords.add(word);
            }
        }
        return quizWords;
    }

    // Getters -----------------------------------------------------------------------------------------

    public int getNumWordsInQuiz() {
        return _numWordsInQuiz;
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

    public QuizState getQuizState(){
        return _quizState;
    }



    public String getCurrentWord() {
        return _quizWords.get(_curruntWordIndex);
    }

    public boolean getQuizFinished() {
        return _quizFinished;
    }

    // End of getters ------------------------------------------------------------------------------------------



    public void updateQuizState() {
        _curruntWordIndex++;
        _quizFinished = (_numWordsInQuiz == _curruntWordIndex);
    }

    // Answer submission logic ---------------------------------------------------------------------------------

    public boolean submitAnswer (String answer) {
        //Verify valid
        if(!answer.matches("[a-zA-Z]+")){
            return false;
        } else {
            //update model state by passing through the answer result (true/false)
            updateWordState(checkAnswer(answer));
            updateQuizState();
        }

        // Return a true response to the view if successful submission
        return true;
    }

    /*
     * Simple helper method that
     */
    private boolean checkAnswer(String word) {
        return (word == getCurrentWord());
    }

}
