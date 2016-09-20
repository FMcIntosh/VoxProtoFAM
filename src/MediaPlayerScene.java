import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URI;

/**
 * Created by Fraser McIntosh on 19/09/2016.
 */
public class MediaPlayerScene {
    static MediaPlayer _player;
    final static VBox vbox = new VBox();
    public static Scene build() {
        Group root = new Group();
        URI path = new File("big_buck_bunny_1_minute.mp4").toURI();
        Media media = new Media(path.toString());
        _player = new MediaPlayer(media);
        MediaView view = new MediaView(_player);


        Slider slider = new Slider();
        vbox.getChildren().add(slider);
        root.getChildren().addAll(view, vbox);

        return new Scene(root, AppModel.getWidth(), AppModel.getHeight());

    }


    public static void setScene(){
        Scene mediaScene = build();
        AppModel.setScene(mediaScene);

       _player.play();
        _player.setOnReady(new Runnable() {
            @Override
            public void run() {
                int w = _player.getMedia().getWidth();
                int h =_player.getMedia().getHeight();

                vbox.setMinSize(w, 100);
                vbox.setTranslateY(h -100);
            }
        });
    }
}
