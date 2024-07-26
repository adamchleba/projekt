import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{

    public Player player;
    public int shieldCollisions;
    public final int MOVE_CONSTANT = 10;



    public Enemy(int x, int y, String url) {
        ImageIcon ei = new ImageIcon(this.getClass().getResource("/" + url));
        this.image = ei.getImage();
        this.width = ei.getIconWidth();
        this.height = ei.getIconHeight();
        this.coords = new Coords(x, y);

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
    public void moveRandomly(){
        Random random = new Random();
        boolean moveHorizontally = random.nextBoolean();

        if (moveHorizontally) {
            boolean moveRight = random.nextBoolean();
            if (moveRight) {
                coords.x+=MOVE_CONSTANT;
            } else {
                coords.x-=MOVE_CONSTANT;
            }
        } else {
            boolean moveUp = random.nextBoolean();
            if (moveUp) {
                coords.y+=MOVE_CONSTANT;
            } else {
                coords.y-=MOVE_CONSTANT;
            }
        }
    }
}
