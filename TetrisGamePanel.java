import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class TetrisGamePanel extends JPanel{
    private TetrisGame game;
    private Sprite2D background;

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
                        if(game.attemptRotate(t)) t.rotate();
                        break;
                    case KeyEvent.VK_SPACE:
                        while(game.canMoveDown(t)){
                            t.moveDown();
                        }
                         
                        game.lockCurrentPiece();

                        if (game.getGameOver() == false)
                        game.spawnNewPiece();
                        break;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //draw background
        BufferedImage[] background = new BufferedImage[1];
        try {
            background[0] = ImageIO.read(new File("Background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sprite2D bg = new Sprite2D(background);
        bg.Draw(g);
        
        g.translate(250, 250);

        //show visible border for game board
        int width = GameBoard.COLS * GameBoard.BLOCK_SIZE;
        int height = GameBoard.ROWS * GameBoard.BLOCK_SIZE;
        
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        //Draw locked blocks
        game.getBoard().draw(g);

        //Draw current piece
        game.getCurrentPiece().draw(g);



    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        
        return new java.awt.Dimension(1000, 1000);

        // return new java.awt.Dimension(GameBoard.COLS * GameBoard.BLOCK_SIZE, GameBoard.ROWS * GameBoard.BLOCK_SIZE);
    }

}
