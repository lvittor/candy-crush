package game.backend.move;

import game.backend.Grid;
import game.backend.element.Bomb;
import game.backend.element.Candy;

public class BombWrappedMove extends Move {

	public BombWrappedMove(Grid grid) {
		super(grid);	
	}
	
	@Override
	public void removeElements() {
		Candy candy = (Candy) (get(i1, j1) instanceof Bomb ? get(i2, j2) : get(i1, j1));
		clearContent(i1, j1);
		clearContent(i2, j2);
		for(int i = 0; i < Grid.SIZE; i++) {
			for(int j = 0; j < Grid.SIZE; j++) {
				if (candy.equals(get(i, j))) {
					clearContent(i, j);
				}
			}
		}
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if (i1 + i >= 0 && i1 + i < Grid.SIZE && j1 + j >= 0 && j1 + j < Grid.SIZE) {
					clearContent(i1 + i, j1 + j);
				}
			}
		}
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if (i2 + i >= 0 && i2 + i < Grid.SIZE && j2 + j >= 0 && j2 + j < Grid.SIZE) {
					clearContent(i2 + i, j2 + j);
				}
			}
		}
	}

}
