package app.scene;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;



/**
 * Created by Fraser McIntosh on 21/09/2016.
 */
public class VLCJMediaPlayerScene {


        private EmbeddedMediaPlayerComponent mediaPlayerComponent;

        //This is the path for libvlc.dll
        static String VLCLIBPATH = "C:\\Program Files (x86)\\VideoLAN\\VLC";
        public static void main(String[] args) {
            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), VLCLIBPATH);
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
//                    VLCPlayer vlcPlayer = new VLCPlayer("Fedora");
                }
            });
        }

        public void play() {

//MAXIMIZE TO SCREEN
            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

            JFrame frame = new JFrame("VLC Player");

            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
            EmbeddedMediaPlayer mediaObject = mediaPlayerComponent.getMediaPlayer();
            frame.setContentPane(mediaPlayerComponent);

            frame.setLocation(0, 0);
            frame.setSize(screenSize.width, screenSize.height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            mediaObject.playMedia("media/movie.mp4");//Movie name which want to play
        }
}
