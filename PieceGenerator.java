import java.util.Random;

public class PieceGenerator {
    private static final Random rand = new Random();

    public static Tetromino getRandomPiece(int startX, int startY){
        int r = rand.nextInt(7);

        switch(r){
            case 0:
                return new TetrominoI(startX, startY);
            case 1:
                return new TetrominoO(startX, startY);
            case 2:
                return new TetrominoT(startX, startY);
            case 3:
                return new TetrominoL(startX, startY);
            case 4:
                return new TetrominoJ(startX, startY);
            case 5:
                return new TetrominoS(startX, startY);
            case 6:
                return new TetrominoZ(startX, startY);
            default:
                return new TetrominoI(startX, startY);
        }
    }
}
