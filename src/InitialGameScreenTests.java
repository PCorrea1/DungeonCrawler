package src;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

public class InitialGameScreenTests extends ApplicationTest {
    private InitialGameScreen initialGameScreen;
    private Player myPlayer = new Player("name", Weapon.SWORD,
            "./media/MainHeroesPNG/Knight/knight.png",
            1, 20, 100);
    private InitialConfigurationScreenTests initialConfigurationScreenTests;


    @Override
    public void start(Stage primaryStage) throws Exception {

    }
    @Test
    public void testHealthDataIsPassed() throws Exception {
        int health = myPlayer.getHealth();
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (health == 20 || health == 15 || health == 10) {
                counter++;
                break;
            }
        }
        assertEquals(counter, 1);
    }
    @Test
    public void testCoinsDataIsPassed() throws Exception {
        int coins = myPlayer.getCoins();
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (coins == 100 || coins == 75 || coins == 50) {
                counter++;
                break;
            }
        }
        assertEquals(counter, 1);
    }

    @Test
    public void testNameValueIsRead() throws Exception {
        String name = myPlayer.getName();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }
    @Test
    public void testWeaponValueIsRead() {
        Enum weapon = myPlayer.getWeapon();
        assertNotNull(weapon);
        assertTrue(weapon.equals(Weapon.KNIFE) || weapon.equals(Weapon.STAFF)
                || weapon.equals(Weapon.SWORD));
    }
    public InitialGameScreen getInitialGameScreen() {
        return initialGameScreen;
    }

    public void setInitialGameScreen(InitialGameScreen initialGameScreen) {
        this.initialGameScreen = initialGameScreen;
    }
}
