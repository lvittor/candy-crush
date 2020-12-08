package game.frontend;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuItemm extends Pane {

    public MenuItemm(String name) {

        Rectangle bg = new Rectangle(
                0, 0,
                200, 30
        );

        bg.fillProperty().bind(
                Bindings.when(pressedProperty())
                        .then(Color.color(1, 198.0 / 255, 250.0 / 255, 1))
                        .otherwise(Color.color(209.0 / 255, 156.0 / 255, 1, 1))
        );

        Text text = new Text(name);
        text.setFont(Font.font("Verdana", 20));
        text.setStyle("-fx-font-weight: bold");
        text.setTranslateX(65);
        text.setTranslateY(20);
        text.setFill(Color.WHITE);

        Effect shadow = new DropShadow(5, Color.BLACK);
        Effect blur = new BoxBlur(1, 1, 3);
        text.effectProperty().bind(
                Bindings.when(hoverProperty())
                        .then(shadow)
                        .otherwise(blur)
        );

        getChildren().addAll(bg, text);
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}