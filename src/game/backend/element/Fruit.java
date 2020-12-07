package game.backend.element;

import java.util.Objects;

public class Fruit extends Element {

    private FruitType type;

    public Fruit(FruitType type) {
        this.type = type;
    }

    public Fruit(){ }

    public void setType(FruitType type){
        this.type = type;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public String getKey() {
        return "FRUIT";
    }

    @Override
    public boolean canBeCleared(){
        return false;
    }

    @Override
    public String getFullKey() {
        return type.toString();
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

}
