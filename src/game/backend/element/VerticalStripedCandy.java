package game.backend.element;

import game.backend.move.Direction;

public class VerticalStripedCandy extends Candy {
	
	private Direction[] explosion = new Direction[2];
	
	public VerticalStripedCandy() {
		explosion[0] = Direction.DOWN;
		explosion[1] = Direction.UP;
	}
	
	@Override
	public String getKey() {
		return "VERT-STRIPED-" + super.getKey();
	}
	
	@Override
	public String getFullKey() {
		return "VERT-STRIPED-" + super.getFullKey();
	}
	
	@Override
	public Direction[] explode() {
		return explosion;
	}
	
	@Override
	public long getScore() {
		return 80;
	}

}
