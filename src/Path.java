package src;

import java.util.LinkedList;

public class Path {

    private LinkedList<Room> myPlayerPath;
    private int number;
    private Room successRoom;

    public Path(int i) {
        successRoom = new Room(null, null, null, null, 99);
        this.myPlayerPath = new LinkedList<>();
        this.number = i;
        switch (i) {
        case 1:
            obtainPath1();
            break;
        case 2:
            obtainPath2();
            break;
        case 3:
            obtainPath3();
            break;
        case 4:
            obtainPath4();
            break;
        default:
            break;
        }
    }

    private void obtainPath1() {
        /* Path 1 Room (w/ Pointers) */
        Room room1 = new Room(null, null, null, null,  11);
        Room room2 = new Room(null, null, null, null,  12);
        Room room3 = new Room(null, null, null, null,  13);
        Room room4 = new Room(null, null, null, null,  14);
        Room room5 = new Room(null, null, null, null,  15);
        Room room6 = new Room(null, null, null, null,  16);
        Room finalBossRoom = new Room(successRoom, successRoom, successRoom, null,  17);

        myPlayerPath.add(room1);
        myPlayerPath.add(room2);
        myPlayerPath.add(room3);
        myPlayerPath.add(room4);
        myPlayerPath.add(room5);
        myPlayerPath.add(room6);
        myPlayerPath.add(finalBossRoom);

        // room 1 (return door (start) for now null, room 2, null/'locked')
        room1.setDoor2(room2);

        // room 2 (return door (room 1), room 3, room 5)
        room2.setDoor1(room1);
        room2.setDoor2(room3);
        room2.setDoor3(room5);

        // room 3 (return door (room2), room 4, room 6)
        room3.setDoor1(room2);
        room3.setDoor2(room4);
        room3.setDoor3(room6);

        // room 4 (return door (room3), room 5, null)
        room4.setDoor1(room3);
        room4.setDoor2(room5);

        // room 5 (return door (room2), return door (room 4), room 6
        room5.setDoor1(room2);
        room5.setDoor2(room4);
        room5.setDoor3(room6);

        // room 6 (return door (room3), return door (room 5), exit for now null
        room6.setDoor1(room3);
        room6.setDoor2(room5);
        room6.setDoor3(finalBossRoom);
    }

    private void obtainPath2() {
        /* Path 2 Room (w/ Pointers) */
        Room room1 = new Room(null, null, null, null,  21);
        Room room2 = new Room(null, null, null, null,  22);
        Room room3 = new Room(null, null, null, null,  23);
        Room room4 = new Room(null, null, null, null,  24);
        Room room5 = new Room(null, null, null, null,  25);
        Room room6 = new Room(null, null, null, null,  26);
        Room finalBossRoom = new Room(successRoom, successRoom, successRoom, null,  27);
        myPlayerPath.add(room1);
        myPlayerPath.add(room2);
        myPlayerPath.add(room3);
        myPlayerPath.add(room4);
        myPlayerPath.add(room5);
        myPlayerPath.add(room6);
        myPlayerPath.add(finalBossRoom);

        // room 1 (start/null, room 3, room 5)
        room1.setDoor2(room3);
        room1.setDoor3(room5);

        // room 2 (room 4, room 3, end/null)
        room2.setDoor1(room4);
        room2.setDoor2(room3);
        room2.setDoor3(finalBossRoom);

        // room 3 (room1, room2, room 4)
        room3.setDoor1(room1);
        room3.setDoor2(room4);
        room3.setDoor3(room2);

        // room 4 (room2, room3, room6)
        room4.setDoor1(room3);
        room4.setDoor2(room2);
        room4.setDoor3(room6);

        // room 5 (room1, room6, null)
        room5.setDoor1(room1);
        room5.setDoor2(room6);

        // room 6 (room2, room5, room4)
        room6.setDoor1(room2);
        room6.setDoor2(room5);
        room6.setDoor3(room4);
    }

