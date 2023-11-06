import java.util.ArrayList;
import java.util.HashMap;

public abstract class Piece implements Movement {
    private final boolean isWhite;
    private Square square;

    public Piece(boolean isWhite, Square square) {
        this.isWhite = isWhite;
        this.square = square;
    }

    final boolean isWhite() {
        return isWhite;
    }

    final public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = new Square(square.getX(), square.getY());
    }

    @Override
    public boolean canMove(Square square, Board board) {
            return this.getSquare() != square &&
                    square.getX() > 0 && square.getX() < 9 &&
                    square.getY() > 0 && square.getY() < 9;
    }
    @Override
    public String toString() {
        return "Error";
    }

    final public short addColor(short temp) {
        return isWhite ? ++temp : temp;
    }
    final public short addVerticalPosition(short temp) {
        return (short) ((temp << 4) + square.getY());
    }

    final public short addHorizontalPosition(short temp) {
        return (short) ((temp << 4) + square.getX());
    }
    public short addType(short temp){
        switch (getClass().getName()) {
            case "Pawn" :
                return (short)(temp << 3);
            case "King" :
                return (short)((temp << 3) + 1);
            case "Queen" :
                return (short)((temp << 3) + 2);
            case "Rook" :
                return (short)((temp << 3) + 3);
            case "Bishop" :
                return (short)((temp << 3) + 4);
            case "Knight" :
                return (short)((temp << 3) + 5);
            default:
                throw new IllegalArgumentException();
        }
    }

    final public boolean canBeBeaten(Square square, Board board) {
        for(Square sq : board.getBoard().keySet()) {
            Piece temp = board.getBoard().get(sq);
            if(temp.isWhite() != isWhite() && temp.canBeat(square, board))
                return true;
        }
        return false;
    }
    public Board makeMove(Square square, Board b) {
        Board board = new Board(b);
        HashMap<Square, Piece> modifiedBoard = new HashMap<>(board.getBoard());
        board.setStepBackBoard(modifiedBoard);

        if(board.isPieceAt(square)) {
            Piece beaten = board.getBoard().get(square);
            beaten.setSquare(new Square(0, 0));
            if (board.getBoard().get(square).isWhite) {
                ArrayList<Piece> beatenWhite = board.getBeatenWhite();
                beatenWhite.add(beaten);
                board.setBeatenWhite(beatenWhite);
            } else {
                ArrayList<Piece> beatenBlack = board.getBeatenBlack();
                beatenBlack.add(beaten);
                board.setBeatenBlack(beatenBlack);
            }
        }
        modifiedBoard.remove(getSquare());
        setSquare(square);
        modifiedBoard.put(square, this);
        board.setBoard(modifiedBoard);
        return board;
    }

    public boolean getIsFirstMove() {
        return false;
    }
}
