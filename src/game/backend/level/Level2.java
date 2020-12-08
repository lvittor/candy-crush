package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;


public class Level2 extends Level{

    private int rows;
    private int cols;

    @Override
    protected GameState newState() {
        return new Level2State(MAX_MOVES);
    }

    public int getGoldenCells(){
        int rowCount, colCount;
        rowCount = Integer.bitCount(rows);
        colCount = Integer.bitCount(cols);

        return rowCount * Grid.SIZE + colCount * Grid.SIZE - rowCount * colCount ;
    }

    private class Level2State extends GameState {

        private final long maxMoves;

        public Level2State(int maxMoves) {
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return getGoldenCells() == Grid.SIZE * Grid.SIZE;
        }

        @Override
        public String getDescription() {
            return super.getDescription() + " |"+ " Moves left: " + (maxMoves - getMoves()) + " |"+ " Cells left: " + ((Grid.SIZE * Grid.SIZE) - getGoldenCells());
        }
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret = super.tryMove(i1, j1, i2, j2);
        if (ret) {
            if (i1 == i2){
                rows |=  1 << i1; //puts 1 in the i1 position
            } else {
                cols |= 1 << j1; //puts 1 in the j1 position
            }
        }
        return ret;
    }

    @Override
    public Cell getGeneratorCellType(){
        return new CandyGeneratorCell(this);
    }

}
