package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    GameLogic logic;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });
    }

    public Game() {
        logic = new GameLogic();
        GameGraphics gg = new GameGraphics(logic);
        logic.initialize();
        gg.render(logic);
        boolean isGameOver = false;


    }
}