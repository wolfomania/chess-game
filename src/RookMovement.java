import java.util.Objects;

public interface RookMovement {
    default boolean moveLeft(Square from, Square to, Board board) {
        for(int x = from.getX(), y = from.getY() - 1; y > 0; y--) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    }

    default boolean moveDown(Square from, Square to, Board board) {
        for(int x = from.getX() + 1, y = from.getY(); x < 9; x++) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    }

    default boolean moveRight(Square from, Square to, Board board) {
        for(int x = from.getX(), y = from.getY() + 1; y < 9; y++) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    }

    default boolean moveUp(Square from, Square to, Board board) {
        for(int x = from.getX() - 1, y = from.getY(); x > 0; x--) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    }
}
