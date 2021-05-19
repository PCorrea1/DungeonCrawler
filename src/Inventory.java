package src;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Inventory {
    private ArrayList<Item> weapons;
    private ArrayList<Item> potions;
    private int inventorySize;

    public Inventory() {
        weapons = new ArrayList<>(10);
        potions = new ArrayList<>(10);
        inventorySize = 0;
    }

    /*public Inventory(ArrayList<Item> weapons, ArrayList<Item> potions) {
        setWeapons(weapons);
        setPotions(potions);
        setInventorySize();
    }*/

    public void addItem(Item newItem) {
        this.inventorySize++;
        newItem.setItemIDForInventory(this.inventorySize);
        if (newItem instanceof IWeapon) {
            weapons.add(newItem);
        } else if (newItem instanceof Potion) {
            potions.add(newItem);
        }
        setInventorySize();
    }

    public Item removeItem(Item item) {
        ArrayList<Item> arrayToSearch = null;
        if (item instanceof IWeapon) {
            arrayToSearch = weapons;
        } else if (item instanceof Potion) {
            arrayToSearch = potions;
        }

        if (arrayToSearch != null) {
            int index = arrayToSearch.indexOf(item);
            if (index == -1) {
                throw new NoSuchElementException("Item does not exist within ArrayList.");
            } else {
                this.inventorySize--;
                return arrayToSearch.remove(index);
            }
        } else {
            return null;
        }
    }

    public Item getItem(Item item) {
        ArrayList<Item> arrayToSearch = null;
        if (item instanceof IWeapon) {
            arrayToSearch = weapons;
        } else if (item instanceof Potion) {
            arrayToSearch = potions;
        }

        if (arrayToSearch != null) {
            int index = arrayToSearch.indexOf(item);
            if (index == -1) {
                throw new NoSuchElementException("Item does not exist within ArrayList.");
            }
            return arrayToSearch.get(index);
        }
        return null;
    }

    public ArrayList<Item> getWeapons() {
        return this.weapons;
    }

    public void setWeapons(ArrayList<Item> weapons) {
        if (weapons == null) {
            throw new IllegalArgumentException("Cannot have null weapons list in Inventory.");
        } else {
            this.weapons = weapons;
            setInventorySize();
        }
    }

    public ArrayList<Item> getPotions() {
        return this.potions;
    }

    public void setPotions(ArrayList<Item> potions) {
        if (potions == null) {
            throw new IllegalArgumentException("Cannot have null potions list in Inventory.");
        } else {
            this.potions = potions;
            setInventorySize();
        }
    }

    public int getInventorySize() {
        return inventorySize;
    }

    // overloaded method, not useful (yet)
    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public void setInventorySize() {
        this.inventorySize = this.weapons.size() + this.potions.size();
    }
}
