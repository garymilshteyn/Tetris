import javax.swing.JFrame;

public class TetrisMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new TetrisGamePanel());
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setVisible(true);
        

        audioPlayer theme = new audioPlayer("Tetris.wav");
       
       //continue looping song while program is running
        while (true) {
        Thread thread1 = new Thread(theme);
        thread1.start();
        try {
        Thread.sleep(178000);
        } catch (InterruptedException e) {
            break;
        }
       }

    }
}
