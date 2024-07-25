import javax.swing.*;
import java.awt.*;

public class Thunderbolt {
    public int x;
    public int y;
    public int width;
    public int height;
    public Image image;


    public Thunderbolt(int x, int y, String url) {
        this.x = x;
        this.y = y;
        ImageIcon thunderboltIcon = new ImageIcon(this.getClass().getResource("/" + url));
        this.image = thunderboltIcon.getImage();
        this.width = thunderboltIcon.getIconWidth();
        this.height = thunderboltIcon.getIconHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Image getImage(){
        return image;
    }
}
