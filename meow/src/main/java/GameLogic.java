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



    public GameLogic() {
        this.mice = new ArrayList<>();
        this.random = new Random();
        this.speed = speed;
        this.shields = new ArrayList<>();
        this.thunders = new ArrayList<>();
    }

    public void initialize() {
        this.player = new Player(20, 300, "kitty.png");
        Enemy enemy1 = new Enemy(700,105, "mouse.png");
        Enemy enemy2 = new Enemy(900,255, "mouse2.png");
        mice.add(enemy1);
        mice.add(enemy2);

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

        // Update enemy positions to chase the player
        for (Enemy enemy : mice) {
            //chasePlayer(enemy, player);
        }

        // Check for collisions with enemies
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

                // Spawn a new shield at a random location
                spawnShield("shield.png");

                // Spawn a new thunderbolt at a random location
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

        // Check for collisions with thunderbolts
        if (!thunders.isEmpty()) {
            Thunderbolt thunderbolt = thunders.get(0); // Get the first thunderbolt
            if (player.getX() < thunderbolt.getX() + thunderbolt.width &&
                    player.getX() + player.width > thunderbolt.getX() &&
                    player.getY() < thunderbolt.getY() + thunderbolt.height &&
                    player.getY() + player.height > thunderbolt.getY()) {

                // Handle thunderbolt collection
                thunders.remove(thunderbolt);
                System.out.println("Thunderbolt collected!");

                // 25% chance to win instantly
                if (random.nextDouble() < 0.25) {
                    System.out.println("Instant win!");
                    lives = 0; // This ends the game
                }
            }
        }
    }
    private Enemy generateRandomEnemy() {
        int x = random.nextInt(1080);
        int y = random.nextInt(720);
        String[] enemyImages = {"mouse.png", "mouse2.png"};
        String image = enemyImages[random.nextInt(enemyImages.length)];
        return new Enemy(x, y, image);
    }
    /*private void chasePlayer(Enemy enemy, Player player) {
        int speed = 2;

        if (player.getX() > enemy.coords.x) {
            enemy.coords.x += speed;
        } else if (player.getX() < enemy.coords.x) {
            enemy.coords.x -= speed;
        }

        if (player.getY() > enemy.coords.y) {
            enemy.coords.y += speed;
        } else if (player.getY() < enemy.coords.y) {
            enemy.coords.y -= speed;
        }

        enemy.coords.x = Math.max(0, Math.min(enemy.coords.x, 1080 - enemy.width));
        enemy.coords.y = Math.max(0, Math.min(enemy.coords.y, 720 - enemy.height));
    }*/
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
}
