import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Fraser McIntosh on 14/09/2016.
 */
public class WordResultScene {
    private QuizModel _quizModel;
    private boolean _isReview;
    private WordState _currentWordState;

    WordResultScene() {
        _quizModel = AppModel.getQuizModel();
        _isReview = _quizModel.getIsReview();
        _currentWordState = _quizModel.getWordState();
    }

    // Only get to this scene if quiz still going, so don't need to check that (or do we??)
    private Scene build() {

        //Label informing the user if the answered correctly or not
        Label label1 = new Label();
        if (_currentWordState.equals(WordState.MASTERED) || _currentWordState.equals(WordState.FAULTED)) {
            label1.setText("Correct");
        } else {
            label1.setText("Incorrect");
        }

        // Button that either says "Next Word", or "Try Again", depending
        // on whether the previous answer was correct or not
        Button actionButton = new Button();
        /*
         * If quiz is finished take us to the finished quiz scene
         */
        if (_quizModel.getQuizState() == QuizState.FINISHED) {

            actionButton.setText("Finish Quiz");
            actionButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                  //  new QuizFinished().setScene();
                }
            });

        } else {

            if (_currentWordState.equals(WordState.INCORRECT)) {
                actionButton.setText("Try Again");
            } else {
                actionButton.setText("Next Word");
            }

             // Either way, this button will take the user back to the 'Enter Word' Scene
            actionButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new EnterWordScene().setScene();
                }
            });

        }

        //Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, actionButton);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout);
    }

    public void setScene() {
        Scene WordResultScene = build();
        AppModel.setScene(WordResultScene);
    }

}
