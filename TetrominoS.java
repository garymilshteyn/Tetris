import java.awt.Color;

public class TetrominoS extends Tetromino {
    public TetrominoS(int startX, int startY){
        super(Color.GREEN);

        blocks[0] = new Block(startX, startY, blockSize, color);
        blocks[1] = new Block(startX+1, startY, blockSize, color);
        blocks[2] = new Block(startX-1, startY+1, blockSize, color);
        blocks[3] = new Block(startX, startY+1, blockSize, color);
        pivot = 1;
    }

    @Override
    public void rotate(){
        performRotationAroundCenter(pivot);
    }
}
