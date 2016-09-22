package app.scene;

import app.AppModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainMenuScene {
	private static Scene build(){
		AppModel.getWindow().setTitle("Main Menu");
		Label title = new Label("Welcome to VoxSpell!");
		title.setFont(Font.font ("Verdana", 35));
		title.setTranslateY(-40);
		
		Button quizBtn = new Button("New Quiz");
		quizBtn.setMinWidth(200);
		quizBtn.setMinHeight(40);
		quizBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				LevelSelectScene.setIsReview(false);
				LevelSelectScene.setScene();
			}
		});
		
		Button reviewBtn = new Button("Review");
		reviewBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				LevelSelectScene.setIsReview(true);
				LevelSelectScene.setScene();
			}
		});
		
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
		VBox layout1 = new VBox();
		layout1.setAlignment(Pos.CENTER);
		layout1.getChildren().addAll(title, quizBtn, reviewBtn, statsBtn, settingsBtn);

		return(new Scene(layout1, AppModel.getWidth(), AppModel.getHeight()));
		
	}
	
	public static void setScene(){
		Scene mainMenuScene = build();
		AppModel.setScene(mainMenuScene);
	}
}
