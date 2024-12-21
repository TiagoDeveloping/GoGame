package dev.go.board;

import dev.go.game.player.Stone;

public interface ReadOnlyBoard {

	public Stone getField(int x, int y) throws ArrayIndexOutOfBoundsException;
	public boolean inBoard(int x, int y);
	public String toString();
	public String prettyPrint();
	public boolean equals(Object obj);
	
}
