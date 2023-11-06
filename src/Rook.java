
public class Rook extends Piece implements RookMovement{

    boolean isFirstMove;
    public Rook(boolean isWhite, Square square) {
        super(isWhite, square);
        isFirstMove = false;
    }

    public Rook(boolean isWhite, boolean isFirstMove, Square square) {
        super(isWhite, square);
        this.isFirstMove = isFirstMove;
    }

    @Override
    public String toString() {
        if(isWhite())
            return "WR";
        else
            return "BR";
    }

    @Override
    public boolean canMove(Square square, Board board) {
        if(super.canMove(square, board)) {
            return moveAround(square, board) && (!board.isPieceAt(square) || board.getBoard().get(square).isWhite() != isWhite());
        } else
            return false;
    }

    @Override
    public Board makeMove(Square square, Board board) {
        isFirstMove = false;
        return super.makeMove(square, board);
    }

    @Override
    public boolean getIsFirstMove() {
        return isFirstMove;
    }
    @Override
    public boolean canBeat(Square square, Board board) {
        return canMove(square, board);
    }

    private boolean moveAround(Square square, Board board) {
        return moveUp(this.getSquare(), square, board) || moveRight(this.getSquare(), square, board) ||
                moveDown(this.getSquare(), square, board) || moveLeft(this.getSquare(), square, board);

    }
}
