package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;

public class FruitGeneratorCell extends GeneratorCell{

    public FruitGeneratorCell(Grid grid) {
        super(grid);
    }

    @Override
    public Element getContent() {
        return null;
    }
}
