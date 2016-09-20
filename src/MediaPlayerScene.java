import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URI;

/**
 * Created by Fraser McIntosh on 19/09/2016.
 */
public class MediaPlayerScene {

    public static void setScene(){
        Group root = new Group();
        URI path = new File("big_buck_bunny_1_minute.mp4").toURI();
        Media media = new Media(path.toString());
        final MediaPlayer player = new MediaPlayer(media);
        MediaView view = new MediaView(player);


        final Slider slider = new Slider();
        final VBox sliderBox = new VBox();
        final HBox controlLayout = new HBox();
        final VBox controls = new VBox();
        sliderBox.getChildren().add(slider);



        Button stop= new Button("Stop");
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.stop();
            }
        });

        Button pause = new Button("Pause");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.pause();
            }
        });

        Button play = new Button("play");
       play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.play();
            }
        });

        controlLayout.setAlignment(Pos.CENTER);
        controlLayout.getChildren().addAll(stop, pause, play);
        controlLayout.setTranslateY(330);
        controls.getChildren().addAll(sliderBox, controlLayout);
        root.getChildren().addAll(view,controls);





        final Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Scene mediaScene = new Scene(root, AppModel.getWidth(), AppModel.getHeight());
        window.setScene(mediaScene);
        window.setResizable(false);
        window.show();

        player.play();
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                int w = player.getMedia().getWidth();
                int h =player.getMedia().getHeight();

                window.setMinWidth(w);
                window.setMinHeight(h);
                sliderBox.setMinSize(w, 100);
                sliderBox.setTranslateY(h - 70);

                slider.setMin(0.0);
                slider.setValue(0.0);
                slider.setMax(player.getTotalDuration().toSeconds());
            }
        });

         player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration duration, Duration current) {
                slider.setValue(current.toSeconds());
            }
        });
        slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek((Duration.seconds(slider.getValue())));
            }
        });
    }
}
