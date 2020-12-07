package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import game.backend.level.Level;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level5;
import game.frontend.*;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;


	// Codigo menu

	private static final int WIDTH = 585;
	private static final int HEIGHT = 645;

	private final List<Pair<String, Runnable>> menuData = Arrays.asList(
			new Pair<String, Runnable>(
					"Level 1", () -> {setLevel(new Level1());
				Platform.exit();
			}),
			new Pair<String, Runnable>(
					"Level 2", () -> setLevel(new Level2())
			),
			new Pair<String, Runnable>(
					"Level 5", () -> setLevel(new Level5())
			)
	);

	private Level level;
	private final VBox menuBox = new VBox(-5);

	public void addTitle() {
		GameApp.Title title = new GameApp.Title("Candy Crush");
		title.setTranslateX((WIDTH - title.getTitleWidth()) / 2.5);
		title.setTranslateY(HEIGHT / 3.0);
		getChildren().add(title);
	}

	public void addBackground() {
		ImageView imageView = new ImageView(generateImage(1, 198.0 / 255, 250.0 / 255, 1));
		imageView.setFitWidth(WIDTH);
		imageView.setFitHeight(HEIGHT);
		getChildren().add(imageView);
	}

	private Image generateImage(double red, double green, double blue, double opacity) {
		WritableImage img = new WritableImage(1, 1);
		PixelWriter pw = img.getPixelWriter();
		Color color = Color.color(red, green, blue, opacity);
		pw.setColor(0, 0, color);
		return img;
	}

	public void startAnimation() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(1), null);
		st.setToY(1);
		st.setOnFinished(e -> {
			for (int i = 0; i < menuBox.getChildren().size(); i++) {
				Node n = menuBox.getChildren().get(i);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
				tt.setToX(0);
				tt.setOnFinished(e2 -> n.setClip(null));
				tt.play();
			}
		});
		st.play();
	}

	private void addMenu(double x, double y) {
		menuBox.setTranslateX(x);
		menuBox.setTranslateY(y);
		menuData.forEach(data -> {
			MenuItemm item = new MenuItemm(data.getKey());
			item.setOnAction(data.getValue());
			item.setTranslateX(-300);

			Rectangle clip = new Rectangle(300, 30);
			clip.translateXProperty().bind(item.translateXProperty().negate());

			item.setClip(clip);

			menuBox.getChildren().addAll(item);
		});

		getChildren().add(menuBox);
	}

	private void setLevel(Level level){
		this.level = level;
	}

	public CandyFrame(CandyGame game){
		this.game = game;

		addBackground();
		addTitle();
		addMenu(WIDTH / 3.0, HEIGHT / 2.0);
		startAnimation();


		// Crear el menu...

//		CandyLevel(level);

		// Dentro el menu llama a el nivel.
	}

	public void CandyLevel(Level level) {

		// idem para cada lvl.

		getChildren().add(new AppMenu());
		images = new ImageManager();
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);

		// diff
		//createScorePanel(level);
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);

		// Idem para cda lvl.

		game.initGame(level);

		// Idem para level 1 y 2 supon que es idem para lvl 5.

		GameListener listener;
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(5);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						Image image = images.getImage(element);
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null)));
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image)));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}
		});

		listener.gridUpdated();

		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null) {
				lastPoint = translateCoords(event.getX(), event.getY());
				System.out.println("Get first = " +  lastPoint);
			} else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				if (newPoint != null) {
					System.out.println("Get second = " +  newPoint);
					boolean valid = game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());
					String message = ((Long)game().getScore()).toString();
					if (game().isFinished()) {
						if (game().playerWon()) {
							message = message + " Finished - Player Won!";
						} else {
							message = message + " Finished - Loser !";
						}
					}
					scorePanel.updateScore(message);
					lastPoint = null;
				}
			}
		});

	}

	private CandyGame game() {
		return game;
	}

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
