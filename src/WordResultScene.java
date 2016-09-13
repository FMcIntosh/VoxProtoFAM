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
    private boolean _answerWasCorrect;

    WordResultScene() {
        _quizModel = AppModel.getQuizModel();
        _isReview = _quizModel.getIsReview();
        _answerWasCorrect = QuizModel.getIsAnswerCorrect();
    }

    private Scene build() {

        //Label informing the user if the answered correctly or not
        Label label1 = new Label();
        if(_answerWasCorrect) {
            label1.setText("Correct");
        } else {
            label1.setText("Incorrect");
        }

        // Button that either says "Next Word", or "Try Again", depending
        // on whether the previous answer was correct or not
        Button actionButton = new Button();
        if(_answerWasCorrect) {
            actionButton.setText("Next Word");
        } else {
            actionButton.setText("Try Again");
        }

        /*
         * Either way, this button will take the user back to the 'Enter Word' Scene
         */
        actionButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    EnterWordScene.setScene();
                }
            });
        //Layout
        VBox layout4 = new VBox(10);
        layout4.getChildren().addAll(label4, againButton);
        layout4.setAlignment(Pos.CENTER);
    }

    public void setScene() {
        Scene WordResultScene = build();
        AppModel.setScene(WordResultScene);
    }


}
