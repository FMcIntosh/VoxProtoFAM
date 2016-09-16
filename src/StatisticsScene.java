import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class StatisticsScene {
	private static Scene build(){
		AppModel.getWindow().setTitle("Statistics");
		
		//Create root and scene to be built
		Group root = new Group();
        Scene scene = new Scene(root, AppModel.getWidth(), AppModel.getHeight(), Color.WHITE);

        TabPane tabPane = new TabPane();

        BorderPane borderPane = new BorderPane();
        
        //Create table layout for each level, in each of their own separate tabs
        for (int i = 1; i < 10; i++) {
        	//Create tab for level based on the current iteration number of for loop
            Tab tab = new Tab();
            tab.setText("Level " + i);
            
            //Create new instance of Statistics class passing level number into the object
            Statistics statsObject = new Statistics(i);
            
            //Construct the table of words for current level and add to this level's tab pane
            tab.setContent(statsObject.constructTableLayout());
            tabPane.getTabs().add(tab);
        }
        
        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        return scene;
	}
	
	public static void setScene(){
		Scene statsScene = build();
		AppModel.setScene(statsScene);
	}
}
