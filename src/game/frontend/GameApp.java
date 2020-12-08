package game.frontend;

import game.backend.CandyGame;
import game.backend.Grid;
import game.backend.level.Level;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level5;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;


public class GameApp extends Application {

    private static final int WIDTH = Grid.SIZE * CandyFrame.CELL_SIZE;
	private static final int HEIGHT = WIDTH + 60;   // 60 es el alto del ScorePanel mas el alto del MenuBar.
	private static Stage stage;
	private final Pane root = new Pane();
	private final VBox menuBox = new VBox(-5);
	private Scene menu;

	private final List<Pair<String, Runnable>> menuData = Arrays.asList(
			new Pair<String, Runnable>(
					"Level 1", () -> playLevel(new Level1())
			),
			new Pair<String, Runnable>(
					"Level 2", () -> playLevel(new Level2(), (board, lastPoint, newPoint) -> {
						if ( (int)lastPoint.getX() == (int)newPoint.getX() ){
							board.setGoldRow((int)lastPoint.getX());
						} else {
							board.setGoldColumn((int)lastPoint.getY());
						}
			})
			),
			new Pair<String, Runnable>(
					"Level 5", () -> playLevel(new Level5())
			)
	);

	private void playLevel(Level level, LevelBehaviour behaviour){
		Scene scene = new Scene(new CandyFrame(new CandyGame(), level, this, behaviour));
		replaceScene(scene);
	}
	private void playLevel(Level level){
		Scene scene = new Scene(new CandyFrame(new CandyGame(), level, this));
		replaceScene(scene);
	}

	private Parent createContent() {
		addBackground();
		addTitle();
		addMenu(WIDTH / 3.0, HEIGHT / 2.0);
		startAnimation();

		return root;
	}

	private void addTitle() {
		Title title = new Title("Candy Crush");
		title.setTranslateX((WIDTH - title.getTitleWidth()) / 2.5);
		title.setTranslateY(HEIGHT / 3.0);
		root.getChildren().add(title);
	}

	private void addBackground() {
		ImageView imageView = new ImageView(generateImage(1, 198.0 / 255, 250.0 / 255, 1));
		imageView.setFitWidth(WIDTH);
		imageView.setFitHeight(HEIGHT);
		root.getChildren().add(imageView);
	}

	/*
	 * From: https://stackoverflow.com/questions/60532986
	 */
	private Image generateImage(double red, double green, double blue, double opacity) {
		WritableImage img = new WritableImage(1, 1);
		PixelWriter pw = img.getPixelWriter();
		Color color = Color.color(red, green, blue, opacity);
		pw.setColor(0, 0, color);
		return img;
	}

	private void startAnimation() {
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

		root.getChildren().add(menuBox);
	}

	private static class Title extends Pane {
		private final Text text;

		public Title(String name) {
			text = new Text(name.replace("", " ").trim());
			text.setFont(Font.font("Verdana", 40));
			text.setStyle("-fx-font-weight: bold");
			text.setFill(Color.BLACK);
			getChildren().addAll(text);
		}

		public double getTitleWidth() {
			return text.getLayoutBounds().getWidth();
		}
	}

	private static void replaceScene(Scene newScene) {
		stage.setScene(newScene);
		stage.sizeToScene();
	}

	private void initMenu() {
		menu = new Scene(createContent());
		stage.setResizable(false);
		replaceScene(menu);
	}

	public void backToMenu(){
		replaceScene(menu);
	}

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		initMenu();
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
