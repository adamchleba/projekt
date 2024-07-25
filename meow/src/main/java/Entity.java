import java.awt.*;

public class Entity {
    protected Coords coords;
    protected Image image;
    protected int width;
    protected int height;
    protected int steps;


    public int getX() {
        return coords.x;
    }

    public int getY() {
        return coords.y;
    }
    public Image getImage(){
        return image;
    }

}
