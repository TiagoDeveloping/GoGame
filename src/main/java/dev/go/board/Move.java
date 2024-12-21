package dev.go.board;

import dev.go.game.player.Stone;

/**
 * @author tiago
 */
public class Move {

	public final int x, y;
	
	public final Stone stone;

	public Move(int x, int y, Stone stone) {
		this.x = x;
		this.y = y;
		this.stone = stone;
	}
	
	@Override
	public String toString() {
		return "(" + x + ";" + y + ")-->" + stone.toString();
	}

}
