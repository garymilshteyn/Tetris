import java.awt.Color;
import java.awt.Graphics;

public abstract class Tetromino {
    protected Block[] blocks = new Block[4];
    protected Color color;
    protected final int blockSize = 30;
    protected int pivot;

    public Tetromino(Color color){
        this.color = color;
    }

    public int getPivot() {
        return pivot;
    }

    //Move entire shape
    public void move(int dx, int dy){
        for(Block b : blocks){
            b.move(dx, dy);
        }
    }

    public void moveLeft(){
        move(-1, 0);
    }
    public void moveRight(){
        move(1, 0);
    }
    public void moveDown(){
        move(0, 1);
    }
    
    //Draw the tetromino
    public void draw(Graphics g){
        for(Block b: blocks){
            b.draw(g);
        }
    }

    //Return blocks
    public Block[] getBlocks(){
        return blocks;
    }

    public Color getColor(){
        return color;
    }

    public abstract void rotate();
    protected void performRotationAroundCenter(int centerIndex){
        Block center = blocks[centerIndex];

        int cx = center.getX();
        int cy = center.getY();

        for(Block b : blocks){
            int x = b.getX();
            int y = b.getY();

            int dx = x - cx;
            int dy = y - cy;

            int newX = cx - dy;
            int newY = cy + dx;

            b.setX(newX);
            b.setY(newY);
        }
    }
}
