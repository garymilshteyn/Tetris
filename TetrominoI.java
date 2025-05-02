import java.awt.Color;

public class TetrominoI extends Tetromino {
    
    private boolean isVertical = true;
    
    public TetrominoI(int startX, int startY){
        super(Color.CYAN);

        blocks[0] = new Block(startX, startY, blockSize, color);
        blocks[1] = new Block(startX, startY+1, blockSize, color);
        blocks[2] = new Block(startX, startY+2, blockSize, color);
        blocks[3] = new Block(startX, startY+3, blockSize, color);
    }  

    @Override
    public void rotate() {
        Block center = blocks[2];
        int cx = center.getX();
        int cy = center.getY();

        if (isVertical) {
            // Rotate to horizontal
            blocks[0].setX(cx - 2); blocks[0].setY(cy);
            blocks[1].setX(cx - 1); blocks[1].setY(cy);
            blocks[2].setX(cx);     blocks[2].setY(cy);
            blocks[3].setX(cx + 1); blocks[3].setY(cy);
        } else {
            // Rotate to vertical
            blocks[0].setX(cx); blocks[0].setY(cy - 2);
            blocks[1].setX(cx); blocks[1].setY(cy - 1);
            blocks[2].setX(cx); blocks[2].setY(cy);
            blocks[3].setX(cx); blocks[3].setY(cy + 1);
        }

        isVertical = !isVertical;
    }

    @Override
    public boolean canRotate(GameBoard gameBoard) {

        Block center = blocks[2];
        int cx = center.getX();
        int cy = center.getY();

        int [][] newCoords;

        if (isVertical) {
            // Rotate to horizontal
            newCoords = new int [][] {
                {cx - 2, cy},
                {cx - 1, cy},
                {cx,     cy},
                {cx + 1, cy}
            };
        } else {
            // Rotate to vertical
            newCoords = new int[][] {
                {cx, cy -2},
                {cx, cy -1},
                {cx,     cy},
                {cx, cy + 1}
            };    
        }

        for (int[] coord : newCoords) {
            int x = coord[0];
            int y = coord[1];

            // Out of bounds
        if (x < 0 || x >= GameBoard.COLS || y < 0  || y >= GameBoard.ROWS) {
            return false;
        }

        // Hit another block
        if (gameBoard.isCellOccupied(y, x)) {
            return false;
        }
    }

    return true;
}
}

