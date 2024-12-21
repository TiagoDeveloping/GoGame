package dev.go.game.player;

import java.io.Serializable;
import java.util.Map.Entry;

import dev.go.board.Move;
import dev.go.game.GoGame;
import dev.go.main.InputProvider;
import lombok.Getter;

@Getter
public class HumanPlayer extends AbstractPlayer implements Serializable {

	private static final long serialVersionUID = -5752345245875348748L;
	private Stone stone;
	private String name;
	
	public HumanPlayer(Stone stone, String name) {
		this.stone = stone;
		this.name = name;
	}

	@Override
	public Move getNextMove(GoGame game, InputProvider provider) {
		Entry<Integer, Integer> pair = provider.getMoveInput();
		int x = pair.getKey();
		int y = pair.getValue();
		
		return new Move(x, y, stone);
	}

}