    private void obtainPath3() {
        /* Path 3 Room (w/ Pointers) */
        Room room1 = new Room(null, null, null, null,  31);
        Room room2 = new Room(null, null, null, null,  32);
        Room room3 = new Room(null, null, null, null,  33);
        Room room4 = new Room(null, null, null, null,  34);
        Room room5 = new Room(null, null, null, null,  35);
        Room room6 = new Room(null, null, null, null,  36);
        Room finalBossRoom = new Room(successRoom, successRoom, successRoom, null,  37);
        myPlayerPath.add(room1);
        myPlayerPath.add(room2);
        myPlayerPath.add(room3);
        myPlayerPath.add(room4);
        myPlayerPath.add(room5);
        myPlayerPath.add(room6);
        myPlayerPath.add(finalBossRoom);

        // room 1 (start, room5, room4)
        room1.setDoor2(room5);
        room1.setDoor3(room4);

        // room 2 (room6, room4, room3)
        room2.setDoor1(room6);
        room2.setDoor2(room4);
        room2.setDoor3(room3);

        // room 3 (room2, room6, locked)
        room3.setDoor1(room2);
        room3.setDoor2(room6);

        // room 4 (room2, room5, room1)
        room4.setDoor1(room2);
        room4.setDoor2(room5);
        room4.setDoor3(room1);

        // room 5 (room1, room6, locked)
        room5.setDoor1(room1);
        room5.setDoor3(room6);

        // room 6 (room2, room3, end)
        room6.setDoor1(room2);
        room6.setDoor2(room3);
        room6.setDoor3(finalBossRoom);
    }

    private void obtainPath4() {
        /* Path 4 Room (w/ Pointers) */
        Room room1 = new Room(null, null, null, null,  41);
        Room room2 = new Room(null, null, null, null,  42);
        Room room3 = new Room(null, null, null, null,  43);
        Room room4 = new Room(null, null, null, null,  44);
        Room room5 = new Room(null, null, null, null,  45);
        Room room6 = new Room(null, null, null, null,  46);
        Room finalBossRoom = new Room(successRoom, successRoom, successRoom, null,  47);
        myPlayerPath.add(room1);
        myPlayerPath.add(room2);
        myPlayerPath.add(room3);
        myPlayerPath.add(room4);
        myPlayerPath.add(room5);
        myPlayerPath.add(room6);
        myPlayerPath.add(finalBossRoom);

        // room 1 (start, room4, room5)
        room1.setDoor2(room4);
        room1.setDoor3(room5);

        // room 2 (room3, room5, locked)
        room2.setDoor1(room3);
        room2.setDoor3(room5);

        // room 3 (room2, room6, locked)
        room3.setDoor1(room2);
        room3.setDoor2(room6);

        // room 4 (room1, room5, room6)
        room4.setDoor1(room1);
        room4.setDoor2(room5);
        room4.setDoor3(room6);

        // room 5 (room4, room1, room2)
        room5.setDoor1(room4);
        room5.setDoor2(room1);
        room5.setDoor3(room2);

        // room 6 (room3, room4, end)
        room6.setDoor1(room3);
        room6.setDoor2(room4);
        room6.setDoor3(finalBossRoom);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LinkedList<Room> getMyPlayerPath() {
        return myPlayerPath;
    }

    public void setMyPlayerPath(LinkedList<Room> myPlayerPath) {
        this.myPlayerPath = myPlayerPath;
    }

    public Room getSuccessRoom() {
        return successRoom;
    }

    public void setSuccessRoom(Room successRoom) {
        this.successRoom = successRoom;
    }

    /* Notes
            this test can test that the user succesfully goes through six rooms
            public void testDoors() {
               clickOn("door")
               clickOn(""}
               times 4
               assertEquals(myPlayer.getRoom(), roomID)
            }

            types of tests
            - paths 4-6
            - random 2
            - avatar 1
     */
    //    /**
    //     * Method for accessing rooms within the Path.
    //     */
    //    public void getRoom() {
    ////        int roomIndex = myPlayerPath.indexOf();
    ////        return myPlayerPath.get(roomIndex);
    //    }

}
