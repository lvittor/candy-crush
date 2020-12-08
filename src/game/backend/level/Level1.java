package game.backend.level;

import game.backend.GameState;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;

public class Level1 extends Level {
	
	private static final int REQUIRED_SCORE = 5000;
	
	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}
	
	private static class Level1State extends GameState {
		private final long requiredScore;
		private final long maxMoves;
		
		public Level1State(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			this.maxMoves = maxMoves;
		}
		
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}
		
		public boolean playerWon() {
			return getScore() > requiredScore;
		}

		@Override
		public String getDescription() {
			return super.getDescription() + " |"+ " Moves left: " + (maxMoves - getMoves()) + " |" + " Goal: " + REQUIRED_SCORE;
		}
	}

	@Override
	public Cell getGeneratorCellType(){
		return new CandyGeneratorCell(this);
	}

}
