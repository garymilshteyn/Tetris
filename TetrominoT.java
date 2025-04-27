import java.awt.Color;

public class TetrominoT extends Tetromino {
    public TetrominoT(int startX, int startY){
        super(Color.MAGENTA);

        blocks[0] = new Block(startX-1, startY, blockSize, color);
        blocks[1] = new Block(startX, startY-1, blockSize, color);
        blocks[2] = new Block(startX, startY, blockSize, color);
        blocks[3] = new Block(startX+1, startY, blockSize, color);
    }

    @Override
    public void rotate(){
        performRotationAroundCenter(2); // block[2] is pivot
    }
}
