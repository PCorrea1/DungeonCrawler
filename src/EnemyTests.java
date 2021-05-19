package src;

//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertTrue;

//import javafx.stage.Stage;
import org.junit.Test;
//import src.InitialGameScreen;
//import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class EnemyTests {

    private Enemy enemy = new Enemy(2, 2);
    private String avatar;
    private int damagePoints;
    private int health;
    private boolean dead;

    @Test
    public void testAvatar() {
        avatar = "./media/MainEnemiesPNG/fireImp.png";
        assertEquals(avatar, enemy.getAvatar());
    }

    @Test
    public void testDamagePoints() {
        int expected = 4;
        assertEquals(enemy.getDamagePoints(), expected);
    }

    @Test
    public void testTakeDamage() {
        int damagePoints = 4;
        int health = 20;
        enemy.takeDamage(4);
        assertEquals(enemy.getHealth(), health - 4);
    }
}