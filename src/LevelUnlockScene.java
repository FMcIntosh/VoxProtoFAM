import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


public class LevelUnlockScene {
	private static Scene build(){
		Label confirmLbl = new Label();

		if(AppModel.getLevelsUnlocked() == 1){
			AppModel.getWindow().setTitle("Level Unlocked");
			confirmLbl.setText("You have unlocked Level 1.");
		}else{
			AppModel.getWindow().setTitle("Level(s) Unlocked");
			confirmLbl.setText("You have unlocked Level " + AppModel.getLevelsUnlocked()
			+".\nYou may now access Levels 1 to " + AppModel.getLevelsUnlocked() + ".");
			confirmLbl.setTextAlignment(TextAlignment.CENTER);
		}
		Button okBtn = new Button("Okay");
		okBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				MainMenuScene.setScene();
			}
		});
		VBox layout1 = new VBox(20);
		layout1.setAlignment(Pos.CENTER);
		layout1.getChildren().addAll(confirmLbl, okBtn);

		return(new Scene(layout1,AppModel.getWidth(),AppModel.getHeight()));
	}
	
	public static void setScene(){
		Scene levelUnlockScene = build();
		AppModel.setScene(levelUnlockScene);
	}
}
