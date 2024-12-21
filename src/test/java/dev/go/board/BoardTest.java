package dev.go.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.go.game.player.Stone;

public class BoardTest {
	
	private Board board;
	
	@BeforeEach
	void init() {
		board = new Board();
	}

	@Test
	public void boardInitializationTest() {
		for (int x = 0; x < Board.DIMENSION; x++) {
			for (int y = 0; y < Board.DIMENSION; y++) {
				assertEquals(board.getField(x, y), Stone.NONE);
			}
		}
	}
	
	@Test
	public void boardGetExceptionTest() {
		assertThrows(IndexOutOfBoundsException.class, () -> board.getField(0, -1));
		assertThrows(IndexOutOfBoundsException.class, () -> board.getField(-1, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> board.getField(-1, -1));
		assertThrows(IndexOutOfBoundsException.class, () -> board.getField(Board.DIMENSION + 1, Board.DIMENSION));
		assertThrows(IndexOutOfBoundsException.class, () -> board.getField(Board.DIMENSION, Board.DIMENSION + 1));
		assertThrows(IndexOutOfBoundsException.class, () -> board.getField(Board.DIMENSION + 1, Board.DIMENSION + 1));
	}
	
	@Test
	public void boardSetExceptionTest() {
		assertThrows(IndexOutOfBoundsException.class, () -> board.setField(0, -1, Stone.WHITE));
		assertThrows(IndexOutOfBoundsException.class, () -> board.setField(-1, 0, Stone.WHITE));
		assertThrows(IndexOutOfBoundsException.class, () -> board.setField(-1, -1, Stone.WHITE));
		assertThrows(IndexOutOfBoundsException.class, () -> board.setField(Board.DIMENSION + 1, Board.DIMENSION, Stone.WHITE));
		assertThrows(IndexOutOfBoundsException.class, () -> board.setField(Board.DIMENSION, Board.DIMENSION + 1, Stone.WHITE));
		assertThrows(IndexOutOfBoundsException.class, () -> board.setField(Board.DIMENSION + 1, Board.DIMENSION + 1, Stone.WHITE));
	}
	
}
