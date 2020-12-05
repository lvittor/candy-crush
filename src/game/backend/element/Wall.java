package game.backend.element;

public class Wall extends Element {
	
	@Override
	public boolean isMovable() {
		return false;
	}
	
	@Override
	public String getKey() {
		return "WALL";
	}

}
