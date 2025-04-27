import java.awt.Color;
import java.awt.Graphics;

public class Block{
    private int x;
    private int y;
    private int size;
    private Color color;

    public Block(int x, int y, int size, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x*size, y*size, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x*size, y*size, size, size);
    }

    //Moving the block on the grid
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }

    //Getters
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getSize(){
        return size;
    }
    public Color getColor(){
        return color;
    }

    //Setters
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y =y;
    }
    public void setColor(Color color){
        this.color = color;
    }
}