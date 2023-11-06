import java.util.Objects;

public interface BishopMovement {
    default boolean moveUpRight(Square from, Square to, Board board){
        for(int x = from.getX() - 1, y = from.getY() + 1; x > 0 && y < 9; x--, y++) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    };
    default boolean moveUpLeft(Square from, Square to, Board board){
        for(int x = from.getX() - 1, y = from.getY() - 1; x > 0 && y > 0; x--, y--) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    };
    default boolean moveDownRight(Square from, Square to, Board board){
        for(int x = from.getX() + 1, y = from.getY() + 1; x < 9 && y < 9; x++, y++) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    };
    default boolean moveDownLeft(Square from, Square to, Board board){
        for(int x = from.getX() + 1, y = from.getY() - 1; x < 9 && y > 0; x++, y--) {
            if(Objects.equals(to, new Square(x, y))) return true;
            else if (board.isPieceAt(new Square(x, y))) return false;
        }
        return false;
    };
}
