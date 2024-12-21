package dev.go.board;

import java.io.Serializable;

import dev.go.game.player.Stone;

/**
 * @author tiago
 */
public class Board implements ReadOnlyBoard, Serializable {

	private static final long serialVersionUID = -8825414111273052962L;

	public static final int DIMENSION = 7;

	/*
	 * (0, 0) (0, 1) (0, 2) (0, 3) (0, 4) (0, 5) (0, 6)
	 * (1, 0) (1, 1) (1, 2) (1, 3) (1, 4) (1, 5) (1, 6)
	 * (2, 0) (2, 1) (2, 2) (2, 3) (2, 4) (2, 5) (2, 6)
	 * (3, 0) (3, 1) (3, 2) (3, 3) (3, 4) (3, 5) (3, 6)
	 * (4, 0) (4, 1) (4, 2) (4, 3) (4, 4) (4, 5) (4, 6)
	 * (5, 0) (5, 1) (5, 2) (5, 3) (5, 4) (5, 5) (5, 6)
	 * (6, 0) (6, 1) (6, 2) (6, 3) (6, 4) (6, 5) (6, 6)
	 * 
	 * The board only cares about where which stone is, not who put it there or whatever,
	 * so the board just has knows which stone is where. 
	 */
	private Stone[][] board; // [x][y]

	public Board() {
		board = new Stone[DIMENSION][DIMENSION];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = Stone.NONE;
			}
		}
	}
	
	private Board(Stone[][] toCopy) {
		board = new Stone[DIMENSION][DIMENSION];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = toCopy[x][y];
			}
		}
	}
	
	public void setField(int x, int y, Stone type) throws ArrayIndexOutOfBoundsException {
		try {
			board[x][y] = type;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("Coordinate outside of board: (" + x + "; " + y + ")");
		}
		
	}
	
	public Stone getField(int x, int y) throws ArrayIndexOutOfBoundsException {
		try {
			return board[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("Coordinate outside of board: (" + x + "; " + y + ")");
		}
	}
	
	public boolean inBoard(int x, int y) {
		boolean xInside = x >= 0 && x < DIMENSION;
		boolean yInside = y >= 0 && y < DIMENSION;
		return xInside && yInside;
	}
	
	public Board deepCopy() {
		return new Board(board);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{[");
		for (int x = 0; x < board.length; x++) {
			sb.append("[");
			for (int y = 0; y < board[x].length; y++) {
				if (getField(x, y).equals(Stone.BLACK)) {
					sb.append(Stone.BLACK.toString());
					sb.append(",");
				} else if (getField(x, y).equals(Stone.WHITE)) {
					sb.append(Stone.WHITE.toString());
					sb.append(",");
				} else {
					sb.append(Stone.NONE.toString());
					sb.append(",");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("],");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		return sb.toString();
	}
	
	public String prettyPrint() {
		StringBuilder sb = new StringBuilder();
		
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (getField(x, y).equals(Stone.BLACK)) {
					sb.append("O ");
				} else if (getField(x, y).equals(Stone.WHITE)) {
					sb.append("X ");
				} else {
					sb.append("· ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Board)) return false;
		Board board = (Board) obj;
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				if (board.getField(i, j) != this.getField(i, j)) return false;
			}
		}
		return true;
	}

}
