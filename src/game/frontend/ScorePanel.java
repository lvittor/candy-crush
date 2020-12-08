package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private final Label scoreLabel;

	public ScorePanel(String desc) {
		setStyle("-fx-background-color: #5490ff");
		scoreLabel = new Label(desc);
		scoreLabel.setAlignment(Pos.CENTER);
		scoreLabel.setStyle("-fx-font-size: 24");
		setCenter(scoreLabel);
	}
	
	public void updateScore(String text) {
		scoreLabel.setText(text);
	}



}