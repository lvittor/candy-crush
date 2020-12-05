package game.frontend;

import game.backend.element.Bomb;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.HorizontalStripedCandy;
import game.backend.element.Nothing;
import game.backend.element.VerticalStripedCandy;
import game.backend.element.Wall;
import game.backend.element.WrappedCandy;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {

	private static final String IMAGE_PATH = "images/";
	private Map<String, Image> images;

	public ImageManager() {
		WrappedCandy wc = new WrappedCandy();
		VerticalStripedCandy vc = new VerticalStripedCandy();
		HorizontalStripedCandy hc = new HorizontalStripedCandy();
		images = new HashMap<>();
		images.put(new Nothing().getKey(), new Image(IMAGE_PATH + "nothing.png"));
		images.put(new Bomb().getKey(),  new Image(IMAGE_PATH + "bomb.png"));
		images.put(new Wall().getKey(),  new Image(IMAGE_PATH + "wall.png"));
		/*
		 * Econtramos que para setear las imagenes con sus colores se recorrian estos 4 veces pero se podia hacer con un
		 * solo recorrido.
		 * */
		for (CandyColor cc: CandyColor.values()) {
			// Agregamos los Candy con sus imagenes
			images.put(new Candy(cc).getFullKey(),   new Image(IMAGE_PATH + cc.toString().toLowerCase() + "Candy.png"));

			// Agregamos los WrappedCandy con sus imagenes
			wc.setColor(cc);
			images.put(wc.getFullKey(),  new Image(IMAGE_PATH + cc.toString().toLowerCase() + "Wrapped.png"));

			// Agregamos los VerticalStripedCandy con sus imagenes
			vc.setColor(cc);
			images.put(vc.getFullKey(),  new Image(IMAGE_PATH + cc.toString().toLowerCase() + "VStripped.png"));

			// Agregamos los HorizontalStripedCandy con sus imagenes
			hc.setColor(cc);
			images.put(hc.getFullKey(),  new Image(IMAGE_PATH + cc.toString().toLowerCase() + "HStripped.png"));
		}

	}

	public Image getImage(Element e) {
		return images.get(e.getFullKey());
	}

}
