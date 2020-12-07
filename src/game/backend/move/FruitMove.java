package game.backend.move;

import game.backend.Grid;

public class FruitMove extends Move {

    public FruitMove(Grid grid) {
        super(grid);
    }

    @Override
    public void removeElements() {
        //
    }

    @Override
    public boolean internalValidation(){
        return false;
    }

}
