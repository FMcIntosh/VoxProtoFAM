import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Fraser McIntosh on 19/09/2016.
 */
public class QuizFinishedScene {
    private QuizModel _quizModel;
    private boolean _isReview;
    private WordState _currentWordState;

    QuizFinishedScene() {
        _quizModel = AppModel.getQuizModel();
        _isReview = _quizModel.getIsReview();
        _currentWordState = _quizModel.getWordState();
    }

    // Only get to this scene if quiz still going, so don't need to check that (or do we??)
    private Scene build() {

        //Label informing the user if the answered correctly or not
        Label label = new Label("You got " + _quizModel.getNumCorrectWords() +" out of " + _quizModel.getNumWordsInQuiz());

        // Button that either says "Next Word", or "Try Again", depending
        // on whether the previous answer was correct or not
        Button levelSelectButton = new Button("Level Select");
        levelSelectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LevelSelectScene.setScene();
            }
        });

        Button retryLevelButton = new Button("Retry Level");
        retryLevelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AppModel.startQuiz(_isReview, _quizModel.getLevelSelected());
            }
        });

        Button nextLevelButton = new Button("Next Level");
        nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AppModel.startQuiz(_isReview, _quizModel.getLevelSelected() + 1);
            }
        });
        //Layout
        HBox innerLayout = new HBox();


        // add components to inner layout

        //If final level, we don't want a next level button
        if(_quizModel.getLevelSelected() == AppModel.getNumLevels()) {
            innerLayout.getChildren().addAll(levelSelectButton, retryLevelButton);
        } else {
            innerLayout.getChildren().addAll(levelSelectButton, retryLevelButton, nextLevelButton);
        }

        innerLayout.setAlignment(Pos.CENTER);
        VBox outerLayout = new VBox(10);
        outerLayout.setPadding(new Insets(30, 0, 0, 0));
        //Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, innerLayout);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, AppModel.getWidth(), AppModel.getHeight());
    }

    public void setScene() {
        Scene WordResultScene = build();
        AppModel.setScene(WordResultScene);
    }


}
