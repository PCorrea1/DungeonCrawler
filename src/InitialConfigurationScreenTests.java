package src;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.Assert.assertEquals;


public class InitialConfigurationScreenTests extends ApplicationTest {

    private InitialConfigurationScreen initConfig;
    private Player myPlayer;

    // not working
    //    @Before
    //    public void startup() throws Exception {
    //        initConfig = new InitialConfigurationScreen();
    //    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initConfig = new InitialConfigurationScreen();
        initConfig.start(primaryStage);
    }

    @Test
    public void testBeginnerButton() {
        Button button = initConfig.getBeginner();
        FxAssert.verifyThat(button, LabeledMatchers.hasText("Beginner"));
        clickOn("Beginner");
        assertEquals(1, myPlayer.getDifficulty());
        assertEquals(20, myPlayer.getHealth());
        assertEquals(100, myPlayer.getCoins());
    }

    @Test
    public void testIntermediateButton() {
        Button button = initConfig.getIntermediate();
        FxAssert.verifyThat(button, LabeledMatchers.hasText("Intermediate"));
        clickOn("Intermediate");
        assertEquals(2, myPlayer.getDifficulty());
        assertEquals(15, myPlayer.getHealth());
        assertEquals(75, myPlayer.getCoins());
    }

    @Test
    public void testAvatarImg() {
        clickOn("SELECT YOUR WEAPON");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Update Avatar");
        assertEquals("./media/MainHeroesPNG/Knight/knight.png", myPlayer.getAvatar());
    }


    @Test
    public void testWeaponComboBox() {
        clickOn("SELECT YOUR WEAPON");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        assertEquals(Weapon.SWORD, myPlayer.getWeapon());
    }
    /*@Test
    public void testAvatar() {
        clickOn("SELECT YOUR WEAPON");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        assertEquals("./media/MainHeroesPNG/Knight/knight.png", myPlayer.getAvatar());
    }*/


    // not working
    //    @Test
    //    public void testPlayerNameTextField() {
    //              TextField field = initConfig.getPlayerName();
    //              clickOn("Player Name:");
    //              type(KeyCode.valueOf("Natalie"));
    //              FxAssert.verifyThat((NodeQuery) field, LabeledMatchers.hasText("Natalie"));
    //    }

    public InitialConfigurationScreen getInitConfig() {
        return initConfig;
    }

    public void setInitConfig(InitialConfigurationScreen initConfig) {
        this.initConfig = initConfig;
    }

}

