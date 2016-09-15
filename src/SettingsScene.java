import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class SettingsScene {
	private static Scene build(){
		AppModel.getWindow().setTitle("Settings");
		
		Label selectVoiceLbl= new Label("Select Voice to read out Quiz Words");
		
		final ToggleGroup group = new ToggleGroup();
		
		RadioButton defaultBtn = new RadioButton("Default Voice");
		RadioButton nzBtn = new RadioButton("New Zealand Voice");
		
		defaultBtn.setToggleGroup(group);
		defaultBtn.setSelected(true);
		
		nzBtn.setToggleGroup(group);
		
		//Sets vertical layout
				VBox layout1 = new VBox(20);
				layout1.setAlignment(Pos.CENTER);
				layout1.getChildren().addAll(selectVoiceLbl, defaultBtn, nzBtn);

				return(new Scene(layout1,AppModel.getWidth(),AppModel.getHeight()));
		
	}
	public static void setScene(){
		Scene settingsScene = build();
		AppModel.setScene(settingsScene);
	}
}
