package src;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import static org.junit.Assert.assertEquals;


public class RealChallengeRoomScreenTests extends ApplicationTest {

    private RealChallengeRoomScreen realChallengeRoomScreen;
    private InventoryScreen inventoryScreen;
    private Player myPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        myPlayer = new Player("name", Weapon.SWORD,
                "./media/MainHeroesPNG/Knight/knight.png",
                1, 20, 100);
        realChallengeRoomScreen = new RealChallengeRoomScreen(myPlayer);
        realChallengeRoomScreen.start(primaryStage);
        inventoryScreen = new InventoryScreen(myPlayer);
        inventoryScreen.start(primaryStage);
    }

    @Test
    public void testAvatarImg() {
        // This ensures that the avatar does not change and is the correct avatar pictured!
        assertEquals("./media/MainHeroesPNG/Knight/knight.png", myPlayer.getAvatar());

        // This checks to make sure the avatar can still be changed.
        myPlayer.setAvatar("./media/MainHeroesPNG/Rogue/rogue.png");
        assertEquals("./media/MainHeroesPNG/Rogue/rogue.png", myPlayer.getAvatar());
    }

    @Test
    public void testHealthChallengeRoom() {
        // Testing to see if player can take damage in the challenge room.
        int oldHealth = myPlayer.getHealth();
        myPlayer.takeDamage(10);
        assertEquals(oldHealth - 10, myPlayer.getHealth());
    }
}

