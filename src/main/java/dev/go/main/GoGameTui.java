package dev.go.main;

import dev.go.board.Move;
import dev.go.exceptions.IllegalMoveException;
import dev.go.game.GoGame;
import dev.go.game.player.HumanPlayer;
import dev.go.game.player.Player;
import dev.go.game.player.Stone;

public class GoGameTui {
	
	public static void main(String[] args) {
		Player p0 = new HumanPlayer(Stone.BLACK, "p0");
		Player p1 = new HumanPlayer(Stone.WHITE, "p1");
		
		GoGame game = new GoGame(p0, p1);
		game.setHasWinnerListener(winner -> {
			System.out.println("Winner: " + winner.getName());
			System.exit(0);
		});
		
		InputProvider provider = new ConsoleInputProvider();
		
		while(game.isActive()) {
			System.out.println(game.prettyPrint() + "\n");
			
			System.out.println("Turn for: " + p0.getName());
			Move m = p0.getNextMove(game, provider);
			
			try {
				game.doMove(m);
			} catch (IllegalMoveException e) {
				System.err.println(e.getMessage());
				System.err.println("You get 1 more try...");
				m = p0.getNextMove(game, provider);
				game.doMove(m);
			}
			
			System.out.println(game.prettyPrint() + "\n");
			
			System.out.println("Turn for: " + p1.getName());
			Move m0 = p1.getNextMove(game, provider);
			
			try {
				game.doMove(m0);
			} catch (IllegalMoveException e) {
				System.err.println(e.getMessage());
			}
		}

	}
	
}
