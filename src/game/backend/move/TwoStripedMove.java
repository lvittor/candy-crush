package game.backend.move;

import game.backend.Grid;

public class TwoStripedMove extends Move {

	public TwoStripedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		for(int i = 0; i < Grid.SIZE; i++) {
			clearContent(i, j2);
		}
		for(int j = 0; j < Grid.SIZE; j++) {
			clearContent(i2, j);
		}
	}

}
