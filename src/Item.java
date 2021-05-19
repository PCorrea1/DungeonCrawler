package src;

public class Item {
    private String itemName;
    private int impactDelta;
    private int coinsValue;
    private int itemIDForInventory;
    private String itemImageURL;

    public Item(String itemName, int impactDelta, int coinsValue, String itemImageURL) {
        this(itemName, impactDelta, coinsValue);
        this.itemImageURL = itemImageURL;
    }

    public Item(String itemName, int impactDelta, int coinsValue) {
        this.itemName = itemName;
        this.impactDelta = impactDelta;
        this.coinsValue = coinsValue;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getImpactDelta() {
        return impactDelta;
    }

    public void setImpactDelta(int impactDelta) {
        this.impactDelta = impactDelta;
    }

    public int getCoinsValue() {
        return coinsValue;
    }

    public void setCoinsValue(int coinsValue) {
        this.coinsValue = coinsValue;
    }

    public int getItemIDForInventory() {
        return itemIDForInventory;
    }

    public void setItemIDForInventory(int itemIDForInventory) {
        this.itemIDForInventory = itemIDForInventory;
    }

    public String getItemImageURL() {
        return itemImageURL;
    }

    public void setItemImageURL(String itemImageURL) {
        this.itemImageURL = itemImageURL;
    }

    public String toString() {
        return this.itemName;
    }
}
