package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.Cell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.Fruit;
import game.backend.element.Nothing;

public class Level5 extends Level{

    private static final int FRUITS = 5;
    private int fruitsFound;

    @Override
    protected GameState newState() {
        return new Level5State(MAX_MOVES, FRUITS);
    }

    private class Level5State extends GameState {

        private long fruits;
        private long maxMoves;

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

    }

    private int getFruitsFound() {
        return fruitsFound;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2){
        boolean ret;
        if(ret = super.tryMove(i1,j1,i2,j2)){
            state().addMove();
            clearFruitLine();
        }
        return ret;
    }

    public void clearFruitLine(){
        for(Cell c : g()[Grid.SIZE - 1]){
            if( ! c.getContent().canBeCleared() ){
                c.setContent(new Nothing());
                fallElements();
                fruitsFound++;
            }
        }
    }

    @Override
    public Cell getGeneratorCellType(){
        return new FruitGeneratorCell(this);
    }

}
