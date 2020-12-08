package game.frontend;

import javafx.geometry.Point2D;


@FunctionalInterface
public interface LevelBehaviour {

    void update(BoardPanel board, Point2D first, Point2D second);

}
