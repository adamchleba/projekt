import java.awt.Image;
import javax.swing.ImageIcon;

public class Shield {
    private int x, y;
    public int width, height;
    private Image image;

    public Shield(int x, int y, String url) {
        ImageIcon shieldIcon = new ImageIcon(this.getClass().getResource("/" + url));
        this.x = x;
        this.y = y;
        this.image = shieldIcon.getImage();
        this.width = shieldIcon.getIconWidth();
        this.height = shieldIcon.getIconHeight();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}