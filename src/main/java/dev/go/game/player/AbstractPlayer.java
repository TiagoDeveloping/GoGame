package dev.go.game.player;

public abstract class AbstractPlayer implements Player {
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("name=").append(getName()).append(",");
		sb.append("stone=").append(getStone());
		sb.append("}");
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Player)) return false;
		Player player = (Player) obj;
		return 
			getStone().equals(player.getStone()) &&
			getName().equals(player.getName());
	}

}
