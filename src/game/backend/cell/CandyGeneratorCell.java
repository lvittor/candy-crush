package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;

public class CandyGeneratorCell extends GeneratorCell {

	public CandyGeneratorCell(Grid grid) {
		super(grid);
	}

	@Override
	public Element getContent() {
		int i = (int)(Math.random() * CandyColor.values().length);
		return new Candy(CandyColor.values()[i]);
	}


}
