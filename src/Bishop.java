import java.util.Objects;

public class Bishop extends Piece implements BishopMovement{

    public Bishop(boolean isWhite, Square square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if(isWhite())
            return "WB";
        else
            return "BB";
    }

    @Override
    public boolean canMove(Square square, Board board) {
        if(super.canMove(square, board)) {
            return moveAround(square, board) && (!board.isPieceAt(square) || board.getBoard().get(square).isWhite() != isWhite());
        } else
            return false;
    }
    @Override
    public boolean canBeat(Square square, Board board) {
        return canMove(square, board);
    }

    private boolean moveAround(Square square, Board board) {
        return moveUpRight(this.getSquare(), square, board) || moveDownRight(this.getSquare(), square, board) ||
                moveDownLeft(this.getSquare(), square, board) || moveUpLeft(this.getSquare(), square, board);
    }
//    private boolean moveUpRight(Square square, Board board){
//        for(int x = getSquare().getX() - 1, y = getSquare().getY() + 1; x > 0 && y < 9; x--, y++) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }
//    private boolean moveUpLeft(Square square, Board board){
//        for(int x = getSquare().getX() - 1, y = getSquare().getY() - 1; x > 0 && y > 0; x--, y--) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }
//    private boolean moveDownRight(Square square, Board board){
//        for(int x = getSquare().getX() + 1, y = getSquare().getY() + 1; x < 9 && y < 9; x++, y++) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }
//    private boolean moveDownLeft(Square square, Board board){
//        for(int x = getSquare().getX() + 1, y = getSquare().getY() - 1; x < 9 && y > 0; x++, y--) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }
}
