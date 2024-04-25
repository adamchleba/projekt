package org.example;

import java.awt.*;

public class Enemy {

    public int speed;
    public int health;
    public Coords coords;
    public Enemy(int x, int y, int speed, int health) {
        this.coords = new Coords(x,y);
        this.speed = speed;
        this.health = health;

    }
    public int getX() {
        return coords.x;
    }
    public int getY() {
        return coords.y;
    }
    public Image getImage(){
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
