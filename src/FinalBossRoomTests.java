package src;

import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class FinalBossRoomTests extends ApplicationTest {
    private RoomScreen screen;
    private Player myPlayer = new Player("name", Weapon.SWORD,
                "./media/MainHeroesPNG/Knight/knight.png",
                        1, 20, 100);

    private Enemy enemy;


    @Override
    public void start(Stage primaryStage) throws Exception {
        myPlayer.setPath(new Path(1));
        myPlayer.setCurrentRoom(myPlayer.getPath().getMyPlayerPath().getLast());
        screen = new RoomScreen(myPlayer);
        screen.start(primaryStage);
        enemy = screen.getEnemy();
    }

    @Test
    public void testInstantiatedInProperRoom() {
        Room successRoom = new Room(null, null, null, null, 99);
        assertEquals(myPlayer.getCurrentRoom().getId(), new Room(successRoom, successRoom, successRoom, null,  17).getId());
    }

    @Test
    public void testBossPicture() {
        // testing final boss
        String expected = enemy.getAvatar();
        assertEquals(expected, "./media/MainEnemiesPNG/boss.png");
    }

    @Test
    public void testBossInitialHealth() {
        // Testing that the final boss has an initial health of 50 (boosted from 20).
        int original = enemy.getHealth();
        assertEquals(original, 50);
    }

    @Test
    public void testBossTakeDamage() {
        // Testing that the final boss can take damage.
        int original = enemy.getHealth();
        enemy.takeDamage(10);
        assertEquals(original - 10, enemy.getHealth());
    }

    @Test
    public void testBossSetDead() {
        // Testing that the final boss can take damage.
        int original = enemy.getHealth();
        enemy.setDead(true);
        assertEquals(true, enemy.isDead());
    }

    @Test
    public void testPlayerStillTakesDamageBack() {
        // Testing that the player boss can take damage.
        int original = myPlayer.getHealth();
        myPlayer.takeDamage(2);
        assertEquals(original - 2, myPlayer.getHealth());
    }
}
