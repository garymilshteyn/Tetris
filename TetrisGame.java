import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class TetrisGame {
    private GameBoard board;
    private Tetromino currentPiece;
    private Timer timer;
    private Runnable repaintCallback;
    private boolean lockDelayActive = false;
    private int lockDelayTicks = 0;
    private final int LOCK_DELAY = 1; // 1 tick = 500ms


    public TetrisGame(){
        board = new GameBoard();
        spawnNewPiece();

        timer = new Timer(500, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                updateGame();
            }
        });
        timer.start();
    }

    //Tries to move the current piece down
    private void updateGame() {
        if (lockDelayActive) {
            lockDelayTicks++;
            if (lockDelayTicks >= LOCK_DELAY) {
                lockDelayActive = false;
                lockDelayTicks = 0;
                spawnNewPiece();
            }
            return;
        }
    
        if (canMoveDown(currentPiece)) {
            currentPiece.moveDown();
        } else {
            lockCurrentPiece();
            lockDelayActive = true;
            lockDelayTicks = 0;
        }
    
        if (repaintCallback != null) repaintCallback.run();
    }
    
    
    
    public void setRepaintCallback(Runnable r){
        repaintCallback = r;
    }

    public void spawnNewPiece() {
        currentPiece = PieceGenerator.getRandomPiece(5, 0);
    }
    
    

    public boolean canMoveDown(Tetromino t){
        return canMove(t, 0, 1);
    }
    public boolean canMove(Tetromino t, int dx, int dy) {
        for (Block b : t.getBlocks()) {
            int newRow = b.getY() + dy;
            int newCol = b.getX() + dx;
    
            // Out of bounds
            if (newRow<0 || newRow >= GameBoard.ROWS || newCol < 0 || newCol >= GameBoard.COLS) {
                return false;
            }
    
            // Hit another block
            if (board.isCellOccupied(newRow, newCol)) {
                return false;
            }
        }
    
        return true;
    }
    

    public void lockCurrentPiece(){
        board.addTetrominoToGrid(currentPiece);
        board.clearFullRows();
    }

    public Tetromino getCurrentPiece(){
        return currentPiece;
    }
    public GameBoard getBoard(){
        return board;
    }
    
}
