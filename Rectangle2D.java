import java.awt.*;

/**
 * @author - Niqolas Gonzalez
 * @version - (4/16/2025)
 */
public class Rectangle2D extends Shape2D {

    private int length;
    private int width;

    /**
     *
     */
    public Rectangle2D() {
        super(10, 50, 50 );
        this.length = 200;
        this.width = 100;
    }

    /**
     *
     * @param fillColorIndex
     * @param xPos
     * @param yPos
     * @param length
     * @param width
     */
    public Rectangle2D(int fillColorIndex, int xPos, int yPos, int length, int width) {
        super( fillColorIndex, xPos, yPos);
        this.length = length;
        this.width = width;
    }


    /**
     *
     * @param g
     */
    public void Draw(Graphics g) {

        if (isFill()) {
            g.setColor(super.getFillColor());
            g.fillRect(super.getxPos(),super.getyPos(),length,width);
        }
        if (isOutline()) {
            g.setColor(super.getOutlineColor());
            g.drawRect(super.getxPos(),super.getyPos(),length,width);
        }
    }
}
