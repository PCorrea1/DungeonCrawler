package src;

public class Potion extends Item {
    // attack or health potion type
    private String potionType; // attack or health

    public Potion(String potionType, String potionName, int healthDelta,
                  int coinsValue, String itemImageURL) {
        super(potionName, healthDelta, coinsValue, itemImageURL);
        this.potionType = potionType;
    }

    public String getPotionType() {
        return potionType;
    }

    public void setPotionType(String potionType) {
        this.potionType = potionType;
    }
}
