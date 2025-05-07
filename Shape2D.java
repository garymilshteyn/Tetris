import java.awt.*;

/**
 * @author - Niqolas Gonzalez
 * @version - (4/16/2025)
 */
abstract class Shape2D {

    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private Color fillColor;
    private int fillColorIndex;
    private Color outlineColor;
    private int outlineColorIndex;
    private boolean fill;
    private boolean outline;
    private double sX;
    private double sY;
    private double rotAnglez;

    public final static int RED = 0;
    public final static int GREEN = 1;
    public final static int BLUE = 2;
    public final static int BLACK = 3;
    public final static int GREY = 4;
    public final static int WHITE = 5;
    public final static int YELLOW = 6;
    public final static int CYAN = 7;
    public final static int MAGENTA = 8;
    public final static int BROWN = 9;
    public static final Color[] COLORS = {
// R G B
            new Color(255, 0, 0), // Red 0
            new Color( 0, 255, 0), // Green 1
            new Color( 0, 0, 255), // Blue 2
            new Color( 0, 0, 0), // Black 3
            new Color(128, 128, 128), // Grey 4
            new Color(255, 255, 255), // White 5
            new Color(255, 255, 0), // Yellow 6
            new Color( 0, 255, 255), // Cyan 7
            new Color(255, 0, 255), // Magenta 8
            new Color(165, 42, 42), // Brown 9
            new Color(255, 38, 38),
            new Color(255, 168, 38),
            new Color(212, 255, 38),
            new Color( 82, 255, 38),
            new Color( 38, 255, 125),
            new Color( 38, 255, 255),
            new Color(38, 85, 255),
            new Color( 82, 38, 255),
            new Color(212, 38, 255),
            new Color(255, 38, 168),
    };

    /**
     *
     */
    public Shape2D() {

        this.xPos = 0;
        this.yPos = 0;
        this.fillColorIndex = 2;
        this.fillColor = COLORS[BLUE];
    }

    /**
     *
     * @param fillColorIndex
     * @param xPos
     * @param yPos
     */
    public Shape2D(int fillColorIndex, int xPos, int yPos) {
        this.fillColor = COLORS[fillColorIndex];
        this.outlineColor = COLORS[BLACK];
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = 5;
        this.yVel = 5;
        this.fill = true;
        this.outline = true;
        this.sX = 1.0;
        this.sY = 1.0;

    }

    /**
     *
     * @param g
     */
    public abstract void Draw(Graphics g);
    public void move(int xVel, int yVel){
        xPos += xVel;
        yPos += yVel;
    }

    public void Animate() {
    }
    public void SetPos (int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void SetSpeed (int x, int y) {
        xVel = x;
        yVel = y;
    }

    /**
     *
     * @return
     */
    public int getxPos() {
        return xPos;
    }

    /**
     *
     * @param xPos
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     *
     * @return
     */
    public int getyPos() {
        return yPos;
    }

    /**
     *
     * @param yPos
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     *
     * @return
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     *
     * @param fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     *
     * @return
     */
    public int getFillColorIndex() {
        return fillColorIndex;
    }

    /**
     *
     * @param fillColorIndex
     */
    public void setFillColorIndex(int fillColorIndex) {
        this.fillColorIndex = fillColorIndex;
    }

    /**
     *
     * @return
     */
    public int getxVel() {
        return xVel;
    }

    /**
     *
     * @param xVel
     */
    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    /**
     *
     * @return
     */
    public int getyVel() {
        return yVel;
    }

    /**
     *
     * @param yVel
     */
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }

    /**
     *
     * @return
     */
    public Color getOutlineColor() {
        return outlineColor;
    }

    /**
     *
     * @param outlineColor
     */
    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = COLORS[outlineColorIndex];
    }

    /**
     *
     * @return
     */
    public int getOutlineColorIndex() {
        return outlineColorIndex;
    }

    /**
     *
     * @param outlineColorIndex
     */
    public void setOutlineColorIndex(int outlineColorIndex) {
        outlineColor = COLORS[outlineColorIndex];
    }

    /**
     *
     * @return
     */
    public boolean isFill() {
        return fill;
    }

    /**
     *
     * @param fill
     */
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    /**
     *
     * @return
     */
    public boolean isOutline() {
        return outline;
    }

    /**
     *
     * @param outline
     */
    public void setOutline(boolean outline) {
        this.outline = outline;
    }

    public double getsX() {
        return sX;
    }

    public void setsX(double sX) {
        this.sX = sX;
    }

    public double getsY() {
        return sY;
    }

    public void setsY(double sY) {
        this.sY = sY;
    }

    public double getRotAnglez() {
        return rotAnglez;
    }

    public void setRotAnglez(double rotAnglez) {
        this.rotAnglez = rotAnglez;
    }


}
