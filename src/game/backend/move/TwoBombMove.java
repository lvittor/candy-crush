package game.backend.move;

import game.backend.Grid;

public class TwoBombMove extends Move {
	
	public TwoBombMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				clearContent(i,j);
			}
		}
	}

}
