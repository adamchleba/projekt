package org.example;

import java.util.ArrayList;



public class GameLogic {
    public Player player;
    public ArrayList<Enemy> enemies;

    public GameLogic() {
        this.player = player;
        this.enemies = new ArrayList<>();
    }
    public void initialize(){
        player = new Player(20,20,5,"chleba",50, "player.png",50,50);

        Enemy enemy1 = new Enemy(100,100,3,20);
        Enemy enemy2 = new Enemy(135,100,3,40);
        enemies.add(enemy1);
        enemies.add(enemy2);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void update(){
        for(Enemy enemy : enemies){
            int diffX = Math.abs(player.getX() - enemy.getX());
            int diffY = Math.abs(player.getY() - enemy.getY());

            if(diffX > diffY){
                if(player.getX() - enemy.getX() > 0) {
                    enemy.move(20,Direction.RIGHT);
                }else {
                    enemy.move(20,Direction.LEFT);
                }if(player.getY() - enemy.getY() > 0) {
                    enemy.move(20, Direction.DOWN);
                }else{
                    enemy.move(20,Direction.UP);
                }
            }

        }
    }

}
