import javax.sound.sampled.*;
import java.io.File;

public class audioPlayer implements Runnable{

    private String soundFilePath;

    public audioPlayer(String soundFilePath) {
        this.soundFilePath = soundFilePath;
    }

    @Override
    public void run() {
        playSound(soundFilePath);
    }


    private void playSound(String filePath) {

        try {
            //create file for the audio file to be stored in
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();

            //open and start the audio file
            clip.open(audioStream);
            clip.start();
            clip.addLineListener(new LineListener() {

                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.close();
                }
            });

            while (clip.isOpen()) {
                if (Thread.currentThread().isInterrupted()) {
                    clip.close();
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
