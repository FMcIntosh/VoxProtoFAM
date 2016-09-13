import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeScene extends Application{
	private int _selectedLevel;
	
	//main method just here to test class individually
	public static void main(String[] args){
		AppModel.setup();
		launch(args); //goes into Application and sets everything up, then invokes start()
	}

	public void start(Stage primaryStage) throws Exception {
		AppModel.setWindow(primaryStage);
		AppModel.getWindow().setTitle("Welcome");

		Label welcomeLbl = new Label("Welcome to VoxSpell");
		Label selectLbl = new Label("To get started, please select your desired starting level");
		Button selectBtn = new Button("Select");

		//create drop down box containing all available levels
		final ComboBox<String> comboBox = new ComboBox<String>();
		for (int i = 1; i< 11;i++){
			comboBox.getItems().add("Level "+i);
		}

		//modifiable to fit the window appropriately
		comboBox.setVisibleRowCount(4);

		//when selectbutton is clicked, read selected value from drop down box
		selectBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				if(comboBox.getValue() == null){
					//add alert box informing user to select level here
				}else{
					try {
						setLevelNo(comboBox.getValue());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});


		VBox layout1 = new VBox(20);
		layout1.setAlignment(Pos.CENTER);
		layout1.getChildren().addAll(welcomeLbl, selectLbl, comboBox, selectBtn);

		Scene scene1 = new Scene(layout1,AppModel.getWidth(),AppModel.getHeight());

		AppModel.getWindow().setScene(scene1);

		AppModel.getWindow().show();
	}

	private void setLevelNo(String levelString) throws FileNotFoundException{
		String str = levelString.replaceAll("\\D+","");
		System.out.println(str);
		_selectedLevel = Integer.parseInt(str);
		AppModel.setLevelsUnlocked(_selectedLevel);
	}
}
