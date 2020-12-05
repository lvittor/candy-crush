package game.backend;

import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;

import java.awt.Point;

public class FigureDetector {
	
	private Grid grid;
	
	public FigureDetector(Grid grid) {
		this.grid = grid;
	}
	
	public Figure checkFigure(int i, int j) {
		int acum = readCheckpoints(i, j);
		if (acum > 0) {
			for(Figure f: Figure.values()) {
				if (f.matches(acum)) {
					return f;
				}
			}
		}
		return null;
	}
	
	private int readCheckpoints(int i, int j) {
		Element curr = grid.get(i,j);
		int acum = 0;
		for (Checkpoint cp: Checkpoint.values()) {
			int newI = i + cp.getI();
			int newJ = j + cp.getJ();
			if (newI >= 0 && newI < Grid.SIZE && newJ >= 0 && newJ < Grid.SIZE) {
				if (curr.equals(grid.get(newI, newJ))) {
					acum += cp.getValue();
				}
			}
		}
		return acum;
	}
	
	public void removeFigure(int i, int j, Figure f) {
		CandyColor color = ((Candy)grid.get(i, j)).getColor();
		grid.clearContent(i, j);
		if (f.hasReplacement()) {
			grid.setContent(i, j, f.generateReplacement(color));
		}
		for (Point p: f.getPoints()) {
			grid.clearContent(i + p.x, j + p.y);
		}
	}
	
}
