import javax.swing.*;


public class Player extends Entity{

    public int lives = 3;
    public final int steps = 5;
    private int shieldCollisionsLeft;
    private boolean shieldActive;


        public Player(int x, int y, String url) {
            ImageIcon playericon = new ImageIcon(this.getClass().getResource("/" + url));
            this.image = playericon.getImage();
            this.width = playericon.getIconWidth();
            this.height = playericon.getIconHeight();
            this.coords = new Coords(x, y);
            this.shieldActive = false;
            this.shieldCollisionsLeft = 0;
        }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                coords.x -= steps;
                break;
            case RIGHT:
                coords.x += steps;
                break;
            case UP:
                coords.y -= steps;
                break;
            case DOWN:
                coords.y += steps;
                break;
        }
    }
    public boolean isShieldActive() {
        return shieldActive;
    }

    public void activateShield(int collisions) {
        this.shieldActive = true;
        this.shieldCollisionsLeft = collisions;
    }

    public void deactivateShield() {
        this.shieldActive = false;
        this.shieldCollisionsLeft = 0;
    }

    public void reduceShieldCollisions() {
        if (shieldActive) {
            shieldCollisionsLeft--;
            if (shieldCollisionsLeft <= 0) {
                deactivateShield();
            }
        }
    }
}
