import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenuScene {
	private static Scene build(){
		AppModel.getWindow().setTitle("Main Menu");
		
		Button quizBtn = new Button("New Quiz");
		
		Button reviewBtn = new Button("Review");
		
		Button statsBtn = new Button("Statistics");
		statsBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				StatisticsScene.setScene();
			}
			
		});
		
		Button settingsBtn = new Button("Settings");
		settingsBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				SettingsScene.setScene();
			}
			
		});
		VBox layout1 = new VBox(20);
		layout1.setAlignment(Pos.CENTER);
		layout1.getChildren().addAll(quizBtn, reviewBtn, statsBtn, settingsBtn);

		return(new Scene(layout1,AppModel.getWidth(),AppModel.getHeight()));
		
	}
	
	public static void setScene(){
		Scene mainMenuScene = build();
		AppModel.setScene(mainMenuScene);
	}
}
