package de.go.game.listeners;

import dev.go.game.player.Player;

public interface HasWinnerListener {
	void onWinner(Player player);
}
