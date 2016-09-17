/**
 * Created by Fraser McIntosh on 16/09/2016.
 */
public class WordModel {

    private WordState _wordState;

    WordModel() {
        _wordState = WordState.STARTED;
    }
    public WordState getWordState(){
        return _wordState;
    }

    public void updateWordState(boolean isCorrectAnswer) {
        switch(_wordState) {
            case STARTED:
                if(isCorrectAnswer) {
                    _wordState = WordState.MASTERED;
                } else {
                    _wordState = WordState.INCORRECT;
                }
                break;
            case INCORRECT:
                if(isCorrectAnswer) {
                    _wordState = WordState.FAULTED;
                } else {
                    _wordState = WordState.FAILED;
                }
                break;
        }
    }
}
