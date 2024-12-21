package dev.go.game.player;

import dev.go.board.Move;
import dev.go.game.GoGame;
import dev.go.main.InputProvider;

public interface Player {
	
	public Stone getStone();
	public String getName();
	
	public Move getNextMove(GoGame game, InputProvider provider);
}
