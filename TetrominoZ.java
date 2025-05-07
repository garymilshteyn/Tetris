import java.awt.Color;

public class TetrominoZ extends Tetromino {
    public TetrominoZ(int startX, int startY){
        super(Color.RED);

        blocks[0] = new Block(startX-1, startY, blockSize, color);
        blocks[1] = new Block(startX, startY, blockSize, color);
        blocks[2] = new Block(startX, startY+1, blockSize, color);
        blocks[3] = new Block(startX+1, startY+1, blockSize, color);
        pivot = 1;
    }

    @Override
    public void rotate(){
        performRotationAroundCenter(pivot); // block[2] is pivot
    }
}
