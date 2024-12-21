package dev.go.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.go.game.player.Stone;

public class BoardAnalyzerTest {
//TODO make tests for board analyzer
	
	Board board;
	BoardAnalyser analyser;
	
	@BeforeEach
	void init() {
		board = new Board();
		analyser = new BoardAnalyser(board);
	}
	
	@Test
	void middleLCapture() {
		board.setField(3, 3, Stone.BLACK);
	    board.setField(4, 3, Stone.BLACK);
	    board.setField(3, 4, Stone.BLACK);

	    board.setField(2, 3, Stone.WHITE);
	    board.setField(3, 2, Stone.WHITE);
	    board.setField(4, 2, Stone.WHITE);
	    board.setField(5, 3, Stone.WHITE);
	    board.setField(4, 4, Stone.WHITE);
	    board.setField(3, 5, Stone.WHITE);
	    board.setField(2, 4, Stone.WHITE);
	    
	    System.out.println(board.prettyPrint());

	    assertTrue(analyser.hasCapture(3, 3));
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);
	    
	    assertTrue(analyser.hasCapture(4, 3));
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);
	    
	    assertTrue(analyser.hasCapture(3, 4));
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);
	    
	    assertFalse(analyser.hasCapture(0, 0));
	    assertFalse(analyser.hasCapture(2, 3));
	    assertFalse(analyser.hasCapture(3, 2));
	    assertFalse(analyser.hasCapture(4, 2));
	    assertFalse(analyser.hasCapture(5, 3));
	    assertFalse(analyser.hasCapture(4, 4));
	    assertFalse(analyser.hasCapture(3, 5));
	    assertFalse(analyser.hasCapture(2, 4));
	}
	
	@Test
	void verticalLineCapture() {
	    board.setField(3, 3, Stone.BLACK);
	    board.setField(3, 4, Stone.BLACK);
	    board.setField(3, 5, Stone.BLACK);

	    board.setField(2, 3, Stone.WHITE);
	    board.setField(2, 4, Stone.WHITE);
	    board.setField(2, 5, Stone.WHITE);
	    board.setField(4, 3, Stone.WHITE);
	    board.setField(4, 4, Stone.WHITE);
	    board.setField(4, 5, Stone.WHITE);
	    
	    board.setField(3, 2, Stone.WHITE);
	    board.setField(3, 6, Stone.WHITE);
	    
	    System.out.println(board.prettyPrint());

	    assertTrue(analyser.hasCapture(3, 3));
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);
	    
	    assertTrue(analyser.hasCapture(3, 4));
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);
	    
	    assertTrue(analyser.hasCapture(3, 5));
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);

	    assertFalse(analyser.hasCapture(2, 3));
	    assertFalse(analyser.hasCapture(4, 3));
	    assertFalse(analyser.hasCapture(3, 2));
	    assertFalse(analyser.hasCapture(3, 6));
	}
	
	@Test
	void tShapeCapture() {
	    board.setField(3, 3, Stone.BLACK);
	    board.setField(3, 4, Stone.BLACK);
	    board.setField(3, 5, Stone.BLACK);
	    board.setField(2, 3, Stone.BLACK);
	    board.setField(4, 3, Stone.BLACK);

	    board.setField(2, 4, Stone.WHITE);
	    board.setField(3, 2, Stone.WHITE);
	    board.setField(3, 6, Stone.WHITE);
	    board.setField(4, 2, Stone.WHITE);
	    board.setField(2, 2, Stone.WHITE);
	    board.setField(1, 3, Stone.WHITE);
	    board.setField(2, 5, Stone.WHITE);
	    board.setField(5, 3, Stone.WHITE);
	    board.setField(4, 4, Stone.WHITE);
	    board.setField(4, 5, Stone.WHITE);
	    
	    System.out.println(board.prettyPrint());

	    assertTrue(analyser.hasCapture(3, 3));
	    assertTrue(analyser.hasCapture(3, 4));
	    assertTrue(analyser.hasCapture(3, 5));
	    assertTrue(analyser.hasCapture(2, 3));
	    assertTrue(analyser.hasCapture(4, 3));
	    
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);

	    assertFalse(analyser.hasCapture(2, 4));
	    assertFalse(analyser.hasCapture(5, 3));
	    assertFalse(analyser.hasCapture(3, 2));
	    assertFalse(analyser.hasCapture(4, 4));
	}
	
	@Test
	void squareCapture() {
	    board.setField(3, 3, Stone.BLACK);
	    board.setField(3, 4, Stone.BLACK);
	    board.setField(4, 3, Stone.BLACK);
	    board.setField(4, 4, Stone.BLACK);

	    board.setField(2, 3, Stone.WHITE);
	    board.setField(2, 4, Stone.WHITE);
	    board.setField(5, 3, Stone.WHITE);
	    board.setField(5, 4, Stone.WHITE);
	    board.setField(3, 2, Stone.WHITE);
	    board.setField(4, 2, Stone.WHITE);
	    board.setField(3, 5, Stone.WHITE);
	    board.setField(4, 5, Stone.WHITE);
	    
	    System.out.println(board.prettyPrint());

	    assertTrue(analyser.hasCapture(3, 3));
	    assertTrue(analyser.hasCapture(3, 4));
	    assertTrue(analyser.hasCapture(4, 3));
	    assertTrue(analyser.hasCapture(4, 4));
	    
	    assertEquals(analyser.getFoundCapture(), Stone.BLACK);
	    
	    assertFalse(analyser.hasCapture(2, 3));
	    assertFalse(analyser.hasCapture(2, 4));
	    assertFalse(analyser.hasCapture(5, 3));
	    assertFalse(analyser.hasCapture(5, 4));
	    assertFalse(analyser.hasCapture(3, 2));
	    assertFalse(analyser.hasCapture(4, 2));
	    assertFalse(analyser.hasCapture(3, 5));
	    assertFalse(analyser.hasCapture(4, 5));
	}
	
	@Test
	void noStoneCapture() {
		assertFalse(analyser.hasCapture(0, 0));
		assertFalse(analyser.hasCapture(0, 6));
		assertFalse(analyser.hasCapture(6, 0));
		assertFalse(analyser.hasCapture(6, 6));
		assertFalse(analyser.hasCapture(3, 3));
	}
	
	@Test
	void topRightCornerCapture() {
		board.setField(6, 0, Stone.WHITE);
		board.setField(5, 0, Stone.BLACK);
		board.setField(6, 1, Stone.BLACK);
		
		System.out.println(board.prettyPrint());
		
		assertFalse(analyser.hasCapture(5, 0));
		assertFalse(analyser.hasCapture(6, 1));
		
		assertTrue(analyser.hasCapture(6, 0));
	}
	
	@Test
	void topLeftCornerCapture() {
		board.setField(0, 0, Stone.WHITE);
		board.setField(0, 1, Stone.BLACK);
		board.setField(1, 0, Stone.BLACK);
		
		System.out.println(board.prettyPrint());
		
		assertFalse(analyser.hasCapture(5, 0));
		assertFalse(analyser.hasCapture(6, 1));
		
		assertTrue(analyser.hasCapture(0, 0));
	}
	
	@Test
	void bottomRightCornerCapture() {
		board.setField(6, 6, Stone.WHITE);
		board.setField(5, 6, Stone.BLACK);
		board.setField(6, 5, Stone.BLACK);
		
		System.out.println(board.prettyPrint());
		
		assertFalse(analyser.hasCapture(5, 6));
		assertFalse(analyser.hasCapture(6, 5));
		
		assertTrue(analyser.hasCapture(6, 6));
	}
	
	@Test
	void bottomLeftCornerCapture() {
		board.setField(0, 6, Stone.WHITE);
		board.setField(1, 6, Stone.BLACK);
		board.setField(0, 5, Stone.BLACK);
		
		System.out.println(board.prettyPrint());
		
		assertFalse(analyser.hasCapture(1, 6));
		assertFalse(analyser.hasCapture(0, 5));
		
		assertTrue(analyser.hasCapture(0, 6));
	}
	
}
