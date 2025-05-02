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
    private int lineClears = 0;
    private int level = 1;
    private int score;
    private boolean gameOver = false;

    

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
    
        if (repaintCallback != null) repaintCallback.run();}


    
    
    
    
    public void setRepaintCallback(Runnable r){
        repaintCallback = r;
    }

    public void spawnNewPiece() {
        currentPiece = PieceGenerator.getRandomPiece();

        
        if (board.isCellOccupied(0, 5)) {
            timer.stop();
            System.out.println("Game Over");
            gameOver = true;
            return;
        }
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

    // check if block can rotate before actually rotating it
    public boolean attemptRotate(Tetromino t) {

        if (t.canRotate(board)) {
            return true;
        }

        for (int i = 0; i < 2; i++) {
            switch (i) {
                case 0: //attempt to move piece to right before rotation
                    if (canMove(t, 1, 0)) {
                        t.move(1, 0);

                        //recursive call to check if another movement is needed for rotation
                        attemptRotate(t);
                        return true;
                    }
                    break;
                case 1: //attempt to move piece to left before rotation
                    if (canMove(t, -1, 0)) {
                        t.move(-1, 0);

                        //recursive call to check if another movement is needed for rotation
                        attemptRotate(t);
                        return true;
                    }
                    break;
                default:
                    break;
            }
        }

        return false;
        
    }

    

    

    public void lockCurrentPiece(){
        board.addTetrominoToGrid(currentPiece);
        int temp = lineClears;
        lineClears += board.clearFullRows();
        calcScore(lineClears, temp);

        //speed up game speed when line clear thresholds are met
        if (lineClears >= level * 10) {
            level++;

            if (timer.getDelay() > 100) {
                timer.setDelay(timer.getDelay() - 100);
                
            }
        }

        System.out.println(timer.getDelay());
    }


    //calculate points player receives after line clears
    public void calcScore(int newClears, int oldClears) {

        int cleared = newClears - oldClears;

        switch (cleared) {
            case 1: 
                score += 100 * level;
                break;
            case 2:
                score += 300 * level;
                break;
            case 3:
                score += 500 * level;
                break;
            case 4:
                score += 800 * level;
                break;
        } 
    }

    public boolean getGameOver() {

        return gameOver;

    }

    public Tetromino getCurrentPiece(){
        return currentPiece;
    }
    public GameBoard getBoard(){
        return board;
    }
    
}
