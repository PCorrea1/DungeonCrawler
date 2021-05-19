package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

//import java.util.concurrent.atomic.AtomicInteger;

public class ChallengeRoomScreen extends Application {
    private Player myPlayer;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private boolean challengeAccepted = false;


    public ChallengeRoomScreen(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Initialization Frame */
        StackPane stackPane1 = new StackPane();
        primaryStage.setTitle("Challenge Room");

        /* Set RoomScreen Background */
        ImageView background = new ImageView(new Image("./media/game_background_3.png"));
        background.setFitWidth(1200);
        background.setFitHeight(700);

        /* Room Number Text */
        Text text = new Text("You are in the Challenge Room");
        text.setFont(Font.font(String.valueOf(text),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 75));
        text.setFill(Paint.valueOf("Yellow"));
        text.setStroke(Paint.valueOf("Black"));

        setStageBeforeChallengeAccepted(primaryStage, stackPane1, text, background);

        /* Routing to Main Stage */
        primaryStage.setScene(new Scene(stackPane1));
        primaryStage.show();
        confirmChallengeAcceptance(primaryStage, stackPane1, text, background);
    }

    private void confirmChallengeAcceptance(Stage primaryStage, StackPane stackPane1, Text text, ImageView background) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you confidently ACCEPTING this challenge?",
                ButtonType.OK,
                ButtonType.CANCEL);
        alert.setTitle("Challenge Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("Challenge Accepted");
            challengeAccepted = true;
        }
        if (challengeAccepted) {
            //setStageAfterChallengeAccepted(primaryStage, stackPane1, text, background);
            RealChallengeRoomScreen realChallengeRoom = new RealChallengeRoomScreen(myPlayer);
            try {
                realChallengeRoom.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setCurrentRoom(myPlayer.getPrevRoom());
            myPlayer.setPrevRoom(myPlayer.getPrevPrevRoom());
            Alert challengeRoomDefeated = new Alert(Alert.AlertType.ERROR);
            challengeRoomDefeated.setTitle("Challenge Declined!");
            challengeRoomDefeated.setHeaderText("Escaping Challenge Room...");
            challengeRoomDefeated.setContentText("You are now returning to Room " + myPlayer.getCurrentRoom().getId() % 10);
            challengeRoomDefeated.showAndWait();
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setStageBeforeChallengeAccepted(Stage primaryStage, StackPane stackPane1, Text text, ImageView background) {
        Button invButton = initializeInventoryButton(primaryStage);
        /* Create Box Frames */
        VBox vBox1 = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        /* Populate Inventory Box, Place in Panes */
        ImageView invBox = new ImageView(new Image("./media/rectangle.png"));
        invBox.setFitWidth(500);
        invBox.setFitHeight(180);
        /* Populate Coins, Place in Panes */
        ImageView coinsGraphic = new ImageView(new Image("media/coins.png"));
        coinsGraphic.setFitWidth(30);
        coinsGraphic.setFitHeight(30);
        /* Populate Health, Place in Panes */
        ImageView healthGraphic = new ImageView(new Image("./media/heart.png"));
        healthGraphic.setFitWidth(30);
        healthGraphic.setFitHeight(30);
        /* Create Health Indicator and Money Bag, Place in Panes */
        Label healthLabel = new Label("HEALTH: " + myPlayer.getHealth() + "hp");
        healthLabel.setGraphic(healthGraphic);
        Font font = Font.font("Pickwick", FontWeight.BOLD,
                FontPosture.REGULAR, 25);
        healthLabel.setFont(font);
        Label coinsLabel = new Label("COINS: " + myPlayer.getCoins() + "gp");
        coinsLabel.setGraphic(coinsGraphic);
        coinsLabel.setFont(font);
        /* Create Box Frames */
        VBox vbox4 = new VBox();
        StackPane stackPane2 = new StackPane();
        HBox hBox5 = new HBox();
        /* Box Padding */
        hbox1.setPadding(new Insets(-60, 0, -100, 80));
        hbox2.setPadding(new Insets(0, 0, 20, 0));
        hBox3.setPadding(new Insets(10, -30, 90, 0));
        hBox4.setPadding(new Insets(20, 0, -50, 0));
        vbox4.setPadding(new Insets(40, 0, 0, 60));
        /* Box Alignment */
        vBox1.setAlignment(Pos.CENTER);
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox2.setAlignment(Pos.TOP_CENTER);
        hBox3.setAlignment(Pos.BOTTOM_RIGHT);
        //hBox4.setAlignment(Pos.CENTER);
        hBox5.setAlignment(Pos.CENTER_RIGHT);
        /* Populating HBoxes, VBoxes and StackPanes */
        vbox4.getChildren().addAll(healthLabel, coinsLabel);
        stackPane2.getChildren().addAll(invBox, vbox4);
        //hbox1.getChildren().addAll(door1, door2, door3);
        hBox3.getChildren().addAll(stackPane2, invButton);
        hBox5.getChildren().addAll(invButton);
        //hBox4.getChildren().addAll(playerAvatar, enButton1, enButton2, enButton3);
        vBox1.getChildren().addAll(hbox2, hbox1, hBox4, hBox5, hBox3);
        hbox2.getChildren().addAll(text);
        stackPane1.getChildren().addAll(background, vBox1);
    }
    
    private void setStageAfterChallengeAccepted(Stage primaryStage, StackPane stackPane1, Text text, ImageView background) {
        /* instantiate enemy */
        enemy1 = new Enemy(myPlayer.getDifficulty(), 1);
        ImageView enemyOne = new ImageView(new Image(enemy1.getAvatar()));
        enemy2 = new Enemy(myPlayer.getDifficulty(), 3);
        ImageView enemyTwo = new ImageView(new Image(enemy2.getAvatar()));
        enemy3 = new Enemy(myPlayer.getDifficulty(), 5);
        ImageView enemyThree = new ImageView(new Image(enemy3.getAvatar()));
        //ImageView firstEnemy = new ImageView(new Image(enemy.getAvatar()));
        /* player avatar */
        ImageView playerAvatar = new ImageView(new Image(myPlayer.getAvatar()));
        Button invButton = initializeInventoryButton(primaryStage);


        Button enButton1 = new Button();
        Button enButton2 = new Button();
        Button enButton3 = new Button();
        setUpEnemyButton(enButton1, enemy1, primaryStage, enemyOne);
        setUpEnemyButton(enButton2, enemy2, primaryStage, enemyTwo);
        setUpEnemyButton(enButton3, enemy3, primaryStage, enemyThree);
        /* Create Box Frames */
//        VBox vBox1 = new VBox();
//        HBox hbox1 = new HBox();
//        HBox hbox2 = new HBox();
//        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        /* Populate Inventory Box, Place in Panes */
//        ImageView invBox = new ImageView(new Image("./media/rectangle.png"));
//        invBox.setFitWidth(500);
//        invBox.setFitHeight(180);
        /* Populate Coins, Place in Panes */
//        ImageView coinsGraphic = new ImageView(new Image("media/coins.png"));
//        coinsGraphic.setFitWidth(30);
//        coinsGraphic.setFitHeight(30);
        /* Populate Health, Place in Panes */
//        ImageView healthGraphic = new ImageView(new Image("./media/heart.png"));
//        healthGraphic.setFitWidth(30);
//        healthGraphic.setFitHeight(30);
        /* Create Health Indicator and Money Bag, Place in Panes */
//        Label healthLabel = new Label("HEALTH: " + myPlayer.getHealth() + "hp");
//        healthLabel.setGraphic(healthGraphic);
//        Font font = Font.font("Pickwick", FontWeight.BOLD,
//                FontPosture.REGULAR, 25);
//        healthLabel.setFont(font);
//        Label coinsLabel = new Label("COINS: " + myPlayer.getCoins() + "gp");
//        coinsLabel.setGraphic(coinsGraphic);
//        coinsLabel.setFont(font);
        /* Create Box Frames */
//        VBox vbox4 = new VBox();
//        StackPane stackPane2 = new StackPane();
//        HBox hBox5 = new HBox();
        /* Box Padding */
//        hbox1.setPadding(new Insets(-60, 0, -100, 80));
//        hbox2.setPadding(new Insets(0, 0, 20, 0));
//        hBox3.setPadding(new Insets(10, -30, 90, 0));
//        hBox4.setPadding(new Insets(20, 0, -50, 0));
//        vbox4.setPadding(new Insets(40, 0, 0, 60));
        /* Box Alignment */
//        vBox1.setAlignment(Pos.CENTER);
//        hbox1.setAlignment(Pos.CENTER_LEFT);
//        hbox2.setAlignment(Pos.TOP_CENTER);
//        hBox3.setAlignment(Pos.BOTTOM_RIGHT);
//        hBox4.setAlignment(Pos.CENTER);
//        hBox5.setAlignment(Pos.CENTER_RIGHT);
        /* Populating HBoxes, VBoxes and StackPanes */
//        vbox4.getChildren().addAll(healthLabel, coinsLabel);
//        stackPane2.getChildren().addAll(invBox, vbox4);
//        hbox1.getChildren().addAll(door1, door2, door3);
//        hBox3.getChildren().addAll(stackPane2, invButton);
//        hBox5.getChildren().addAll(invButton);
        hBox4.getChildren().addAll(playerAvatar, enButton1, enButton2, enButton3);
//        vBox1.getChildren().addAll(hbox2, hbox1, hBox4, hBox5, hBox3);
//        hbox2.getChildren().addAll(text);
//        stackPane1.getChildren().addAll(background, vBox1);
    }

    private Button initializeInventoryButton(Stage primaryStage) {
        InventoryScreen inventoryScreen = new InventoryScreen(myPlayer);
        Button invButton = new Button();
        invButton.setStyle("-fx-background-color: transparent;");
        ImageView invButtonImg = new ImageView(new Image("./media/invButton.png"));
        invButtonImg.setFitWidth(60);
        invButtonImg.setFitHeight(60);
        invButton.setGraphic(invButtonImg);
        invButton.setOnAction(event -> {
            try {
                inventoryScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        return invButton;
    }

    private void defeatScreenCue(Stage primaryStage) {
        DefeatScreen defeatScreen = new DefeatScreen(myPlayer);
        try {
            defeatScreen.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doorCheck4() {
        if (myPlayer.getCurrentRoom().getDoor1() != null) {
            myPlayer.getCurrentRoom().getDoor1().setLocked(false);
        }
        if (myPlayer.getCurrentRoom().getDoor2() != null) {
            myPlayer.getCurrentRoom().getDoor2().setLocked(false);
        }
        if (myPlayer.getCurrentRoom().getDoor3() != null) {
            myPlayer.getCurrentRoom().getDoor3().setLocked(false);
        }
    }

    private void doorCheck3A(Stage primaryStage) {
        if (myPlayer.getCurrentRoom().getDoor3() == null) { // if door is null
            //createEmptyMessage();
            // if door is null, lead to Defeat Screen
            defeatScreenCue(primaryStage);
        } else if (myPlayer.getCurrentRoom().getDoor3().isLocked()) { // if locked
            createAlertMessage();
        } else if (myPlayer.getCurrentRoom().getDoor3().getId() % 10 == myPlayer.getPath().getSuccessRoom().getId() % 10) {
            WinScreen winScreen = new WinScreen(myPlayer);
            try {
                winScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (myPlayer.getCurrentRoom().getDoor3().equals(
                myPlayer.getPath().getMyPlayerPath().getLast())) {
            /// if next door is last room in path, then cue final boss
            RoomScreen finalBossScreen = new RoomScreen(myPlayer);
            myPlayer.setPrevRoom(myPlayer.getCurrentRoom());
            myPlayer.setCurrentRoom(myPlayer.getCurrentRoom().getDoor3());
            try {
                finalBossScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // get door3 pointer
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setPrevRoom(myPlayer.getCurrentRoom());
            myPlayer.setCurrentRoom(myPlayer.getCurrentRoom().getDoor3());
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doorCheck3() {
        if (myPlayer.getCurrentRoom().getDoor3().equals(myPlayer.getPrevRoom())) {
            myPlayer.getCurrentRoom().getDoor3().setLocked(false);
        } else {
            myPlayer.getCurrentRoom().getDoor3().setLocked(true);
        }
    }

    private void doorCheck2A(Stage primaryStage) {
        if (myPlayer.getCurrentRoom().getDoor2() == null) { // if door is null
            //createEmptyMessage();
            // if door is null, lead to Defeat Screen
            defeatScreenCue(primaryStage);
        } else if (myPlayer.getCurrentRoom().getDoor2().isLocked()) { // if locked
            createAlertMessage();
        } else if (myPlayer.getCurrentRoom().getDoor2().getId() % 10 == myPlayer.getPath().getSuccessRoom().getId() % 10) {
            WinScreen winScreen = new WinScreen(myPlayer);
            try {
                winScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (myPlayer.getCurrentRoom().getDoor2().equals(
                myPlayer.getPath().getMyPlayerPath().getLast())) {
            /// if next door is last room in path, then cue final boss
            RoomScreen finalBossScreen = new RoomScreen(myPlayer);
            myPlayer.setPrevRoom(myPlayer.getCurrentRoom());
            myPlayer.setCurrentRoom(myPlayer.getCurrentRoom().getDoor2());
            try {
                finalBossScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // get door2 pointer
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setPrevRoom(myPlayer.getCurrentRoom());
            myPlayer.setCurrentRoom(myPlayer.getCurrentRoom().getDoor2());
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doorCheck1A(Stage primaryStage) {
        if (myPlayer.getCurrentRoom().getDoor1() == null) { // if door is null
            //createEmptyMessage();
            // if door is null, lead to Defeat Screen
            defeatScreenCue(primaryStage);
            //createEmptyMessage();
            // no straight path to DefeatScreen since this door leads to previous room
            // except for very first room since player does not go back to Initial Game Screen
        } else if (myPlayer.getCurrentRoom().getDoor1().isLocked()) { // if locked
            createAlertMessage();
        } else if (myPlayer.getCurrentRoom().getDoor1().getId() % 10 == myPlayer.getPath().getSuccessRoom().getId() % 10) {
            WinScreen winScreen = new WinScreen(myPlayer);
            try {
                winScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (myPlayer.getCurrentRoom().getDoor1().equals(
                myPlayer.getPath().getMyPlayerPath().getLast())) {
            /// if next door is last room in path, then cue final boss
            RoomScreen finalBossScreen = new RoomScreen(myPlayer);
            myPlayer.setPrevRoom(myPlayer.getCurrentRoom());
            myPlayer.setCurrentRoom(myPlayer.getCurrentRoom().getDoor1());
            try {
                finalBossScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // get door1 pointer
            RoomScreen roomScreen = new RoomScreen(myPlayer);
            myPlayer.setPrevRoom(myPlayer.getCurrentRoom());
            myPlayer.setCurrentRoom(myPlayer.getCurrentRoom().getDoor1());
            try {
                roomScreen.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doorCheck2() {
        if (myPlayer.getCurrentRoom().getDoor2().equals(myPlayer.getPrevRoom())) {
            myPlayer.getCurrentRoom().getDoor2().setLocked(false);
        } else {
            myPlayer.getCurrentRoom().getDoor2().setLocked(true);
        }
    }

    private void doorCheck1() {
        if (myPlayer.getCurrentRoom().getDoor1().equals(myPlayer.getPrevRoom())) {
            myPlayer.getCurrentRoom().getDoor1().setLocked(false);
        } else {
            myPlayer.getCurrentRoom().getDoor1().setLocked(true);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void createEmptyMessage() {
        String msg = "The selected door is inaccessible."
                + "\nPlease try another door.";

        Alert lockedDoor = new Alert(Alert.AlertType.ERROR);
        lockedDoor.setTitle("CANNOT ENTER NEXT ROOM");
        lockedDoor.setHeaderText("Oh no!");
        lockedDoor.setContentText(msg);
        lockedDoor.showAndWait();
    }

    private void createAlertMessage() {
        String msg = "The selected door is locked and inaccessible."
                + "\nPlease defeat all monsters first.";

        Alert lockedDoor = new Alert(Alert.AlertType.ERROR);
        lockedDoor.setTitle("CANNOT ENTER NEXT ROOM");
        lockedDoor.setHeaderText("A hero does not run away!");
        lockedDoor.setContentText(msg);
        lockedDoor.showAndWait();
    }

    private void warnAboutDroppedItem(Enemy enemy) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Do you want to add " + enemy.getDropItem().getItemName() + " to your inventory?",
                ButtonType.OK,
                ButtonType.CANCEL);
        alert.setTitle("Dropped Item Alert");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (enemy.getDropItem().getItemName().equals("Dragon Tooth")) {
                myPlayer.setCoins(myPlayer.getCoins() + enemy.getDropItem().getCoinsValue());
            } else {
                myPlayer.getInventory().addItem(enemy.getDropItem());
            }
        }
    }

    /*private void askAboutChallenge(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Do you want to take on a challenge?",
                ButtonType.OK,
                ButtonType.CANCEL);
        alert.setTitle("Challenge Alert");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("Challenge Accepted");
            RoomScreen challengeRoom = new RoomScreen(myPlayer);
            myPlayer.setPrevRoom(myPlayer.getCurrentRoom());
            myPlayer.setCurrentRoom(new Room(null, null, null, null, 99999));
            try {
                challengeRoom.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    private void setUpEnemyButton(Button enButton, Enemy enemy, Stage primaryStage, ImageView enemyNumber) {
        enButton.setGraphic(enemyNumber);
        enButton.setStyle("-fx-background-color: transparent;");
        enButton.setOnMouseClicked(event -> {
            if (!(enemy.isDead())) {
                enemy.takeDamage(myPlayer.getMyWeapon().getImpactDelta());
                RandIntGen rig = new RandIntGen();
                int damage = rig.getRandInt(0, 2);
                myPlayer.takeDamage(damage);
                System.out.println("RoomScreen -- EnemyHealth = " + enemy.getHealth() + " | PlayerHealth = " + myPlayer.getHealth());
                if (myPlayer.getHealth() <= 0) {
                    defeatScreenCue(primaryStage);
                }
                if (enemy.getHealth() <= 0) {
                    enemy.setDead(true);
                    enButton.setDisable(true);
                    warnAboutDroppedItem(enemy);
                }
                if (enemy1.isDead() && enemy2.isDead() && enemy3.isDead()) {
                    RoomScreen roomScreen = new RoomScreen(myPlayer);
                    myPlayer.setCurrentRoom(myPlayer.getPrevRoom());
                    myPlayer.setPrevRoom(myPlayer.getPrevPrevRoom());
                    Alert challengeRoomDefeated = new Alert(Alert.AlertType.ERROR);
                    challengeRoomDefeated.setTitle("Congratulations!");
                    challengeRoomDefeated.setHeaderText("Challenge Room Successfully Defeated");
                    challengeRoomDefeated.setContentText("You are now returning to Room " + myPlayer.getCurrentRoom().getId() % 10);
                    challengeRoomDefeated.showAndWait();
                    try {
                        roomScreen.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void enemyHasBeenDefeated(ImageView imgV) {
        imgV = new ImageView(new Image("./media/defeatedEnemy.png"));
    }
}

