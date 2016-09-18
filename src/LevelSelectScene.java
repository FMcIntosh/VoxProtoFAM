import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LevelSelectScene {


	private static boolean _isReview;

	private static Scene build(){
		//Set title
		AppModel.getWindow().setTitle("Level Select");

		//Labels current quiz mode - either new quiz or review mode
		Label titleLbl = new Label();

		//Sets title to mode type
		if(_isReview){
			titleLbl.setText("Review Mode");
		}else{
			titleLbl.setText("New Quiz");
		}

		//Details instructions for user
		Label promptLbl = new Label("Please select the level of the test words");

		//Create overarching layout for this scene and centers it
		VBox root = new VBox(30);
		root.setAlignment(Pos.CENTER);

		//Layout for the 10 buttons
		VBox buttonLayout = new VBox();

		//Generates each of the 10 buttons, one by one
		for(int i = 1; i < 11; i++){
			//Sets the text of button
			Button levelBtn = new Button("Level "+i);

			//Generates event for the current button
			levelBtn.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					//Gets the level that the button corresponds to
					String str = levelBtn.getText().replaceAll("\\D+","");
					int level = Integer.parseInt(str);

					//Initialises new quiz model object with the selected level
					QuizState quizState = AppModel.setQuizModel(_isReview, level);

					//If Quiz is ready
					// Initialises new EnterWordScene scene to be built next
					if(quizState.equals(QuizState.READY)) {
						EnterWordScene wordScene = new EnterWordScene();
						wordScene.setScene();
						// Else if no words display no words scene
					} else if (quizState.equals(QuizState.NO_WORDS)){
						NoWordsScene.setScene();
					}
				}
			});

			//Disables button if it corresponds to a level that is not unlocked yet
			if(i > AppModel.getLevelsUnlocked()){
				levelBtn.setDisable(true);
			}
			//Adds button to the button layout
			buttonLayout.getChildren().add(levelBtn);
		}
		//Centers button layout
		buttonLayout.setAlignment(Pos.CENTER);

		//Adds all components to root layout and returns the scene containing the layout
		root.getChildren().addAll(titleLbl, promptLbl,buttonLayout);
		return(new Scene(root,AppModel.getWidth(),AppModel.getHeight()));
	}

	//Sets the scene of the window as the Level Select Scene
	public static void setScene(){
		Scene lvlSelectScene = build();
		AppModel.setScene(lvlSelectScene);
	}

	//Allows this class to know if it is in review mode or not
	public static void setIsReview(boolean isReview){
		_isReview = isReview;
	}

}
