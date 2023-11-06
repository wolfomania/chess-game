public interface Movement {
    boolean canMove(Square square, Board board);

    boolean canBeat(Square square, Board board);

}
