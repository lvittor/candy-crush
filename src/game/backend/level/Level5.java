package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.Cell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.Nothing;

public class Level5 extends Level{

    private static final int FRUITS = 3;
    private int fruitsFound = 0;
    private GameState state;

    @Override
    protected GameState newState() {
        return state = new Level5State(MAX_MOVES, FRUITS);
    }

    public GameState getState(){
        return state;
    }

    private class Level5State extends GameState {

        private final long fruits;
        private final long maxMoves;

        public Level5State(int maxMoves, int fruits) {
            this.maxMoves = maxMoves;
            this.fruits = fruits;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getFruitsFound() == fruits;
        }

        @Override
        public String getDescription() {
            return super.getDescription() + " |" + " Moves left: " + (maxMoves - getMoves()) + " |" + " Fruits left: " + (fruits - getFruitsFound());
        }
    }

    private int getFruitsFound() {
        return fruitsFound;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2){
        boolean ret;
        if(ret = super.tryMove(i1,j1,i2,j2)){
            clearFruitLine(g()[Grid.SIZE - 1], 0);
        }
        return ret;
    }


    public void clearFruitLine(Cell[] line, int j){
        if (j==Grid.SIZE)
            return;
        if ( ! line[j].getContent().canBeCleared() ) {
            line[j].setContent(new Nothing());
            fruitsFound++;
            super.fallElements();
            clearFruitLine(line, 0);
        }
        clearFruitLine(line, j+1);
    }

    @Override
    public Cell getGeneratorCellType(){
        return new FruitGeneratorCell(this, FRUITS);
    }


}
