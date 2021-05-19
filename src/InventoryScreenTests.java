package src;

import javafx.scene.input.KeyCode;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InventoryScreenTests extends ApplicationTest {
    private InventoryScreen inventoryScreen;
    private Player myPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        myPlayer = new Player("name", Weapon.SWORD,
                "./media/MainHeroesPNG/Knight/knight.png",
                1, 20, 100);
        inventoryScreen = new InventoryScreen(myPlayer);
        inventoryScreen.start(primaryStage);
    }

    @Test
    public void testAvatarImg() {
        // This ensures that the avatar does not change and is the correct avatar pictured!
        assertEquals("./media/MainHeroesPNG/Knight/knight.png", myPlayer.getAvatar());
    }

    @Test
    public void testAddNewPotion() {
        int expected = myPlayer.getInventory().getInventorySize();
        Potion health = new Potion("health", "Health Potion", 10, 10, "");
        myPlayer.getInventory().addItem(health);
        assertEquals(expected + 1, myPlayer.getInventory().getInventorySize());
    }

    @Test
    public void testAddNewIWeapon() {
        IWeapon knife = new IWeapon("Knife", 2, 2, "");
        myPlayer.getInventory().addItem(knife);
        myPlayer.setMyWeapon(knife);
        assertEquals(myPlayer.getMyWeapon(), myPlayer.getInventory().getItem(knife));
    }

    @Test
    public void testRemoveWeapon() {
        int oldCount = myPlayer.getInventory().getInventorySize();

        IWeapon knife = new IWeapon("Knife", 2, 2, "");
        myPlayer.getInventory().addItem(knife);
        assertEquals(oldCount + 1, myPlayer.getInventory().getInventorySize());

        myPlayer.getInventory().removeItem(knife);
        assertEquals(oldCount, myPlayer.getInventory().getInventorySize());
    }

    @Test
    public void testRemovePotion() {
        int oldCount = myPlayer.getInventory().getInventorySize();

        Potion health = new Potion("health", "Health Potion", 10, 10, "");
        myPlayer.getInventory().addItem(health);
        assertEquals(oldCount + 1, myPlayer.getInventory().getInventorySize());

        myPlayer.getInventory().removeItem(health);
        assertEquals(oldCount, myPlayer.getInventory().getInventorySize());
    }

    @Test
    public void testNumOfItems() {
        int oldCount = myPlayer.getInventory().getInventorySize();
        IWeapon knife = new IWeapon("Knife", 2, 2, "");
        myPlayer.getInventory().addItem(knife);
        myPlayer.getInventory().addItem(new Potion("health", "Health Potion", 10, 10, ""));
        assertEquals(oldCount + 2, myPlayer.getInventory().getInventorySize());
    }

    @Test
    public void testHealthCanStillBeModified() {
        int health = myPlayer.getHealth();
        myPlayer.takeDamage(1);
        assertEquals(health - 1, myPlayer.getHealth());
    }

    @Test
    public void testWeaponComboBox() {
        clickOn("Select a Weapon");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        assertEquals(Weapon.SWORD, myPlayer.getWeapon());
    }
}