
package src;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class InitialGameScreen extends Application {
    private Player myPlayer;
    private Image heroImage;
    private Node hero;
    private static final double W = 1200;
    private static final double H = 700;

    /*
    boolean running;
    boolean goNorth, goSouth, goEast, goWest;
    */

    public InitialGameScreen(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public InitialGameScreen() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* initialization things */
        StackPane sP = new StackPane();
        primaryStage.setTitle("Initial Game Screen");
        Path[] sequenceRandomPaths = preparePaths(randomizePaths(generateRandIndexPathSequence()));
        /* Choose Your Door Text */
        Text text = new Text("Choose Your Fate...");
        text.setFont(Font.font(String.valueOf(text),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 75));
        text.setFill(Paint.valueOf("Red"));
        text.setStroke(Paint.valueOf("Black"));
        FadeTransition fd = new FadeTransition();
        fd.setDuration(Duration.millis(1000));
        fd.setFromValue(10);
        fd.setToValue(0.1);
        fd.setCycleCount(1000);
        fd.setAutoReverse(true);
        fd.setNode(text);
        fd.play();
        /* set Transition */
        Rectangle blackscreen = new Rectangle();
        blackscreen.setX(50);
        blackscreen.setY(50);
        blackscreen.setWidth(1200);
        blackscreen.setHeight(700);
        blackscreen.setArcWidth(20);
        blackscreen.setArcHeight(20);
        FadeTransition fd2 = new FadeTransition();
        fd2.setDuration(Duration.millis(12500));
        fd2.setFromValue(10);
        fd2.setToValue(0.1);
        fd2.setCycleCount(1);
        fd2.setAutoReverse(true);
        fd2.setNode(blackscreen);
        fd2.play();
        heroImage = new Image(myPlayer.getAvatar());
        hero = new ImageView(heroImage);

        /* set background */
        ImageView background = new ImageView(new Image("./media/game_background_3.png"));
        background.setFitWidth(1200);
        background.setFitHeight(700);
        /* create dungeon doors and place in panes*/
        ImageView door1 = new ImageView(new Image("./media/door5.png"));
        // set on action -- once touching door && click:
        // have random number [1,4]
        // pass chosen path to player
        // player.setPath(randomNumber)
        // -- from there, the rooms are generated in the constructor of path
        door1.setOnMouseClicked(event -> {
            myPlayer.setPath(sequenceRandomPaths[0]);
            System.out.println("User has selected Path " + myPlayer.getPath().getNumber());
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setCurrentRoom(myPlayer.getPath().getMyPlayerPath().getFirst());
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ImageView door2 = new ImageView(new Image("./media/door5.png"));
        door2.setOnMouseClicked(event -> {
            myPlayer.setPath(sequenceRandomPaths[1]);
            System.out.println("User has selected Path " + myPlayer.getPath().getNumber());
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setCurrentRoom(myPlayer.getPath().getMyPlayerPath().getFirst());
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ImageView door3 = new ImageView(new Image("./media/door5.png"));
        door3.setOnMouseClicked(event -> {
            myPlayer.setPath(sequenceRandomPaths[2]);
            System.out.println("User has selected Path " + myPlayer.getPath().getNumber());
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setCurrentRoom(myPlayer.getPath().getMyPlayerPath().getFirst());
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ImageView door4 = new ImageView(new Image("./media/door5.png"));
        door4.setOnMouseClicked(event -> {
            myPlayer.setPath(sequenceRandomPaths[3]);
            System.out.println("User has selected Path " + myPlayer.getPath().getNumber());
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setCurrentRoom(myPlayer.getPath().getMyPlayerPath().getFirst());
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        HBox invHbox = new HBox();
        invHbox.setAlignment(Pos.CENTER_RIGHT);
        VBox vBox1 = new VBox(80);
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        /* create inventory box and place in pane*/
        ImageView invBox = new ImageView(new Image("./media/rectangle.png"));
        invBox.setFitWidth(500);
        invBox.setFitHeight(180);
        ImageView coinsGraphic = new ImageView(new Image("media/coins.png"));
        coinsGraphic.setFitWidth(30);
        coinsGraphic.setFitHeight(30);
        ImageView healthGraphic = new ImageView(new Image("./media/heart.png"));
        healthGraphic.setFitWidth(30);
        healthGraphic.setFitHeight(30);
        HBox hBox3 = new HBox();
        /* create money bag and health indicator and place in pane*/
        Label healthLabel = new Label("HEALTH: " + myPlayer.getHealth() + "hp");
        healthLabel.setGraphic(healthGraphic);
        Font font = Font.font("Pickwick", FontWeight.BOLD,
                FontPosture.REGULAR, 25);
        healthLabel.setFont(font);
        Label coinsLabel = new Label("COINS: " + myPlayer.getCoins() + "gp");
        coinsLabel.setGraphic(coinsGraphic);
        coinsLabel.setFont(font);
        Button invButton = initializeInventoryButton(primaryStage);
        VBox vbox4 = new VBox();
        StackPane stackPane = new StackPane();
        /* modifying panes */
        hbox1.setPadding(new Insets(-60, 0, -80, 80));
        hbox2.setPadding(new Insets(0, 0, -40, 0));
        hBox3.setPadding(new Insets(-60, -30, 90, 0));
        vbox4.setPadding(new Insets(40, 0, 0, 60));
        vBox1.setAlignment(Pos.CENTER);
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox2.setAlignment(Pos.TOP_CENTER);
        hBox3.setAlignment(Pos.BOTTOM_RIGHT);
        /* populating panes */
        invHbox.getChildren().addAll(invButton);
        vbox4.getChildren().addAll(healthLabel, coinsLabel);
        stackPane.getChildren().addAll(invBox, vbox4);
        hbox1.getChildren().addAll(door1, door2, door3, door4);
        hbox2.getChildren().addAll(text);
        hBox3.getChildren().addAll(stackPane);
        vBox1.getChildren().addAll(hbox2, hbox1, invHbox, hBox3);
        sP.getChildren().addAll(background, blackscreen, vBox1, hero);

        /* adding to main stage */

        primaryStage.setScene(new Scene(sP));
        primaryStage.show();
    }

    public int generateRandIndexPathSequence() {
        RandIntGen rig = new RandIntGen();
        int randNum = rig.getRandInt(0, 24);
        return randNum;
    }

    public int[] randomizePaths(int randNum) {
        /* initialize Paths to be assigned in random order for each door */

        int[][] nums = new int[24][4];

        nums[0] = new int[]{1, 2, 3, 4};
        nums[1] = new int[]{1, 2, 4, 3};

        nums[2] = new int[]{1, 3, 2, 4};
        nums[3] = new int[]{1, 3, 4, 2};

        nums[4] = new int[]{1, 4, 3, 2};
        nums[5] = new int[]{1, 4, 2, 3};

        nums[6] = new int[]{2, 1, 3, 4};
        nums[7] = new int[]{2, 1, 4, 3};

        nums[8] = new int[]{2, 3, 1, 4};
        nums[9] = new int[]{2, 3, 4, 1};

        nums[10] = new int[]{2, 4, 3, 1};
        nums[11] = new int[]{2, 4, 1, 3};

        nums[12] = new int[]{3, 4, 2, 1};
        nums[13] = new int[]{3, 4, 1, 2};

        nums[14] = new int[]{3, 2, 1, 4};
        nums[15] = new int[]{3, 2, 4, 1};

        nums[16] = new int[]{3, 1, 2, 4};
        nums[17] = new int[]{3, 1, 4, 2};

        nums[18] = new int[]{4, 2, 3, 1};
        nums[19] = new int[]{4, 2, 1, 3};

        nums[20] = new int[]{4, 1, 3, 2};
        nums[21] = new int[]{4, 1, 2, 3};

        nums[22] = new int[]{4, 3, 2, 1};
        nums[23] = new int[]{4, 3, 1, 2};

        //int randNum = generateRandIndexPathSequence();
        System.out.println("randNum: " + randNum);
        System.out.println("Initial Path Choices:");
        for (int num : nums[randNum]) {
            System.out.print(num + " ");
        }
        return nums[randNum];
    }
    private Button initializeInventoryButton(Stage primaryStage) {
        Button invButton = new Button();
        invButton.setStyle("-fx-background-color: transparent;");
        ImageView invButtonImg = new ImageView(new Image("./media/invButton.png"));
        invButtonImg.setFitWidth(60);
        invButtonImg.setFitHeight(60);
        invButton.setGraphic(invButtonImg);
        invButton.setOnAction(event -> {
            try {
                InventoryScreen inventoryScreen = new InventoryScreen(myPlayer);
                inventoryScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        return invButton;
    }

    public Path[] preparePaths(int[] nums) {

        Path[] sequenceRandPaths = new Path[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sequenceRandPaths[i] = new Path(nums[i]);
        }
        return sequenceRandPaths;
    }

    /*private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }

        final double cx = hero.getBoundsInLocal().getWidth() / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;

        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        final double cx = hero.getBoundsInLocal().getWidth() / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            hero.relocate(x - cx, y - cy);
        }
    }*/


    public static void main(String[] args) {
        launch(args);
        // test method below
        //randomizePaths();

        /* randomize path assignment to four doors in Initial Game Screen */
        /*
        // randomly choose one of 24 arrangements of one of 4 Paths to one of 4 Doors
        // see methods implemented below (randomizePaths and preparePaths)
        // here, initialize a Path [] and pull one by one for each door
        Path[] sequenceRandomPaths = preparePaths(randomizePaths(generateRandIndexPathSequence()));

        // NEXT STEPS:
        // 1) CREATE LABELS FOR EACH {combo of PATH, ROOM (id), ROOM ELEMENT}
        // 2) DECIDE WHERE TO RANDOMIZE ELEMENT OF EACH ROOM
        // 3) IDEA: Create door1, 2, 3, 4 as instance vars of Game Screen
        // to put this path preparation functionality completely inside helper methods
        // because this will help reduce # lines in each method
        // which is a reason why I have to put these comments here and not where they're used above
        */
    }
}
/*
    KeyListener listener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent event) {
            printEventInfo("Key Pressed", event);
        }

        @Override
        public void keyReleased(KeyEvent event) {
            printEventInfo("Key Released", event);
        }

        @Override
        public void keyTyped(KeyEvent event) {
            printEventInfo("Key Typed", event);
        }

        private void printEventInfo(String str, KeyEvent e) {
            System.out.println(str);
            int code = e.getKeyCode();
            System.out.println("   Code: " + KeyEvent.getKeyText(code));
            System.out.println("   Char: " + e.getKeyChar());
            int mods = e.getModifiersEx();
            System.out.println("    Mods: "
                    + KeyEvent.getModifiersExText(mods));
            System.out.println("    Location: "
                    + keyboardLocation(e.getKeyLocation()));
            System.out.println("    Action? " + e.isActionKey());
        }

        private String keyboardLocation(int keybrd) {
            switch (keybrd) {
                case KeyEvent.KEY_LOCATION_RIGHT:
                    return "Right";
                case KeyEvent.KEY_LOCATION_LEFT:
                    return "Left";
                case KeyEvent.KEY_LOCATION_NUMPAD:
                    return "NumPad";
                case KeyEvent.KEY_LOCATION_STANDARD:
                    return "Standard";
                case KeyEvent.KEY_LOCATION_UNKNOWN:
                default:
                    return "Unknown";
            }
        }
    };
}
*/


