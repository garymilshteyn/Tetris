import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class TetrisGamePanel extends JPanel{
    private TetrisGame game;
    private ArrayList<Shape2D> shapesList = new ArrayList<>();

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

        int width;
        int height;

        int windowWidth = getWidth();
        int windowHeight = getHeight();

        //draw background
        BufferedImage[] background = new BufferedImage[1];
        try {
            background[0] = ImageIO.read(new File("Background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sprite2D bg = new Sprite2D(background);
        bg.Draw(g, windowWidth, windowHeight);
        
        
        
        //buffer for board so that it is placed at middle of screen
        int xBuffer = ((windowWidth - (GameBoard.COLS * GameBoard.BLOCK_SIZE)) / 2 );
        int yBuffer = ((windowHeight - (GameBoard.ROWS * GameBoard.BLOCK_SIZE)) / 2);

        //show visible border for game board
        width = GameBoard.COLS * GameBoard.BLOCK_SIZE;
        height = GameBoard.ROWS * GameBoard.BLOCK_SIZE;
        
        Shape2D board = new Rectangle2D(Shape2D.BLACK, 0, 0,  width, height);
        board.setOutlineColorIndex(Shape2D.WHITE);
        g.translate(xBuffer, yBuffer);
        board.Draw(g);
        
        //Draw locked blocks
        game.getBoard().draw(g);

        //Draw current piece
        game.getCurrentPiece().draw(g);
        g.translate(-xBuffer, -yBuffer);
        
        
        //specifiy location and size of box that holds upcoming piece
        int xPieceBuffer = xBuffer + (GameBoard.COLS * GameBoard.BLOCK_SIZE) + 10;
        int yPieceBuffer = (yBuffer + (GameBoard.ROWS * GameBoard.BLOCK_SIZE)) / 2 - 10;
        width = 5 * GameBoard.BLOCK_SIZE;
        height = 6 * GameBoard.BLOCK_SIZE;
        
        //draw box
        g.translate(xPieceBuffer, yPieceBuffer);
        Shape2D nextPiece = new Rectangle2D(Shape2D.BLACK, 0, 0, width, height);
        nextPiece.setOutlineColorIndex(Shape2D.WHITE);
        nextPiece.Draw(g);
        g.translate(-xPieceBuffer, -yPieceBuffer);


        
        //specify location and size of box that holds current and high scores
        int xScoreBuffer = xPieceBuffer;
        int yScoreBuffer = (yBuffer + (GameBoard.ROWS * GameBoard.BLOCK_SIZE)) / 15;
        width = 8 * GameBoard.BLOCK_SIZE;
        height = 10 * GameBoard.BLOCK_SIZE;
        
        //draw box
        g.translate(xScoreBuffer, yScoreBuffer);
        Shape2D scoreBoard = new Rectangle2D(Shape2D.BLACK, 0, 0, width, height);
        scoreBoard.setOutlineColorIndex(Shape2D.WHITE);
        scoreBoard.Draw(g);
        g.translate(-xScoreBuffer, -yScoreBuffer);



        //specify location and size of box that holds lines cleared
        int xLineBuffer = xBuffer;
        int yLineBuffer = yScoreBuffer;
        width = GameBoard.COLS * GameBoard.BLOCK_SIZE;
        height = 2 * GameBoard.BLOCK_SIZE + 15;
        
        //draw box
        g.translate(xLineBuffer, yLineBuffer);
        Shape2D numLinesBoard = new Rectangle2D(Shape2D.BLACK, 0, 0, width, height);
        numLinesBoard.setOutlineColorIndex(Shape2D.WHITE);
        numLinesBoard.Draw(g);
        g.translate(-xLineBuffer, -yLineBuffer);


        
        //specify location and size of box that holds current level
        int xLevelBuffer = xPieceBuffer;
        int yLevelBuffer = (yBuffer + (GameBoard.ROWS * GameBoard.BLOCK_SIZE) - 300 );
        width = 8 * GameBoard.BLOCK_SIZE;
        height = 3 * GameBoard.BLOCK_SIZE;
        
        //draw box
        g.translate(xLevelBuffer, yLevelBuffer);
        Shape2D levelBoard = new Rectangle2D(Shape2D.BLACK, 0, 0, width, height);
        levelBoard.setOutlineColorIndex(Shape2D.WHITE);
        levelBoard.Draw(g);
        g.translate(-xLevelBuffer, -yLevelBuffer);



        



    }

}
