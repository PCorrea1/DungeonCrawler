package src;

//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;

//import java.awt.*;

import static org.junit.Assert.assertEquals;

public class DefeatScreenTests extends ApplicationTest {
    private DefeatScreen defeatScreen;
    private Player myPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        myPlayer = new Player("name", Weapon.SWORD,
                "./media/MainHeroesPNG/Knight/knight.png",
                1, 20, 100);
        defeatScreen = new DefeatScreen(myPlayer);
        defeatScreen.start(primaryStage);
    }

    @Test
    public void testPlayButton() {
        Button button = defeatScreen.getStartGame();
        FxAssert.verifyThat(button, LabeledMatchers.hasText("Play"));
        clickOn("Play");
        assertEquals(1, myPlayer.getDifficulty());
        assertEquals(20, myPlayer.getHealth());
        assertEquals(100, myPlayer.getCoins());
    }

    @Test
    public void testQuitButton() {
        Button button = defeatScreen.getQuitGame();
        FxAssert.verifyThat(button, LabeledMatchers.hasText("Quit"));
        clickOn("Quit");
        assertEquals(1, myPlayer.getDifficulty());
        assertEquals(20, myPlayer.getHealth());
        assertEquals(100, myPlayer.getCoins());
    }

    @Test
    public void testAvatarImg() {
        // This ensures that the avatar does not change and is the correct avatar pictured!
        assertEquals("./media/MainHeroesPNG/Knight/knight.png", myPlayer.getAvatar());
    }
}