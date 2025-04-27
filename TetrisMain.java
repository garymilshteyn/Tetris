import javax.swing.JFrame;

public class TetrisMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new TetrisGamePanel());
        frame.pack();
        frame.setResizable(false);
        frame.setContentPane(new TetrisGamePanel());
        frame.setVisible(true);
    }
}
