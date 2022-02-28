package gt.edu.url.totito;

import gt.edu.url.adapter.Board;
import gt.edu.url.adapter.Piece;

public class Minimax {

    public static<Move> double minimax(Board<Move> board, boolean maximizing, Piece originalPlayer, int maxDepth) {
        if (board.isWin() || board.isDraw() || maxDepth == 0) {
            return board.evaluate(originalPlayer);
        }

        if (maximizing) {
            double v = Double.NEGATIVE_INFINITY;
            for (Move move: board.getLegalMoves()) {
                double result = minimax(board.move(move), false, originalPlayer, maxDepth-1);
                v = Math.max(v, result);
            }
            return v;
        } else {
            double v = Double.POSITIVE_INFINITY;
            for (Move move: board.getLegalMoves()) {
                double result = minimax(board.move(move), true, originalPlayer, maxDepth-1);
                v = Math.min(v, result);
            }
            return v;
        }
    }

    public static<Move> Move findBestMove(Board<Move> board, int maxDepth) {
        double bestValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        for (Move move: board.getLegalMoves()) {
            double result = minimax(board.move(move), false, board.getTurn(), maxDepth);
            if (result > bestValue) {
                bestValue = result;
                bestMove = move;
            }
        }
        return bestMove;
    }
}
