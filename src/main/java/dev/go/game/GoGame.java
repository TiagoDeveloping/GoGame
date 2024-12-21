package dev.go.game;

import java.io.Serializable;

import de.go.game.listeners.HasWinnerListener;
import dev.go.board.Board;
import dev.go.board.BoardAnalyser;
import dev.go.board.Move;
import dev.go.board.ReadOnlyBoard;
import dev.go.exceptions.GameOverException;
import dev.go.exceptions.IllegalMoveException;
import dev.go.game.player.Player;
import dev.go.game.player.Stone;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GoGame implements Serializable {

	private static final long serialVersionUID = 7731181278339457278L;

	@Getter(value = AccessLevel.NONE)
	private final Board board = new Board();
	
	@Setter
	private HasWinnerListener hasWinnerListener;
	
	private Player player0;
	
	private Player player1;
	
	private transient BoardAnalyser analyzer;
	
	// first move is black
	private Stone turn = Stone.BLACK;
	
	private boolean isActive = true;
	
	private Stone winningStone = null;
	
	public GoGame(Player p0, Player p1) {
		this.player0 = p0;
		this.player1 = p1;
		
		this.analyzer = new BoardAnalyser(board);
	}
	
	/**
	 * Put the move into the board
	 * 
	 * @param move the move to execute
	 * 
	 * @throws IllegalMoveException if the move is not allowd
	 * @throws GameOverException if the game has ended
	 */
	public void doMove(Move move) throws IllegalMoveException, GameOverException {
		if (!isActive) throw new GameOverException("Game has ended");
		
		if (!isValidMove(move)) throw new IllegalMoveException("Move isn't allowed: " + move.toString());
		board.setField(move.x, move.y, turn);
		
		toggleTurn();
		
		analyseForWinner(move);
	}

	private void analyseForWinner(Move move) {
		boolean hasWinner = analyzer.hasWinner(move.x, move.y);
		if (hasWinner && (hasWinnerListener != null)) {
			isActive = false;
			Stone captured = analyzer.getFoundCapture();
			
			Stone winner;
			
			if (captured.equals(Stone.BLACK)) {
				winner = Stone.WHITE;
			} else {
				winner = Stone.BLACK;
			}
			
			winningStone = winner;
			hasWinnerListener.onWinner(getWinner());
		}
	}
	
	/**
	 * Get the winner of the game
	 * 
	 * @return <b>null</b> if no winner, otherwise the winner
	 */
	public Player getWinner() {
		if (winningStone == null) return null;
		
		if (player0.getStone().equals(winningStone)) {
			return player0;
		} else {
			return player1;
		}
	}
	
	/**
	 * Checks if move is valid
	 * 
	 * @param m move to be checked
	 * 
	 * @return true if move is in board and field ins't occupied
	 */
	public boolean isValidMove(Move m) {
		boolean inBoard = board.inBoard(m.x, m.y);
		if (!inBoard) return false; // cannot get field if outside of board
		
		boolean freeField = board.getField(m.x, m.y) == Stone.NONE;
		boolean correctStone = m.stone.equals(turn); // --> severe bad.. should throw smth
		return inBoard && freeField && correctStone;
	}
	
	public ReadOnlyBoard getReadBoard() {
		return board;
	}
	
	public String prettyPrint() {
		StringBuilder sb = new StringBuilder();
		sb.append("Turn: ").append(turn.toString()).append("\n\n");
		sb.append(board.prettyPrint()).append("\n");
		return sb.toString();
	}
	
	private void toggleTurn() {
		if (turn == Stone.BLACK) {
			turn = Stone.WHITE;
		} else {
			turn = Stone.BLACK;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("board=").append(board.toString()).append(",");
		sb.append("player0=").append(player0.toString()).append(",");
		sb.append("player1=").append(player1.toString()).append(",");
		sb.append("turn=").append(turn.toString());
		sb.append("}");
		
		return sb.toString();
	}
	
	public boolean equals(GoGame game) {
		return 
			this.board.equals(game.getReadBoard()) &&
			this.isActive == game.isActive &&
			this.turn == game.turn &&
			this.player0.equals(game.player0) &&
			this.player1.equals(game.player1) &&
//			this.hasWinnerListener.equals(game.hasWinnerListener) &&
			this.winningStone == game.winningStone;
	}
	
}
