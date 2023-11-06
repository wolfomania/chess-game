import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


public class Board{

    public static final Square[][] squares = new Square[][]{
        {new Square(1, 1), new Square(1, 2), new Square(1, 3), new Square(1, 4), new Square(1, 5), new Square(1, 6), new Square(1, 7), new Square(1, 8)},
        {new Square(2, 1), new Square(2, 2), new Square(2, 3), new Square(2, 4), new Square(2, 5), new Square(2, 6), new Square(2, 7), new Square(2, 8)},
        {new Square(3, 1), new Square(3, 2), new Square(3, 3), new Square(3, 4), new Square(3, 5), new Square(3, 6), new Square(3, 7), new Square(3, 8)},
        {new Square(4, 1), new Square(4, 2), new Square(4, 3), new Square(4, 4), new Square(4, 5), new Square(4, 6), new Square(4, 7), new Square(4, 8)},
        {new Square(5, 1), new Square(5, 2), new Square(5, 3), new Square(5, 4), new Square(5, 5), new Square(5, 6), new Square(5, 7), new Square(5, 8)},
        {new Square(6, 1), new Square(6, 2), new Square(6, 3), new Square(6, 4), new Square(6, 5), new Square(6, 6), new Square(6, 7), new Square(6, 8)},
        {new Square(7, 1), new Square(7, 2), new Square(7, 3), new Square(7, 4), new Square(7, 5), new Square(7, 6), new Square(7, 7), new Square(7, 8)},
        {new Square(8, 1), new Square(8, 2), new Square(8, 3), new Square(8, 4), new Square(8, 5), new Square(8, 6), new Square(8, 7), new Square(8, 8)}
    };
    private HashMap<Square, Piece> board;
    private HashMap<Square, Piece> stepBackBoard;
    private ArrayList<Piece> beatenWhite;
    private ArrayList<Piece> beatenBlack;
    private GameStatus gameStatus;
    private Square whiteKing;
    private Square blackKing;

    public Board() {
//        createSquares();
        board = new HashMap<>();
        beatenWhite = new ArrayList<>();
        beatenBlack = new ArrayList<>();
        resetBoard();
        gameStatus = GameStatus.ONGOING;
        stepBackBoard = new HashMap<>();
        stepBackBoard.putAll(board);
    }

    public Board(Board identical) {
        board = new HashMap<>(identical.board);
        stepBackBoard = new HashMap<>(identical.stepBackBoard);
        beatenWhite = new ArrayList<>(identical.beatenWhite);
        beatenBlack = new ArrayList<>(identical.beatenBlack);
        gameStatus = identical.gameStatus;
        whiteKing = identical.whiteKing;
        blackKing = identical.blackKing;
    }

    public HashMap<Square, Piece> getStepBackBoard() {
        return stepBackBoard;
    }

