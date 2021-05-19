package src;

public class Room {

    private Room door1;
    private Room door2;
    private Room door3;
    private String element;
    private int id;
    private boolean locked;
    private boolean cleared;

    public Room(Room door1, Room door2, Room door3, String element, int id, boolean cleared) {
        this.door1 = door1;
        this.door2 = door2;
        this.door3 = door3;
        this.element = element;
        this.id = id;
        this.locked = false;
        this.cleared = cleared;
    }

    public Room(Room door1, Room door2, Room door3, String element, int id) {
        this(door1, door2, door3, element, id, false);
    }

    public Room getDoor1() {
        return door1;
    }

    public void setDoor1(Room door1) {
        this.door1 = door1;
    }

    public Room getDoor2() {
        return door2;
    }

    public void setDoor2(Room door2) {
        this.door2 = door2;
    }

    public Room getDoor3() {
        return door3;
    }

    public void setDoor3(Room door3) {
        this.door3 = door3;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }
}
