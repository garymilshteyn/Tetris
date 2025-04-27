import java.awt.Color;

public class TetrominoO extends Tetromino {
    public TetrominoO(int startX, int startY) {
        super(Color.YELLOW);

        blocks[0] = new Block(startX,     startY,     blockSize, color);
        blocks[1] = new Block(startX + 1, startY,     blockSize, color);
        blocks[2] = new Block(startX,     startY + 1, blockSize, color);
        blocks[3] = new Block(startX + 1, startY + 1, blockSize, color);
    }

    @Override
    public void rotate() {
        // [] piece doesn't rotate — it's symmetrical
    }
}
