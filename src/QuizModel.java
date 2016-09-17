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
    private WordModel _wordModel;


    QuizModel(boolean isReview, int levelSelected) {
        _isReview = isReview;
        _levelSelected = levelSelected;
        _curruntWordIndex = 0;
        _quizWords = generateQuizWords();
        _numWordsInQuiz = _quizWords.size();
        _numCorrectWords = 0;
        _wordModel = new WordModel();
        if (_numCorrectWords > 0) {
            _quizState = QuizState.READY;
        } else _quizState = QuizState.NO_WORDS;
    }

    /*
     * Helper method for constructor that generates the words for a quiz, utilisting FileModel's
     * methods for getting words.
     */
    private ArrayList<String> generateQuizWords() {
        ArrayList<String> quizWords = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            // Decide what file to take from
            WordFile file = WordFile.SPELLING_LIST;
            if(_isReview) {
                file = WordFile.REVIEW;
            }
            // Take a random word
            String word = FileModel.randWordFromLevel(file, _levelSelected);
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

    public WordState getWordState() {
        return _wordModel.getWordState();
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


    // End of getters ------------------------------------------------------------------------------------------



    /*
     * Update the current state of the quiz, including the state of the word
     */
    public void updateQuizState() {
        // If the word is failed or mastered, it is finished so need to go to the next word
        if(_wordModel.getWordState() == WordState.FAILED || _wordModel.getWordState() == WordState.MASTERED) {
            _curruntWordIndex++;
            _wordModel = new WordModel();
        }
        // If we have gone through all words in the quiz, the quiz is finished
        if(_numWordsInQuiz == _curruntWordIndex){
            _quizState = QuizState.FINISHED;
        }
    }

    // Answer submission logic ---------------------------------------------------------------------------------

    public boolean submitAnswer (String answer) {
        //Verify valid
        if(!answer.matches("[a-zA-Z]+")){
            return false;
        } else {
            //update model state by passing through the answer result (true/false)
            _wordModel.updateWordState(answer == getCurrentWord());
            updateQuizState();
        }

        // Return a true response to the view if successful submission
        return true;
    }
}
