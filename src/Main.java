import java.io.File;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nDo you want to start a new game or to load saved game? (Enter anything/Load)");
        String gameStart = scan.next();
        Board board = new Board();
        if(gameStart.equals("Load") || gameStart.equals("load")) {
            System.out.println("Please, provide a name for a binary file with the saved game: ");
            String fileName = scan.next();
            File file = new File(fileName);
            if(file.exists()) board.loadBoardFrom(fileName);
            else {
                while(!file.exists()) {
                    System.out.println("File is not found, please try to enter another name: ");
                    fileName = scan.next();
                    file = new File(fileName);
                }
            }
        }
        boolean whiteMove = true;
        System.out.println("**GAME IS STARTED**\n" +
                "Please, take note to move pieces on the board use following format:\n" +
                "Letters from A to H (uppercase) and number from 1 to 8.\nFor example:" +
                "A2 A4 - will move a pawn from A2 to A4. \nThat's it §(*￣▽￣*)§." +
                "\n**GOOD LUCK and HAVE FUN**");
        while(board.getGameStatus() == GameStatus.ONGOING) {

            board.printBoard();
            if(whiteMove)
                System.out.println("****WHITES TURN****");
            else System.out.println("****BLACKS TURN****");
            System.out.println("Please, make a move:");
            Square firstPos = Square.legalPosition(scan);

            if(firstPos.equals(new Square(0,0))){
                System.out.println("Confirm Draw");
                String answer = scan.next();
                if(answer.equals("Draw") || answer.equals("draw")) {
                    board.setGameStatus(GameStatus.FINISHED);
                }
                continue;
            } else if(firstPos.equals(new Square(9, 9))) {
                System.out.println("***GAME IS SAVED***\nDo you want to continue? (Yes/No(default))");
                String answer = scan.next();
                if(answer.equals("Yes") || answer.equals("yes"))
                    continue;
                else break;
            }

            Square secondPos = Square.legalPosition(scan);
            while(!board.isLegalMove(firstPos, secondPos, whiteMove)){
                System.out.println("Illegal move detected. Please try another one:");
                firstPos = Square.legalPosition(scan);
                secondPos = Square.legalPosition(scan);
            }
            board = board.getBoard().get(firstPos).makeMove(secondPos, board);
            board.checkForPromotion(secondPos, scan);
            board.isCheckMate();
            whiteMove = !whiteMove;
        }
    }

}
