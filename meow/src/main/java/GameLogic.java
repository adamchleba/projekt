import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    public Player player;
    public ArrayList<Enemy> mice;
    public static int score = 0;
    public static int lives = 3;
    public Random random;
    public int speed;
    public ArrayList<Shield> shields;
    public ArrayList<Thunderbolt> thunders;
    public ArrayList<Wall> walls;



    public GameLogic() {
        this.mice = new ArrayList<>();
        this.random = new Random();
        this.speed = speed;
        this.shields = new ArrayList<>();
        this.thunders = new ArrayList<>();
        this.walls = new ArrayList<>();
    }

    public void initialize() {
        this.player = new Player(20, 300, "kitty.png");
        Enemy enemy1 = new Enemy(700,105, "mouse.png");
        Enemy enemy2 = new Enemy(900,255, "mouse2.png");
        mice.add(enemy1);
        mice.add(enemy2);



        Wall wall1 = new Wall(900,450,10,100);
        Wall wall2 = new Wall(500,500,10,100);
        Wall wall3 = new Wall(900,250,10,100);
        Wall wall4 = new Wall(700,100,10,100);

        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);



        spawnShield("shield.png");
        spawnThunderbolt("bolt.png");

    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return mice;
    }
    public ArrayList<Shield> getShields() {
        return shields;
    }
    public ArrayList<Thunderbolt> getThunders() {
        return thunders;
    }


    public void update() {
        if (lives <= 0) return; // Don't update if the game is over



        boolean collisionDetected = false;
        Enemy collidedEnemy = null;
        for (Enemy enemy : mice) {
            if (player.getX() < enemy.getX() + enemy.width &&
                    player.getX() + player.width > enemy.getX() &&
                    player.getY() < enemy.getY() + enemy.height &&
                    player.getY() + player.height > enemy.getY()) {

                collisionDetected = true;
                collidedEnemy = enemy;
                break;
            }
        }

        if (collisionDetected) {
            if (collidedEnemy.isShieldActive()) {
                collidedEnemy.reduceShieldCollisions();
                if (collidedEnemy.isShieldActive()) {
                    System.out.println("Enemy shield absorbed the collision!");
                } else {
                    System.out.println("Enemy shield expired!");
                }
            } else {
                mice.remove(collidedEnemy);
                lives--;
                System.out.println("zbývá " + lives + " životů");

                score++;
                System.out.println("Skóre: " + score);

                // Spawn a new mouse at a random location
                Enemy newEnemy = generateRandomEnemy();
                mice.add(newEnemy);


                spawnShield("shield.png");

                spawnThunderbolt("bolt.png");
            }
        }

        // Check for collisions with shields
        Shield collectedShield = null;
        for (Shield shield : shields) {
            for (Enemy enemy : mice) {
                if (enemy.getX() < shield.getX() + shield.width &&
                        enemy.getX() + enemy.width > shield.getX() &&
                        enemy.getY() < shield.getY() + shield.height &&
                        enemy.getY() + enemy.height > shield.getY()) {

                    // Handle shield collection
                    collectedShield = shield;
                    enemy.activateShield(2); // Activate shield with 2 collisions protection
                    break;
                }
            }
        }

        if (collectedShield != null) {
            shields.remove(collectedShield);
            System.out.println("Shield collected by enemy!");
        }


        if (!thunders.isEmpty()) {
            Thunderbolt thunderbolt = thunders.get(0);
            if (player.getX() < thunderbolt.getX() + thunderbolt.width &&
                    player.getX() + player.width > thunderbolt.getX() &&
                    player.getY() < thunderbolt.getY() + thunderbolt.height &&
                    player.getY() + player.height > thunderbolt.getY()) {


                thunders.remove(thunderbolt);
                System.out.println("Thunderbolt collected!");


                if (random.nextDouble() < 0.25) {
                    System.out.println("Instant win!");
                    lives = 0;
                }
            }
        }
        for (Enemy enemy: mice){
            Random random = new Random();
            boolean moveHorizontally = random.nextBoolean();

            if (moveHorizontally) {
                boolean moveRight = random.nextBoolean();
                if (moveRight) {
                    if (!checkPossibleEnemyMoveWithWalls(enemy, Direction.RIGHT)){
                        enemy.coords.x+=enemy.MOVE_CONSTANT;
                    }
                } else {
                    if (!checkPossibleEnemyMoveWithWalls(enemy, Direction.LEFT)){
                        enemy.coords.x-=enemy.MOVE_CONSTANT;
                    }

                }
            } else {
                boolean moveUp = random.nextBoolean();
                if (moveUp) {
                    if (!checkPossibleEnemyMoveWithWalls(enemy, Direction.UP)){
                        enemy.coords.y-=enemy.MOVE_CONSTANT;
                    }

                } else {
                    if (!checkPossibleEnemyMoveWithWalls(enemy, Direction.DOWN)){
                        enemy.coords.y+=enemy.MOVE_CONSTANT;
                    }
                }
            }

        }

    }

    public boolean checkPossibleEnemyMoveWithWalls(Enemy enemy, Direction direction){
        Rectangle newEnemyRectangleAfterMove = null;
        switch (direction){
            case UP -> {
                newEnemyRectangleAfterMove = new Rectangle(enemy.coords.x, enemy.coords.y - enemy.MOVE_CONSTANT, enemy.getWidth(), enemy.getHeight());
            }
            case DOWN -> {
                newEnemyRectangleAfterMove = new Rectangle(enemy.coords.x, enemy.coords.y + enemy.MOVE_CONSTANT, enemy.getWidth(), enemy.getHeight());
            }
            case LEFT -> {
                newEnemyRectangleAfterMove = new Rectangle(enemy.coords.x - enemy.MOVE_CONSTANT, enemy.coords.y, enemy.getWidth(), enemy.getHeight());
            }
            case RIGHT -> {
                newEnemyRectangleAfterMove = new Rectangle(enemy.coords.x + enemy.MOVE_CONSTANT, enemy.coords.y, enemy.getWidth(), enemy.getHeight());
            }
        }

        for (Wall wall: walls){
            if (newEnemyRectangleAfterMove.intersects(wall.getRectangle())){
                return true;
            }
        }
        return false;
    }


    private Enemy generateRandomEnemy() {
        int x = random.nextInt(1080);
        int y = random.nextInt(720);
        String[] enemyImages = {"mouse.png", "mouse2.png"};
        String image = enemyImages[random.nextInt(enemyImages.length)];
        return new Enemy(x, y, image);
    }


    public void spawnShield(String imagePath) {
        int x = random.nextInt(1080);
        int y = random.nextInt(720);
        Shield newShield = new Shield(x, y, imagePath);
        shields.add(newShield);
    }
    public void spawnThunderbolt(String imagePath) {
        int x = random.nextInt(1080); // Assuming screen width is 1080
        int y = random.nextInt(720);  // Assuming screen height is 720
        Thunderbolt newThunderbolt = new Thunderbolt(x, y, imagePath);
        thunders.add(newThunderbolt);
    }

    public ArrayList<Wall> getAllWalls() {
        return walls;
    }
}
