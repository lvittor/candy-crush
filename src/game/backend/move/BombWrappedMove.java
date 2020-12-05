package game.backend.move;

import game.backend.Grid;

public class BombWrappedMove extends BombMove {

	public BombWrappedMove(Grid grid) {
		super(grid);	
	}
	
	@Override
	public void removeElements() {

		/*
		* Como un BombWrappedMove tiene casi la misma implementacion y funcionamiento de un BombMove, hicimos que extienda de esta.
		* */

		super.removeElements();

		//Los dos fors siguientes podrían estar modularizados en una unica funcion
		clearClose(i1, j1);
		clearClose(i2, j2);
	}

	private void clearClose(int i2, int j2) {
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if (i2 + i >= 0 && i2 + i < Grid.SIZE && j2 + j >= 0 && j2 + j < Grid.SIZE) {
					clearContent(i2 + i, j2 + j);
				}
			}
		}
	}
}
