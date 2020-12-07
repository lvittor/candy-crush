package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Candy;

public class Level2 extends Level{

    private long goldenCells;

    public void addGoldenCell(){
        goldenCells++;
    }

    @Override
    protected GameState newState() {
        return new Level2State(MAX_MOVES);
    }

    public long getGoldenCells(){
        return goldenCells;
    }

    private class Level2State extends GameState {

        private long maxMoves;

        public Level2State(int maxMoves) {
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getGoldenCells() == Grid.SIZE * Grid.SIZE;
        }

    }

    @Override
    public Cell getGeneratorCellType(){
        return new CandyGeneratorCell(this);
    }

}
