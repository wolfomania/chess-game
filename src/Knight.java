
public class Knight extends Piece{

    public Knight(boolean isWhite, Square square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if(isWhite())
            return "WN";
        else
            return "BN";
    }

    @Override
    public boolean canMove(Square square, Board board) {
        if(super.canMove(square, board)) {
                return checkSquare(square) && (!board.isPieceAt(square) || board.getBoard().get(square).isWhite() != isWhite());
        } else
            return false;
    }

    @Override
    public boolean canBeat(Square square, Board board) {
        return canMove(square, board);
    }

    //Check if a knight can move to that square
    private boolean checkSquare(Square square) {
        return moveUpLong(square) || moveDownLong(square) || moveUpShort(square) || moveDownShort(square);

    }
    private boolean moveUpLong(Square square) {
        return (square.getX() == this.getSquare().getX() - 2 &&
                (square.getY() == this.getSquare().getY() + 1 || square.getY() == this.getSquare().getY() - 1));
    }

    private boolean moveUpShort(Square square) {
        return (square.getX() == this.getSquare().getX() - 1 &&
                (square.getY() == this.getSquare().getY() + 2 || square.getY() == this.getSquare().getY() - 2));
    }

    private boolean moveDownLong(Square square) {
        return (square.getX() == this.getSquare().getX() + 2 &&
                (square.getY() == this.getSquare().getY() + 1 || square.getY() == this.getSquare().getY() - 1));
    }

    private boolean moveDownShort(Square square) {
        return (square.getX() == this.getSquare().getX() + 1 &&
                (square.getY() == this.getSquare().getY() + 2 || square.getY() == this.getSquare().getY() - 2));
    }
}
