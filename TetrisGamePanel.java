import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;


public class TetrisGamePanel extends JPanel{
    private TetrisGame game;

    public TetrisGamePanel(){
        game = new TetrisGame();
        game.setRepaintCallback(()->repaint());
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                Tetromino t = game.getCurrentPiece();

                switch(e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        if(game.canMove(t, -1, 0)) t.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(game.canMove(t, 1, 0))t.moveRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        if(game.canMove(t, 0, 1)) t.moveDown();
                        break;
                    case KeyEvent.VK_UP:
                        t.rotate();
                        break;
                    case KeyEvent.VK_SPACE:
                        while(game.canMoveDown(t)){
                            t.moveDown();
                        }
                        game.lockCurrentPiece();
                        break;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //Draw locked blocks
        game.getBoard().draw(g);

        //Draw current piece
        game.getCurrentPiece().draw(g);
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(GameBoard.COLS * GameBoard.BLOCK_SIZE, GameBoard.ROWS * GameBoard.BLOCK_SIZE);
    }

}
