import java.util.ArrayList;
import java.util.HashMap;

public class Pawn extends Piece{

    public Pawn(boolean isWhite, Square square) {
        super(isWhite, square);
    }


    @Override
    public boolean canMove(Square square, Board board) {
        if(super.canMove(square, board)) {
            if (isWhite()) {
                if (isFirstMove(board)) {
                    return this.getSquare().getY() == square.getY() && this.getSquare().getX() + 2 >= square.getX()
                            && !board.isPieceAt(new Square(getSquare().getX() + 1, getSquare().getY()))
                            && !board.isPieceAt(new Square(getSquare().getX() + 2, getSquare().getY()));
                } else {
                    return this.getSquare().getY() == square.getY() && this.getSquare().getX() + 1 == square.getX() && !board.isPieceAt(new Square(getSquare().getX() + 1, getSquare().getY()));
                }
            } else { //for black pawns
                if (isFirstMove(board)) {
                    return this.getSquare().getY() == square.getY() && this.getSquare().getX() - 2 <= square.getX()
                            && !board.isPieceAt(new Square(getSquare().getX() - 1, getSquare().getY()))
                            && !board.isPieceAt(new Square(getSquare().getX() - 2, getSquare().getY()));
                } else {
                    return this.getSquare().getY() == square.getY() && this.getSquare().getX() - 1 == square.getX()
                            && !board.isPieceAt(new Square(getSquare().getX() - 1, square.getY()));
                }
            }
        } else
            return false;
    }

    final boolean isFirstMove(Board board) {
        if(isWhite())
            return getSquare().getX() == 2;
        else return getSquare().getX() == 7;
    }

    @Override
    public boolean canBeat(Square square, Board board) {
        if(super.canMove(square, board) && board.isPieceAt(square)) {
            if (isWhite()) {
                if(!board.getBoard().get(new Square(square.getX(), square.getY())).isWhite() && square.getX() == this.getSquare().getX() + 1) {
                    if(square.getY() == this.getSquare().getY() + 1)
                        return true;
                    else return square.getY() == this.getSquare().getY() - 1;
                } else return false;
            } else {
                if(board.getBoard().get(new Square(square.getX(), square.getY())).isWhite() && square.getX() == this.getSquare().getX() - 1) {
                    if(square.getY() == this.getSquare().getY() + 1)
                        return true;
                    else return square.getY() == this.getSquare().getY() - 1;
                } else return false;
            }
        } else return canEnPassant(square, board);
    }

    public boolean canEnPassant(Square square, Board board) {
        if(super.canMove(square, board) && !board.isPieceAt(square)) {
            if(isWhite()) {
                if(board.isPieceAt(new Square(square.getX() - 1, square.getY()))) {
                    Piece temp = board.getBoard().get(new Square(square.getX() - 1, square.getY()));
                    if(!temp.isWhite() && temp.getClass().getName().equals("Pawn")) {
                        if(board.wasPieceAt(new Square(square.getX() + 1, square.getY())))
                            return board.getStepBackBoard().get(new Square(square.getX() + 1, square.getY())).getClass().getName().equals("Pawn");
                    }
                }
            } else {
                if(board.isPieceAt(new Square(square.getX() + 1, square.getY()))) {
                    Piece temp = board.getBoard().get(new Square(square.getX() + 1, square.getY()));
                    if(temp.isWhite() && temp.getClass().getName().equals("Pawn")) {
                        if(board.wasPieceAt(new Square(square.getX() - 1, square.getY())))
                            return board.getStepBackBoard().get(new Square(square.getX() - 1, square.getY())).getClass().getName().equals("Pawn");
                    }

                }
            }
            return false;
        }
        return false;
    }

    @Override
    public Board makeMove(Square square, Board board) {
        if(canEnPassant(square, board)){
            HashMap<Square, Piece> modifiedBoard = new HashMap<>(board.getBoard());
            board.setStepBackBoard(board.getBoard());
            if(isWhite()) {
                Piece beaten = board.getBoard().get(new Square(square.getX() - 1, square.getY()));
                beaten.setSquare(new Square(0, 0));
                ArrayList<Piece> beatenWhite = board.getBeatenWhite();
                beatenWhite.add(beaten);
                board.setBeatenWhite(beatenWhite);
            } else {
                Piece beaten = board.getBoard().get(new Square(square.getX() + 1, square.getY()));
                beaten.setSquare(new Square(0, 0));
                ArrayList<Piece> beatenBlack = board.getBeatenBlack();
                beatenBlack.add(beaten);
                board.setBeatenBlack(beatenBlack);
            }
            modifiedBoard.remove(getSquare());
            setSquare(square);
            modifiedBoard.put(square, this);
            board.setBoard(modifiedBoard);
            return new Board(board);
        } else return super.makeMove(square, board);
//        if(board.isPieceAt(square)) {
//            Piece beaten = board.getBoard().get(square);
//            beaten.setSquare(new Square(0, 0));
//            if (board.getBoard().get(square).isWhite()) {
//                ArrayList<Piece> beatenWhite = board.getBeatenWhite();
//                beatenWhite.add(beaten);
//                board.setBeatenWhite(beatenWhite);
//            } else {
//                ArrayList<Piece> beatenBlack = board.getBeatenBlack();
//                beatenBlack.add(beaten);
//                board.setBeatenBlack(beatenBlack);
//            }
//        }
//        board.setStepBackBoard(board.getBoard());
//        modifiedBoard.put(square, board.getBoard().get(getSquare()));
//        modifiedBoard.remove(getSquare());
//        board.setBoard(modifiedBoard);
//        setSquare(square);
//        return board;
    }

    @Override
    public String toString() {
        if(isWhite())
            return "WP";
        else
            return "BP";
    }
}
