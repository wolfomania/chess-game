import java.util.Objects;

public class King extends Piece{

    boolean isFirstMove;
    public King(boolean isWhite, Square square) {
        super(isWhite, square);
        isFirstMove = false;
    }

    public King(boolean isWhite, boolean isFirstMove, Square square) {
        super(isWhite, square);
        this.isFirstMove = isFirstMove;
    }

    @Override
    public String toString() {
        if(isWhite())
            return "WK";
        else
            return "BK";
    }

    @Override
    public boolean canMove(Square square, Board board) {
        if(super.canMove(square, board)) {
            if (moveAround(square) &&
                    (!board.isPieceAt(square) || board.getBoard().get(square).isWhite() != isWhite()) &&
                    !canBeBeaten(square, board)) {
                return true;
            } else if(canKingSideCastle(square, board)) {
                return true;
            } else return canQueenSideCastle(square, board);
        }
        return false;
    }

    private boolean canKingSideCastle(Square square, Board board) {
        if(isFirstMove) {
            if(board.isPieceAt(new Square(getSquare().getX(), getSquare().getY() + 3))) {
                Piece temp = board.getBoard().get(new Square(getSquare().getX(), getSquare().getY() + 3));
                if(temp instanceof  Rook && temp.getIsFirstMove()) {
                    if(getSquare().getX() == square.getX() && getSquare().getY() + 2 == square.getY())
                        if(!board.isPieceAt(new Square(square.getX(), square.getY())) &&
                                !board.isPieceAt(new Square(square.getX(), square.getY() - 1))) {
                            return !canBeBeaten(square, board) && !canBeBeaten(new Square(getSquare().getX(), getSquare().getY() + 1), board);
                        }
                }
            }
        }
        return false;
    }

    private boolean canQueenSideCastle(Square square, Board board) {
        if(isFirstMove) {
            if(board.isPieceAt(new Square(getSquare().getX(), getSquare().getY() - 4))) {
                Piece temp = board.getBoard().get(new Square(getSquare().getX(), getSquare().getY() - 4));
                if(temp instanceof  Rook && temp.getIsFirstMove()) {
                    if(getSquare().getX() == square.getX() && getSquare().getY() - 2 == square.getY())
                        if(!board.isPieceAt(new Square(square.getX(), square.getY())) &&
                                !board.isPieceAt(new Square(square.getX(), square.getY() - 1)) &&
                                    !board.isPieceAt(new Square(square.getX(), square.getY() + 1))) {
                            return !canBeBeaten(square, board) &&
                                    !canBeBeaten(new Square(getSquare().getX(), getSquare().getY() + 1), board);
                        }
                }
            }
        }
        return false;
    }

    @Override
    public Board makeMove(Square square, Board board) {
        if(canKingSideCastle(square, board)) {
            board = board.getBoard().get(new Square(getSquare().getX(), getSquare().getY() + 3)).makeMove(new Square(getSquare().getX(), getSquare().getY() + 1), board);
            board = super.makeMove(square, board);
            isFirstMove = false;
            if(isWhite())
                board.setWhiteKing(square);
            else board.setBlackKing(square);
            return new Board(board);
        } else if (canQueenSideCastle(square, board)) {
            board = board.getBoard().get(new Square(getSquare().getX(), getSquare().getY() - 4)).makeMove(new Square(getSquare().getX(), getSquare().getY() - 1), board);
            board = super.makeMove(square, board);
            isFirstMove = false;
            if(isWhite())
                board.setWhiteKing(square);
            else board.setBlackKing(square);
            return new Board(board);
        }
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

    private boolean moveAround(Square square) {
        for(int x = getSquare().getX() - 1; x <= getSquare().getX() + 1; x++) {
            for(int y = getSquare().getY() - 1; y <= getSquare().getY() + 1; y++) {
                if(Objects.equals(square, new Square(x, y))) return true;
            }
        }
        return false;
    }
}
