import java.awt.*;

public class Entity {
    protected Coords coords;
    protected Image image;
    protected int width;
    protected int height;



    public int getX() {
        return coords.x;
    }

    public int getY() {
        return coords.y;
    }
    public Image getImage(){
        return image;
    }
    public int setX(int x) {
        return x;
    }
    public int setY(int y) {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    protected Rectangle getCollisionRectangle(){
        return new Rectangle(coords.x, coords.y, width, height);
    }
/*
    public boolean isCollided(Rectangle otherObject){
        return getCollisionRectangle().intersects(otherObject);
    }

 */
}
