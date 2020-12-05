package game.backend.move;

import game.backend.Grid;

public class TwoWrappedMove extends Move {
	
	public TwoWrappedMove(Grid grid) {
		super(grid);
	}
	
	@Override
	public void removeElements() {
		int currI, currJ;
		if (i1 == i2) {
			if (j1 < j2) {
				currI = i1;
				currJ = j1;
			} else {
				currI = i2;
				currJ = j2;
			}
			clearContent(currI,currJ-1);
			clearContent(currI, currJ + 2);
			for(int n = -1; n < 3; n++) {
				clearContent(currI - 1, currJ + n);
				clearContent(currI + 1, currJ + n);
			}
		} else {
			if (i1 < i2) {
				currI = i1;
				currJ = j1;
			} else {
				currI = i2;
				currJ = j2;
			}
			clearContent(currI,currJ-1);
			clearContent(currI,currJ+2);
			for(int n = -1; n < 3; n++) {
				clearContent(currI - 1, currJ + n);
				clearContent(currI + 1, currJ + n);
			}
		}
	}

}