    public void setStepBackBoard(HashMap<Square, Piece> map) {
        stepBackBoard = new HashMap<>(map);
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setBoard(HashMap<Square, Piece> hm) {
        board = new HashMap<>(hm);
    }

    public void setBeatenWhite(ArrayList<Piece> beatenWhite) {
        this.beatenWhite = beatenWhite;
    }

    public void setWhiteKing(Square whiteKing) {
        this.whiteKing = whiteKing;
    }

    public void setBlackKing(Square blackKing) {
        this.blackKing = blackKing;
    }

    public void setBeatenBlack(ArrayList<Piece> beatenBlack) {
        this.beatenBlack = beatenBlack;
    }

    public ArrayList<Piece> getBeatenWhite() {
        return beatenWhite;
    }

    public ArrayList<Piece> getBeatenBlack() {
        return beatenBlack;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
//
//    private void createSquares() {
//        for(int i = 0; i < 8; i++)
//            for (int j = 0; j < 8; j++)
//                squares[i][j] = new Square(i + 1, j + 1);
//    }

    public void resetBoard() {

        for(int i = 0; i < 8; i++) {
            board.put(squares[1][i], new Pawn(true, squares[1][i]));
            board.put(squares[6][i], new Pawn(false,  squares[6][i]));
        }
        setPieces(true);
        setPieces(false);
        whiteKing = new Square(1, 5);
        blackKing = new Square(8, 5);
    }

    private void setPieces(boolean isWhite){

        int piecesRow = isWhite ? 0 : 7;

        board.put(squares[piecesRow][0], new Rook(isWhite, true, squares[piecesRow][0]));
        board.put(squares[piecesRow][1], new Knight(isWhite, squares[piecesRow][1]));
        board.put(squares[piecesRow][2], new Bishop(isWhite, squares[piecesRow][2]));
        board.put(squares[piecesRow][3], new Queen(isWhite, squares[piecesRow][3]));
        board.put(squares[piecesRow][4], new King(isWhite, true, squares[piecesRow][4]));
        board.put(squares[piecesRow][5], new Bishop(isWhite, squares[piecesRow][5]));
        board.put(squares[piecesRow][6], new Knight(isWhite, squares[piecesRow][6]));
        board.put(squares[piecesRow][7], new Rook(isWhite, true, squares[piecesRow][7]));
    }

    public HashMap<Square, Piece> getBoard() {
        return board;
    }

    public void printBoard() {
        char[] temp = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for(Piece beaten : beatenWhite)
            System.out.print(beaten + " ");
        System.out.println();
        for(int i = 7; i >= 0; i--){
            System.out.printf("%2s", (1 + i));
            for(int j = 0; j < 8; j++) {
                if(board.get(squares[i][j]) == null) {
                    if((i + j) % 2 == 1)
                        System.out.printf("%3s", "");
                    else System.out.printf("%3s", "##");
                } else System.out.printf("%3s", board.get(squares[i][j]));
            }
            System.out.println();
        }
        System.out.print(" ");
        for(char c: temp)
            System.out.printf("%3s", c);
        System.out.println();
        for(Piece beaten : beatenBlack)
            System.out.print(beaten + " ");
        System.out.println();
    }


    public boolean isPieceAt(Square square){
        return board.get(square) != null;
    }
    public boolean wasPieceAt(Square square){
        return stepBackBoard.get(square) != null;
    }

    public void saveBoardTo(String fileName) {
        try(DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            for(Square sq : board.keySet()) {
                short t = 0;
                Piece temp = board.get(sq);
                t = temp.addColor(t);
                t = temp.addVerticalPosition(t);
                t = temp.addHorizontalPosition(t);
                t = temp.addType(t);
                outputStream.writeShort(t);
            }
            saveBeatenPieces(beatenBlack, outputStream);
            saveBeatenPieces(beatenWhite, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBoardFrom(String fileName) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))){
            board = new HashMap<>();
            for(int i = 0; i < 32; i++) {
                short t = inputStream.readShort();
                boolean isWhite = getColor(t);
                int x = getHorizontalPosition(t);
                int y = getVerticalPosition(t);
                Piece temp = new Pawn(true, null);
                switch (getType(t)) {
                    case 0:
                        temp = new Pawn(isWhite, new Square(x, y));
                        break;
                    case 1:
                        temp = new King(isWhite, new Square(x, y));
                        break;
                    case 2:
                        temp = new Queen(isWhite, new Square(x, y));
                        break;
                    case 3:
                        temp = new Rook(isWhite, new Square(x, y));
                        break;
                    case 4:
                        temp = new Bishop(isWhite, new Square(x, y));
                        break;
                    case 5:
                        temp = new Knight(isWhite, new Square(x, y));
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                if(Objects.equals(temp.getSquare(), new Square(0, 0))){
                    if(temp.isWhite())
                        beatenWhite.add(temp);
                    else beatenBlack.add(temp);
                }
                else board.put(temp.getSquare(), temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveBeatenPieces(ArrayList<Piece> list, DataOutputStream outputStream) throws IOException {
        for(Piece temp: list) {
            short t = 0;
            t = temp.addColor(t);
            t = temp.addVerticalPosition(t);
            t = temp.addHorizontalPosition(t);
            t = temp.addType(t);
            outputStream.writeShort(t);
        }
    }
    private boolean getColor(short temp) {
        return (temp >>> 11) == 1;
    }
    private int getVerticalPosition(short temp) {
        return (temp >>> 7) & 0xF;
    }
    private int getHorizontalPosition(short temp) {
        return (temp >>> 3) & 0xF;
    }
    private int getType(short temp) {
        return temp & 7;
    }

    public boolean isLegalMove(Square firstPos, Square secondPos, boolean whiteMove) {
        if(isPieceAt(firstPos)){
            if(board.get(firstPos).isWhite() == whiteMove) {
                if (board.get(firstPos).canBeat(secondPos, this) || board.get(firstPos).canMove(secondPos, this)) {
                    if (isWhiteCheck()) {
                        Board nextStepBoard = board.get(firstPos).makeMove(secondPos, this);
                        return !nextStepBoard.isWhiteCheck();
                    } else if (isBlackCheck()) {
                        Board nextStepBoard = board.get(firstPos).makeMove(secondPos, this);
                        return !nextStepBoard.isBlackCheck();
                    }
                    Board nextStepBoard = board.get(firstPos).makeMove(secondPos, this);
                    if(whiteMove) {
                        return !nextStepBoard.isWhiteCheck();
                    } else {
                        return !nextStepBoard.isBlackCheck();
                    }
//
//                //If move is made by king and check itself, then it is illegal (already implemented -> check king canMove)
//                Board nextStepBoard = board.get(firstPos).makeMove(firstPos, this);
//                if(whiteMove) {
//                    return !nextStepBoard.isWhiteCheck();
//                } else return !nextStepBoard.isBlackCheck();
                }
            } else System.out.println("**PIECE OF THE WRONG COLOR WAS SELECTED**");
        }
        return false;
    }

    private boolean isWhiteCheck() {
        if(isPieceAt(whiteKing))
            return board.get(whiteKing).canBeBeaten(whiteKing, this);
        return false;
    }
    private boolean isBlackCheck() {
        if(isPieceAt(blackKing))
            return board.get(blackKing).canBeBeaten(blackKing, this);
        return false;
    }

    public void isCheckMate() {
        if(isWhiteCheck()) {
            if(!kingCanMakeMove(whiteKing) && !nextSaveStepIsPossible(whiteKing)) {
                gameStatus = GameStatus.FINISHED;
                printBoard();
                System.out.println("****GAME IS FINISHED**\n****BLACK WON****");
            } else System.out.println("**WHITE KING IS CHECKED**");
        } else if (isBlackCheck()) {
            if(!kingCanMakeMove(blackKing) && !nextSaveStepIsPossible(blackKing)) {
                gameStatus = GameStatus.FINISHED;
                printBoard();
                System.out.println("****GAME IS FINISHED**\n****WHITE WON****");
            } else System.out.println("**BLACK KING IS CHECKED**");
        }
    }

    private boolean nextSaveStepIsPossible(Square king) {
        if(isPieceAt(king)) {
            Square toBlock = new Square(0, 0);
            for (Square sq : board.keySet()) {
                Piece temp = board.get(sq);
                if (temp.isWhite() != board.get(king).isWhite() && temp.canBeat(king, this))
                    toBlock = temp.getSquare();
            }
//            for(Square sq : board.keySet()) {
//                Piece temp = board.get(sq);
//                if(temp.isWhite() == board.get(king).isWhite() && temp.canBeat(toBlock,this))
//                    return true;
//            }return canMoveInBetween(king, toBlock);
            if(salvation(king, toBlock) || canMoveInBetween(king, toBlock)) {
                return true;
            }
        }
        return false;
    }

    private boolean canMoveInBetween(Square king, Square toBlock) {
        if(isPieceAt(toBlock) && isPieceAt(king)) {
            switch (board.get(toBlock).getClass().getName()) {
                case "Rook" :
                    return canMoveInBetweenRook(king, toBlock);
                case "Bishop" :
                    return canMoveInBetweenBishop(king, toBlock);
                case "Queen" :
                    return canMoveInBetweenRook(king, toBlock) || canMoveInBetweenBishop(king, toBlock);
                default:
                    return false;
            }
        }
        return false;
    }

    private boolean canMoveInBetweenBishop(Square king, Square toBlock) {
        ArrayList<Square> road = new ArrayList<>();
        if(isPieceAt(toBlock) && isPieceAt(king)) {
            if(king.getX() > toBlock.getX() + 1 && king.getY() > toBlock.getY() + 1) {
                for(int x = toBlock.getX(), y = toBlock.getY(); x != king.getX() || y != king.getY(); x++, y++) {
                    road.add(new Square(x, y));
                }
            } else if (king.getX() > toBlock.getX() + 1 && king.getY() < toBlock.getY() - 1) {
                for(int x = toBlock.getX(), y = toBlock.getY(); x != king.getX() || y != king.getY(); x++, y--) {
                    road.add(new Square(x, y));
                }
            } else if (king.getX() < toBlock.getX() - 1 && king.getY() < toBlock.getY() - 1) {
                for(int x = toBlock.getX(), y = toBlock.getY(); x != king.getX() || y != king.getY(); x--, y--) {
                    road.add(new Square(x, y));
                }
            } else if (king.getX() < toBlock.getX() - 1 && king.getY() > toBlock.getY() + 1) {
                for(int x = toBlock.getX(), y = toBlock.getY(); x != king.getX() || y != king.getY(); x--, y++) {
                    road.add(new Square(x, y));
                }
            } else return false;
        } else return false;
        for(Square sq : road) {
            if(salvation(king, sq))
                return true;
        }
        return false;
    }

    private boolean canMoveInBetweenRook(Square king, Square toBlock) {
        ArrayList<Square> road = new ArrayList<>();
        if(isPieceAt(toBlock) && isPieceAt(king)) {
            if(king.getX() > toBlock.getX() + 1 && king.getY() == toBlock.getY()) {
                for(int x = toBlock.getX(); x != king.getX(); x++) {
                    road.add(new Square(x, king.getY()));
                }
            } else if (king.getX() < toBlock.getX() - 1 && king.getY() == toBlock.getY()){
                for(int x = toBlock.getX(); x != king.getX(); x--) {
                    road.add(new Square(x, king.getY()));
                }
            } else if (king.getY() > toBlock.getY() + 1 && king.getX() == toBlock.getX()) {
                for(int y = toBlock.getY(); y != king.getX(); y++) {
                    road.add(new Square(king.getX(), y));
                }
            } else if (king.getY() < toBlock.getY() - 1 && king.getX() == toBlock.getX()) {
                for(int y = toBlock.getY(); y != king.getX(); y--) {
                    road.add(new Square(king.getX(), y));
                }
            } else return false;
        } else return false;
        for(Square sq : road) {
            if(salvation(king, sq))
                return true;
        }
        return false;
    }

    private boolean kingCanMakeMove(Square king) {
        if(isPieceAt(king)) {
            Piece temp = board.get(king);
            for(int x = temp.getSquare().getX() - 1; x <= temp.getSquare().getX() + 1; x++) {
                for(int y = temp.getSquare().getY() - 1; y <= temp.getSquare().getY() + 1; y++) {
                    if(temp.canBeat(new Square(x, y), this))
                        return true;
                }
            }
            return temp.canBeat(new Square(temp.getSquare().getX(), temp.getSquare().getY() + 2), this) ||
            temp.canBeat(new Square(temp.getSquare().getX(), temp.getSquare().getY() - 2), this);
        }
        return false;
    }

    private boolean salvation(Square king, Square toBeat) {
        for(Square sq : board.keySet()) {
            Piece temp = board.get(sq);
//            temp.isWhite() == board.get(king).isWhite() && temp.canBeat(toBeat, this) // (temp.canBeat(toBeat, this) || temp.canMove(toBeat, this)) // isLegalMove(sq, toBeat, board.get(king).isWhite())
            if(temp.isWhite() == board.get(king).isWhite() && (temp.canBeat(toBeat, this) || temp.canMove(toBeat, this)))
                return true;
        }
        return false;
    }

    public void checkForPromotion(Square secondPos, Scanner scanner) {
        if(isPieceAt(secondPos) && board.get(secondPos) instanceof Pawn) {
            int raw = board.get(secondPos).isWhite() ? 8 : 1;
            if(board.get(secondPos).getSquare().getX() == raw) {
                System.out.println("**Choose a piece you want to promote to:**");
                String type = scanner.next();
                while (!(type.equals("Knight") || type.equals("Queen") || type.equals("Bishop") ||type.equals("Rook"))) {
                    System.out.println("No such piece or you can't promote to it. Try another one.");
                    type = scanner.next();
                }
                switch (type) {
                    case "Knight" :
                        board.put(secondPos, new Knight(board.get(secondPos).isWhite(), secondPos));
                        break;
                    case "Bishop" :
                        board.put(secondPos, new Bishop(board.get(secondPos).isWhite(), secondPos));
                        break;
                    case "Rook" :
                        board.put(secondPos, new Rook(board.get(secondPos).isWhite(), secondPos));
                        break;
                    case "Queen" :
                        board.put(secondPos, new Queen(board.get(secondPos).isWhite(), secondPos));
                        break;
                    default:
                        break;
                }
                System.out.println("Your pawn at " + secondPos + "was promoted to" + type);
            }
        }
    }
}
