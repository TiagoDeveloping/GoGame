package dev.go.board;

import dev.go.game.player.Stone;
import lombok.Getter;

public class BoardAnalyser {
	
	private Board board;
	boolean filled[][];
	
	@Getter
	private Stone foundCapture;
	
	public BoardAnalyser(Board board) {
		filled = new boolean[Board.DIMENSION][Board.DIMENSION];
		this.board = board;
	}
	
	/**
	 * Checks if there's a winner
	 * 
	 * @param x of latest move
	 * @param y of latest move
	 * 
	 * @return true if there is a winner
	 */
	public boolean hasWinner(int x, int y) {
		boolean cc = hasCapture(x, y);
		if (cc) return cc;
		
		boolean c0 = hasCapture(x - 1, y);
		if (c0) return c0;
		
		boolean c1 = hasCapture(x + 1, y);
		if (c1) return c1;
		
		boolean c2 = hasCapture(x, y - 1);
		if (c2) return c2;

		boolean c3 = hasCapture(x, y + 1);
		if (c3) return c3;
		
		return false;
		
	}

	/**
	 * Check if there is a capture
	 * 
	 * @note will check if the stone at (x, y) is captured.
	 * 
	 * @param x of the board field coordinate
	 * @param y of the board field coordinate
	 * 
	 * @return true if the type of stone at (x, y) is captured starting to search from (x, y)
	 */
	public boolean hasCapture(int x, int y) {
		Board copy = this.board.deepCopy();	
		
		resetArrays();
		
		if (!board.inBoard(x, y)) return false; // edge case
		
		Stone stone = board.getField(x, y);
		
		boolean captured = flood(x, y, copy, stone);
		
		if (captured) this.foundCapture = stone;
		
		return captured;
	}
	
	/**
	 * @return true if there is capture
	 */
	private Boolean flood(int x, int y, Board board, Stone stone) {
		if (!board.inBoard(x, y)) return true;
		
		if (filled[x][y]) return null; // if alr checked/being checked, return null so that it wont count for evaluation or neighbouring fields
		
		filled[x][y] = true;
		
		if (board.getField(x, y).equals(Stone.NONE)) { // if is Nn there is no capturex
			return false;
		}
		
		// we know its not Nn, if its not the stone it should be there is a capture for this point. thus return true
		Stone field = board.getField(x, y);
		if (!field.equals(stone)) {
			return true;
		}
		
		// check all surrounding blocks
		Boolean left = flood(x - 1, y, board, stone);
		Boolean right = flood(x + 1, y, board, stone);
		Boolean bottom = flood(x, y + 1, board, stone);
		Boolean top = flood(x, y - 1, board, stone);

		// its true or the value of the Boolean, effectively ignoring the values that are null
		boolean out = (left == null || left) && 
		              (right == null || right) && 
		              (bottom == null || bottom) && 
		              (top == null || top);
		
		return out;
	}
	
	private void resetArrays() {
		filled = new boolean[Board.DIMENSION][Board.DIMENSION];
	}
	
}
