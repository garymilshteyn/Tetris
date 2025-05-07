import java.awt.Color;
import java.awt.Graphics;

public abstract class Tetromino {
    protected Block[] blocks = new Block[4];
    protected Color color;
    protected final int blockSize = 50;
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


    //checks for valid rotation before making rotation
    public boolean canRotate(GameBoard board) {
        Block center = blocks[getPivot()];

        int cx = center.getX();
        int cy = center.getY();

        for(Block b : blocks){
            int x = b.getX();
            int y = b.getY();

            int dx = x - cx;
            int dy = y - cy;

            int newX = cx - dy;
            int newY = cy + dx;

            // Out of bounds
            if (newX < 0 || newX >= GameBoard.COLS || newY < 0  || newY >= GameBoard.ROWS) {
                
                return false;
            }
        
            // Hit another block
            if (board.isCellOccupied(newY, newX)) {
                return false;
            }

        }

        

        return true;
    }

    
}
