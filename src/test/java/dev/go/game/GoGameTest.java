package dev.go.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.go.board.Move;
import dev.go.exceptions.IllegalMoveException;
import dev.go.game.player.HumanPlayer;
import dev.go.game.player.Player;
import dev.go.game.player.Stone;

public class GoGameTest {
	
	GoGame game;
	
	Player p0 = new HumanPlayer(Stone.BLACK, "p0");
	Player p1 = new HumanPlayer(Stone.WHITE, "p1");
	
	@BeforeEach
	void init() {
		game = new GoGame(p0, p1);
	}
	
	@Test
	void serializationTest() {
		Move m0 = new Move(0, 0, Stone.BLACK);
		Move m1 = new Move(0, 1, Stone.WHITE);
		Move m2 = new Move(3, 3, Stone.BLACK);
		
		game.doMove(m0);
		game.doMove(m1);
		game.doMove(m2);
		
		System.out.println(game.toString());
		
		try {
            // Serialize the object into a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(game);
            oos.close();

            // Deserialize the object from the byte array
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            GoGame deserialized = (GoGame) ois.readObject();

            assertTrue(deserialized.equals(game));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	void moveExecutionTest() {
		assertEquals(game.getReadBoard().getField(0, 0), Stone.NONE);
		Move m = new Move(0, 0, Stone.BLACK);
		game.doMove(m);
		assertEquals(game.getReadBoard().getField(0, 0), Stone.BLACK);

		assertEquals(game.getReadBoard().getField(5, 3), Stone.NONE);
		Move m0 = new Move(5, 3, Stone.WHITE);
		game.doMove(m0);
		assertEquals(game.getReadBoard().getField(5, 3), Stone.WHITE);
	}
	
	@Test
	void winningMoveTest() {
		game.setHasWinnerListener(winner -> {
			assertEquals(winner, p1);
		});
		
		Move m0 = new Move(0, 0, Stone.BLACK);
		Move m1 = new Move(0, 1, Stone.WHITE);
		Move m2 = new Move(3, 3, Stone.BLACK);
		Move m3 = new Move(1, 0, Stone.WHITE);
		
		game.doMove(m0);
		assertNull(game.getWinner());
		game.doMove(m1);
		assertNull(game.getWinner());
		game.doMove(m2);
		assertNull(game.getWinner());
		game.doMove(m3);
		assertEquals(game.getWinner(), p1);
	}
	
	@Test
	void illegalMoveTest() {
		Move m = new Move(0, 0, Stone.WHITE);
		assertThrows(IllegalMoveException.class, () -> game.doMove(m));
		
		Move m0 = new Move(0, 7, Stone.BLACK);
		assertThrows(IllegalMoveException.class, () -> game.doMove(m0));
		
		Move m1 = new Move(7, 0, Stone.BLACK);
		assertThrows(IllegalMoveException.class, () -> game.doMove(m1));
		
		Move m2 = new Move(0, -2, Stone.BLACK);
		assertThrows(IllegalMoveException.class, () -> game.doMove(m2));
	}
	
}
