import java.util.Objects;

public class Queen extends Piece implements BishopMovement, RookMovement{

    public Queen(boolean isWhite, Square square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if(isWhite())
            return "WQ";
        else
            return "BQ";
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
        return moveUp(this.getSquare(), square, board) || moveUpRight(this.getSquare(), square, board) ||
                moveRight(this.getSquare(), square, board) || moveDownRight(this.getSquare(), square, board) ||
                moveDown(this.getSquare(), square, board) || moveDownLeft(this.getSquare(), square, board) ||
                moveLeft(this.getSquare(), square, board) || moveUpLeft(this.getSquare(), square, board);

    }

//    private boolean moveLeft(Square square, Board board) {
//        for(int x = getSquare().getX(), y = getSquare().getY() - 1; y > 0; y--) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }
//
//    private boolean moveDown(Square square, Board board) {
//        for(int x = getSquare().getX() + 1, y = getSquare().getY(); x < 9; x++) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }
//
//    private boolean moveRight(Square square, Board board) {
//        for(int x = getSquare().getX(), y = getSquare().getY() + 1; y < 9; y++) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }
//
//    private boolean moveUp(Square square, Board board) {
//        for(int x = getSquare().getX() - 1, y = getSquare().getY(); x > 0; x--) {
//            if(Objects.equals(square, new Square(x, y))) return true;
//            else if (board.isPieceAt(new Square(x, y))) return false;
//        }
//        return false;
//    }

}
