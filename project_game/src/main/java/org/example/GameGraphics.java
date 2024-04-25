package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GameGraphics extends JFrame {
    Draw draw;
    GameLogic logic;

    public GameGraphics (GameLogic logic) throws HeadlessException{
        this.draw = new Draw();
        this.logic = logic;

        setSize(1080,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Bread Hunt");

        add(draw);
    }
    public void render(GameLogic logic){
        this.logic = logic;
        repaint();
    }
    public class Draw extends JPanel{
        public void paintComps(Graphics g){
            g.drawImage(logic.getPlayer().getImage(), logic.getPlayer().getX(), logic.getPlayer().getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
            for(Enemy enemy : logic.getEnemies()){
                g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }


        }


    }




}
