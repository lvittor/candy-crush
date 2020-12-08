package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;

public abstract class GeneratorCell extends Cell{

    public GeneratorCell(Grid grid) {
        super(grid);
    }

    @Override
    public boolean isMovable(){
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public abstract Element getContent();

    @Override
    public Element getAndClearContent() {
        return getContent();
    }

    @Override
    public boolean fallUpperContent() {
        throw new IllegalStateException();
    }

    @Override
    public void setContent(Element content) {
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
