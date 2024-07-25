import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameGraphics extends JFrame {
    Draw draw;
    GameLogic logic;
    private Image backgroundImage;

    public GameGraphics(GameLogic logic) throws HeadlessException {
        this.logic = logic;
        this.draw = new Draw();
        setSize(1080,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("angry mice");
        setResizable(true);
        add(draw);


    }
    public void render(GameLogic logic){
        this.logic = logic;
        repaint();
    }


    public class Draw extends JPanel{
        public void paintComponent(Graphics g){
            this.setBackground(Color.GREEN);

            ImageIcon image = new ImageIcon("player.png");
            g.drawImage(image.getImage(),20 , 20, null);

            super.paintComponent(g);

            drawWall(700,100,10,100,g);
            drawWall(900,250,10,100,g);
            drawWall(500,500,10,100,g);
            drawWall(900,450,10,100,g);



            if(logic.getPlayer() != null && logic.getPlayer().getImage() != null){
                g.drawImage(logic.getPlayer().getImage(), logic.getPlayer().getX(), logic.getPlayer().getY(), null);
            }
            else{
                System.out.println("Player nebo player image je null");
            }

            for(Enemy enemy : logic.getEnemies()){
                g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
            }
            if(logic.getEnemies() == null){
                System.out.println("enemy nebo enemy image je null");

            }
            for (Shield shield : logic.getShields()) {
                g.drawImage(shield.getImage(), shield.getX(), shield.getY(), this);
            }
            for (Thunderbolt thunderbolt : logic.getThunders()) {
                g.drawImage(thunderbolt.getImage(), thunderbolt.getX(), thunderbolt.getY(), null);
            }
            if (logic.lives == 0) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Konec hry", 300, 300);
                return; // Stop further drawing
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + GameLogic.score, 10, 20);
            g.drawString("Lives: " + GameLogic.lives, 200, 20);

        }

    }
    private void drawThunderbolt(Graphics g, Thunderbolt thunderbolt) {
        g.drawImage(thunderbolt.getImage(), thunderbolt.getX(), thunderbolt.getY(), null);
    }
    public void drawWall(int x, int y, int width, int height, Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x,y,width,height);
    }


}
