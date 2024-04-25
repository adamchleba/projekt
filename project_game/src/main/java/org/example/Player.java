package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {

    public int speed;
    public String name;
    public int health;
    public ArrayList<Items> item;
    public Coords coords;


    public Player(int x, int y, int speed, String name, int health) {
        this.speed = speed;
        this.name = name;
        this.health = health;
        this.coords = new Coords(x,y);

        ArrayList<Items> item = new ArrayList<>();
    }

    public int getX() {
        return coords.x;
    }

    public int getY() {
        return coords.y;
    }

    public Image getImage() {
        return null;
    }

    public void move(int steps, Direction direction){
        switch(direction){
            case LEFT -> this.coords.x -= steps;
            case RIGHT -> this.coords.x += steps;
            case UP -> this.coords.y -= steps;
            case DOWN -> this.coords.y += steps;
        }
    }
}
