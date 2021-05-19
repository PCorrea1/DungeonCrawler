package src;

public class Enemy {
    private String avatar;
    private int damagePoints;
    private int health;
    private boolean dead;
    private Item dropItem;

    /* Enemy Constructor */
    Enemy(int difficulty, int roomNum) {
        this.health = 20;
        this.dead = false;

        /*switch (difficulty) {
            case 1:
                damagePoints = 2;
                break;
            case 2:
                damagePoints = 4;
                break;
            case 3:
                damagePoints = 6;
                break;
            default:
                break;
        }*/

        damagePoints = 2 * difficulty;

        RandIntGen rand = new RandIntGen();
        int random = rand.getRandInt(1, 2);
        if (roomNum == 1 || roomNum == 2) {
            setAvatar("./media/MainEnemiesPNG/fireImp.png");
            if (random == 1) {
                setDropItem(new Item("Dragon Tooth", 0, 50, ""));
            } else {
                setDropItem(new IWeapon("Sword", 3, 4, ""));
            }
        } else if (roomNum == 3 || roomNum == 4) {
            setAvatar("./media/MainEnemiesPNG/ghost.png");
            if (random == 1) {
                setDropItem(new Item("Dragon Tooth", 0, 50, ""));
            } else {
                setDropItem(new Potion("health", "Health Potion", 10, 10, ""));
            }
        } else if (roomNum == 5 || roomNum == 6) {
            setAvatar("./media/MainEnemiesPNG/spider.png");
            if (random == 1) {
                setDropItem(new Item("Dragon Tooth", 0, 50, ""));
            } else {
                setDropItem(new Potion("strength", "Strength Potion", 5, 10, ""));
            }
        } else {
            random = rand.getRandInt(1, 6);
            this.health = 50;
            setAvatar("./media/MainEnemiesPNG/boss.png");
            switch (random) {
                case 1:
                    setDropItem(new IWeapon("Super Sword", 8, 30, ""));
                    break;
                case 2:
                    setDropItem(new IWeapon("Super Knife", 8, 30, ""));
                    break;
                case 3:
                    setDropItem(new IWeapon("Super Staff", 8, 30, ""));
                    break;
                case 4:
                    setDropItem(new Potion("health", "Super Health Potion", 20, 30, ""));
                    break;
                case 5:
                    setDropItem(new Potion("strength", "Super Strength Potion", 20, 30, ""));
                    break;
                case 6:
                    setDropItem(new Item("Super Dragon Tooth", 0, 100, ""));
                    break;
            }
        }
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }

    public Item getDropItem() {
        return this.dropItem;
    }

    public void setDropItem(Item item) {
        this.dropItem = item;
    }
}
