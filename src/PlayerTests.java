package src;


import org.junit.Test;
import static org.junit.Assert.*;

public class  PlayerTests {
    private Player myPlayer = new Player();
    private String name;
    private Enum weapon;
    private String avatar;
    private int difficulty;
    private int health;
    private int coins;
    private Path path;
    private Room room;

    @Test
    public void testNoArgsName() {
        String playerName = "name";
        assertEquals(playerName, myPlayer.getName());
    }
    @Test
    public void testNoArgsAvatar() {
        String avatar = "./media/con2.png";
        assertEquals(avatar, myPlayer.getAvatar());
    }
    @Test
    public void testNoArgsCoins() {
        int coins = -1;
        assertEquals(coins, myPlayer.getCoins());
    }
    @Test
    public void testNoArgsHealth() {
        int health = -1;
        assertEquals(-1, myPlayer.getHealth());
    }
    @Test
    public void testNoArgsWeapon() {
        Enum playerWeapon = null;
        assertEquals(weapon, myPlayer.getWeapon());
    }

}
