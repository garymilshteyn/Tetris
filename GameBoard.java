import java.awt.Graphics;

public class GameBoard {
    public static final int ROWS = 20;
    public static final int COLS = 10;
    public static final int BLOCK_SIZE = 50;

    private Block[][] grid;

    public GameBoard(){
        grid = new Block[ROWS][COLS];
    }

    //Draw locked blocks
    public void draw(Graphics g){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                Block b = grid[row][col];
                if(b != null){
                    b.draw(g);
                }
            }
        }
    }

    //Add a single block to the board
    public void addBlock(Block b){
        int row = b.getY();
        int col = b.getX();
        if(isValidPosition(row, col)){
            grid[row][col] = b;
        }
    }

    //Add all blocks from a tetromino to the board
    public void addTetrominoToGrid(Tetromino t){
        for(Block b : t.getBlocks()){
            addBlock(b);
        }
    }

    //Check if a specific cell is already occupied
    public boolean isCellOccupied(int row, int col){
        if(!isValidPosition(row, col))
            return true;    //Out of bounds = collision
        return grid[row][col] != null;
    }

    //Check bounds
    public boolean isValidPosition(int row, int col){
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    //Accessors
    public Block getBlock(int row, int col){
        if(isValidPosition(row, col)){
            return grid[row][col];
        }
        return null;
    }

    public int clearFullRows(){
       int lines = 0;
       
        for(int row = GameBoard.ROWS - 1; row >= 0; row--){
        if(isRowFull(row)){
            clearRow(row);
            shiftRowsDown(row);
            row++;
            lines++;
        }
       }
       return lines;
    }

    public boolean isRowFull(int row){
        for(int col = 0; col < GameBoard.COLS; col++){
            if(grid[row][col] == null){
                return false;
            }
        }
        return true;
    }

    public void clearRow(int row){
        for(int col = 0; col < GameBoard.COLS; col++){
            grid[row][col] = null; 
        }
    }

    public void shiftRowsDown(int startRow){
        for(int row = startRow; row > 0; row--){
            for(int col = 0; col < GameBoard.COLS; col++){
                grid[row][col] = grid[row-1][col];
                if(grid[row][col] != null)
                    grid[row][col].setY(row);
            }
        }

        for(int col = 0; col < GameBoard.COLS; col++){
            grid[0][col] = null;
        }
    }
}

