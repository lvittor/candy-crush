package game.backend.cell;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.element.*;

public class FruitGeneratorCell extends GeneratorCell{

    private int fruits;
    private int moves = 0;
    private final GameState state;
    private static final int DIFFICULTY = 1;

    public FruitGeneratorCell(Grid grid, int fruits) {
        super(grid);
        state = grid.state();
        this.fruits = fruits;
    }

    @Override
    public Element getContent() {


        if (  state.getMoves() != moves  && fruits > 0 ) {
            moves++;
            if ( ((int)(Math.random() * DIFFICULTY)) == 0) {
                fruits--;
                return new Fruit(FruitType.values()[(int) (Math.random() * FruitType.values().length)]);
            }
        }

        int i = (int)(Math.random() * CandyColor.values().length);
        return new Candy(CandyColor.values()[i]);
    }
}
