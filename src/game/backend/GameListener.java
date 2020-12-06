package game.backend;

import game.backend.element.Element;

public interface GameListener {
	
	default void gridUpdated(){
		//
	}
	
	default void cellExplosion(Element e){
		//
	}


}