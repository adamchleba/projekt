import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{

    public Player player;
    public int shieldCollisions;
    private final int MOVE_CONSTANT = 20;
    public String movementDirection;

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


    public void randomMove() {
        Random random = new Random();
        boolean moveHorizontally = random.nextBoolean(); // 50% pravděpodobnost pro horizontální nebo vertikální pohyb

        if (moveHorizontally) {
            boolean moveRight = random.nextBoolean(); // 50% pravděpodobnost pro pohyb doprava nebo doleva
            if (moveRight) {
                coords.x+=MOVE_CONSTANT; // Pohyb doprava
            } else {
                coords.x-=MOVE_CONSTANT; // Pohyb doleva
            }
        } else {
            boolean moveUp = random.nextBoolean(); // 50% pravděpodobnost pro pohyb nahoru nebo dolů
            if (moveUp) {
                coords.y+=MOVE_CONSTANT; // Pohyb nahoru
            } else {
                coords.y-=MOVE_CONSTANT; // Pohyb dolů
            }
        }
    }
}
