import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite2D {

    private BufferedImage[] imageFrames;
    private int frame;

    public Sprite2D(BufferedImage[] imageFrames) {
        this.imageFrames = imageFrames;

        frame =  0;
    }

    public void Draw(Graphics g, int width, int height) {
        g.drawImage(imageFrames[frame], 0, 0, width, height, null);
    }
}
