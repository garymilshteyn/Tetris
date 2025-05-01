import java.util.Random;

public class PieceGenerator {
    private static final Random rand = new Random();

    public static Tetromino getRandomPiece(){
        int r = rand.nextInt(7);

        switch(r){
            case 0:
                return new TetrominoI(5, 0);
            case 1:
                return new TetrominoO(5, 0);
            case 2:
                return new TetrominoT(5, 1);
            case 3:
                return new TetrominoL(5, 2);
            case 4:
                return new TetrominoJ(5, 2);
            case 5:
                return new TetrominoS(5, 0);
            case 6:
                return new TetrominoZ(5, 0);
            default:
                return new TetrominoI(5, 0);
        }
    }
}
