package src;

import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WinScreenTests extends ApplicationTest {
    private WinScreen winScreen;
    private Player myPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        myPlayer = new Player("name", Weapon.SWORD,
                "./media/MainHeroesPNG/Knight/knight.png",
                1, 20, 100);
        winScreen = new WinScreen(myPlayer);
        winScreen.start(primaryStage);
    }

    @Test
    public void testPlayButton() {
        Button button = winScreen.getStartGame();
        FxAssert.verifyThat(button, LabeledMatchers.hasText("Play"));
        clickOn("Play");
        assertEquals(1, myPlayer.getDifficulty());
        assertEquals(20, myPlayer.getHealth());
        assertEquals(100, myPlayer.getCoins());
    }

    @Test
    public void testQuitButton() {
        Button button = winScreen.getQuitGame();
        FxAssert.verifyThat(button, LabeledMatchers.hasText("Quit"));
        clickOn("Quit");
        assertEquals(1, myPlayer.getDifficulty());
        assertEquals(20, myPlayer.getHealth());
        assertEquals(100, myPlayer.getCoins());
    }
}
