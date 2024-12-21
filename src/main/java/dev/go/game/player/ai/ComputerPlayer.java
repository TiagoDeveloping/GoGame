package dev.go.game.player.ai;

import dev.go.board.Move;
import dev.go.game.GoGame;
import dev.go.game.player.AbstractPlayer;
import dev.go.game.player.Stone;
import dev.go.main.InputProvider;
import lombok.Getter;

@Getter
public class ComputerPlayer extends AbstractPlayer {

	private Strategy strategy;
	
	private Stone stone;
	private String name;
	@Override
	public Move getNextMove(GoGame game, InputProvider provider) {
		// TODO Auto-generated method stub
		return null;
	}
}
