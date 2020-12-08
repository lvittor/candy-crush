package game.backend;

import game.backend.cell.Cell;

import game.backend.element.Element;


public class CandyGame implements GameListener {

	private Grid grid;
	private GameState state;
	
	public void initGame(Grid levelGrid) {
		grid = levelGrid;
		state = grid.createState();
		grid.initialize();
		addGameListener(this);
	}
	
	public int getSize() {
		return Grid.SIZE;
	}
	
	public boolean tryMove(int i1, int j1, int i2, int j2){
		return grid.tryMove(i1, j1, i2, j2);
	}
	
	public Cell get(int i, int j){
		return grid.getCell(i, j);
	}
	
	public void addGameListener(GameListener listener) {
		grid.addListener(listener);
	}
	
	public long getScore() {
		return state.getScore();
	}
	
	public boolean isFinished() {
		return state.gameOver();
	}
	
	public boolean playerWon() {
		return state.playerWon();
	}

	public String getDescription(){
		return state.getDescription();
	}
	
	@Override
	public void cellExplosion(Element e) {
		state.addScore(e.getScore());
	}

}
