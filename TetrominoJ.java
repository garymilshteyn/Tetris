import java.awt.Color;

public class TetrominoJ extends Tetromino {
    public TetrominoJ(int startX, int startY){
        super(Color.BLUE);

        blocks[0] = new Block(startX - 2, startY, blockSize, color);
        blocks[1] = new Block(startX - 1, startY, blockSize, color);
        blocks[2] = new Block(startX, startY, blockSize, color);
        blocks[3] = new Block(startX, startY - 1, blockSize, color);
        pivot = 2;
    }

    @Override
    public void rotate(){
        performRotationAroundCenter(pivot);
    }
}
