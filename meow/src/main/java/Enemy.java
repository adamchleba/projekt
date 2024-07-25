import javax.swing.*;
import java.awt.*;

public class Enemy extends Entity{

    public Player player;
    public int shieldCollisions;
    public String movementDirection;
    public Coords coords;


    public Enemy(int x, int y, String url) {
        ImageIcon ei = new ImageIcon(this.getClass().getResource("/" + url));
        this.image = ei.getImage();
        this.width = ei.getIconWidth();
        this.height = ei.getIconHeight();
        this.coords = new Coords(x, y);
        this.movementDirection = "UP";
    }
    public boolean isShieldActive() {
        return shieldCollisions > 0;
    }

    public void activateShield(int collisions) {
        this.shieldCollisions = collisions;
    }

    public void reduceShieldCollisions() {
        if (shieldCollisions > 0) {
            shieldCollisions--;
        }
    }
}
