package src;

public class Player {
    private String name;
    private Enum weapon;
    private IWeapon myWeapon;
    private String avatar;
    private int difficulty;
    private int health;
    private int coins;
    private Path path;
    private Room currentRoom;
    private Room prevRoom;
    private Inventory inventory;
    private Room prevPrevRoom;
    private int damageDealt;
    private int strengthPotsUsed;
    private int healthPotsUsed;
    private int monstersKilled;

    /* Player Constructor */
    Player(String name, Enum weapon, String avatar, int difficulty, int health, int coins) {
        this.name = name;
        this.weapon = weapon;
        this.avatar = avatar;
        this.difficulty = difficulty;
        this.health = health;
        this.coins = coins;
        this.path = null;
        this.inventory = new Inventory();
        this.myWeapon = null;
        this.damageDealt = 0;
        this.strengthPotsUsed = 0;
        this.healthPotsUsed = 0;
        this.monstersKilled = 0;

        /*if (weapon == null) {
            myWeapon = null;
        } else if (weapon.equals(Weapon.SWORD)) {
            myWeapon = new IWeapon("Sword", 2, 2, "");
        } else if (weapon.equals(Weapon.KNIFE)) {
            setMyWeapon(new IWeapon("Knife", 2, 2, ""));
        } else if (weapon.equals(Weapon.STAFF)) {
            setMyWeapon(new IWeapon("Staff", 2, 2, ""));
        }
        System.out.println("myWeapon: " + myWeapon);
        String weaponName, int impactDelta, int coinsValue, String itemImageURL)*/
    }

    Player() {
        this("name", null, "./media/con2.png", 0, -1, -1);
    }

    public void takeDamage(int damagePoints) {
        this.health -= damagePoints;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Enum weapon) {
        this.weapon = weapon;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getPrevRoom() {
        return prevRoom;
    }

    public void setPrevRoom(Room prevRoom) {
        this.prevRoom = prevRoom;
    }

    public Room getPrevPrevRoom() {
        return prevPrevRoom;
    }

    public void setPrevPrevRoom(Room prevPrevRoom) {
        this.prevPrevRoom = prevPrevRoom;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public IWeapon getMyWeapon() {
        return myWeapon;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }

    public int getStrengthPotsUsed() {
        return strengthPotsUsed;
    }

    public void setStrengthPotsUsed(int strengthPotsUsed) {
        this.strengthPotsUsed = strengthPotsUsed;
    }

    public int getHealthPotsUsed() {
        return healthPotsUsed;
    }

    public void setHealthPotsUsed(int healthPotsUsed) {
        this.healthPotsUsed = healthPotsUsed;
    }

    public int getMonstersKilled() {
        return monstersKilled;
    }

    public void setMonstersKilled(int monstersKilled) {
        this.monstersKilled = monstersKilled;
    }

    public void setMyWeapon(IWeapon myWeapon) {
        if (inventory.getWeapons().size() != 0) {
            inventory.removeItem(this.myWeapon);
        }

        this.myWeapon = myWeapon;
        this.inventory.addItem(myWeapon);
    }

    public IWeapon setMyWeaponType(Enum myWeapon) {
        if (myWeapon.equals(Weapon.SWORD)) {
            return new IWeapon("Sword", 3, 4, "");
        } else if (myWeapon.equals(Weapon.KNIFE)) {
            return new IWeapon("Knife", 2, 2, "");
        } else if (myWeapon.equals(Weapon.STAFF)) {
            return new IWeapon("Staff", 1, 5, "");
        }

        return null;
    }

    /*public void setPath(int i) {
        this.path = new Path(i);
    }*/
}
