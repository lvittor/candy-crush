package game.backend.element;

public class Nothing extends Element {
	
	@Override
	public boolean isMovable() {
		return false;
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	@Override
	public String getKey() {
		return "NOTHING";
	}

}
