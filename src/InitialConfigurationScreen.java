package src;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.io.PrintWriter;

public class InitialConfigurationScreen extends Application {
    /* Instantiate a Player Object */
    private Player myPlayer;
    private Enum myWeapon;
    //private boolean myPlayerIsReadyToPlay = false;

    private final String knight = "./media/MainHeroesPNG/knight.png";
    private final String mage = "./media/MainHeroesPNG/mage.png";
    private final String rogue = "./media/MainHeroesPNG/rogue.png";

    // beginner: 20 health, 100 coins, 1 difficulty
    // intermediate: 15 health, 75 coins, 2 difficulty
    // hardcore: 10 health, 50 coins, 3 difficulty

    private Button beginner;
    private Button intermediate;
    private Button hardcore;
    private TextField playerName;
    private ComboBox<Enum> weapon;
    private ImageView avatarImg;
    private Scene scene1;

    public InitialConfigurationScreen(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public InitialConfigurationScreen() {
        this(new Player());
    }

    @Override
    public void start(Stage primaryStage) {
        /* Initialization Frame */
        //primaryStage = new Stage();
        StackPane root = new StackPane();
        primaryStage.setTitle("Initial Player Configuration Screen");

        /* Set InitConfigScreen Background */
        ImageView bG = new ImageView(new Image("./media/game_background_4.png"));
        bG.setFitHeight(700);
        bG.setFitWidth(1200);

        /* Create Box Frames */
        VBox vBox = new VBox();
        VBox vBox3 = new VBox();
        HBox hBox9 = new HBox();

        /* Player Name Field */
        HBox hBox = new HBox();
        playerName = initializePlayerNameTextField();

        /* Beginner Difficulty Button */
        HBox hbox1 = new HBox();
        beginner = initializeBeginnerButton();

        /* Intermediate Difficulty Button */
        HBox hbox2 = new HBox();
        intermediate = initializeIntermediateButton();

        /* Hardcore Difficulty Button */
        HBox hbox3 = new HBox();
        hardcore = initializeHardcoreButton();

        /* Start Game Button */
        HBox hbox5 = new HBox();
        Button startGame = initializeStartGameButton(playerName, primaryStage);

        /* Difficulty Header */
        HBox hbox6 = new HBox();
        ImageView difficulty = new ImageView(new Image("./media/show.png"));
        difficulty.setFitHeight(50);
        difficulty.setFitWidth(250);

        /* Avatar Weapon Text */
        VBox vBox2 = new VBox();
        HBox hbox8 = new HBox();
        Text text = new Text("Choose a weapon for an avatar!");
        text.setFont(Font.font(String.valueOf(text),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 30));
        text.setFill(Paint.valueOf("Blue"));
        text.setStroke(Paint.valueOf("Black"));

        /* Avatar Image */
        HBox hbox7 = new HBox();
        avatarImg = new ImageView(new Image(myPlayer.getAvatar()));
        avatarImg.setFitHeight(200);
        avatarImg.setFitWidth(200);

        /* Weapon (and Avatar) Select Dropdown */
        HBox hbox4 = new HBox();
        weapon = initializeWeaponComboBox(hbox7);

        //HBox hBox10 = new HBox();
        /*Button updateAvatar = initializeUpdateAvatarButton();
        updateAvatar.setOnAction(event -> {
            ImageView newAvatarImg = new ImageView(setAvatarImg());
            newAvatarImg.setFitHeight(128 * 2);
            newAvatarImg.setFitWidth(128 * 2);
            hbox7.getChildren().clear();
            hbox7.getChildren().addAll(newAvatarImg);
        });*/

        /* General Screen Formatting */

        /* HBox Padding */
        hBox.setPadding(new Insets(30, 90, 0, 30));
        hbox1.setPadding(new Insets(0, 90, 0, 280));
        hbox2.setPadding(new Insets(5, 90, 5, 280));
        hbox3.setPadding(new Insets(5, 90, 30, 280));
        hbox4.setPadding(new Insets(0, 90, 5, 30));
        //hBox10.setPadding(new Insets(0, 160, 5, 30));
        hbox5.setPadding(new Insets(0, 40, 30, 280));
        hbox6.setPadding(new Insets(100, 110, 0, 280));
        hbox8.setPadding(new Insets(100, 0, 0, 150));
        hbox7.setPadding(new Insets(0, 0, 0, 150));

        /* HBox Alignment */
        hbox1.setAlignment(Pos.BASELINE_RIGHT);
        hbox2.setAlignment(Pos.BASELINE_RIGHT);
        hbox3.setAlignment(Pos.BASELINE_RIGHT);
        hbox4.setAlignment(Pos.BASELINE_RIGHT);
        //hBox10.setAlignment(Pos.BASELINE_RIGHT);
        hbox5.setAlignment(Pos.BASELINE_RIGHT);
        hbox6.setAlignment(Pos.BASELINE_RIGHT);
        hbox8.setAlignment(Pos.BASELINE_RIGHT);
        hbox7.setAlignment(Pos.BASELINE_CENTER);


        /* Assigning HBox Content */
        hBox.getChildren().addAll(playerName);
        hbox1.getChildren().addAll(beginner);
        hbox2.getChildren().addAll(intermediate);
        hbox3.getChildren().addAll(hardcore);
        hbox4.getChildren().addAll(weapon);
        hbox5.getChildren().addAll(startGame);
        hbox6.getChildren().addAll(difficulty);
        hbox8.getChildren().addAll(text);
        hbox7.getChildren().addAll(avatarImg);
        //hBox10.getChildren().addAll(updateAvatar);

        /* Populating HBoxes and VBoxes */
        vBox2.getChildren().addAll(hbox8, hbox7);
        //vBox2.getChildren().addAll(hbox8);
        vBox3.getChildren().addAll(hbox6, hbox1, hbox2, hbox3, hbox5);
        hBox9.getChildren().addAll(vBox2, vBox3);
        //vBox.getChildren().addAll(hBox, hbox4, hBox10, hBox9);
        vBox.getChildren().addAll(hBox, hbox4, hBox9);
        vBox.setAlignment(Pos.TOP_CENTER);

        /* Routing to Main Stage */
        root.getChildren().addAll(bG, vBox);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private TextField initializePlayerNameTextField() {
        TextField playerName = new TextField();
        playerName.setStyle("-fx-background-color: #4d4d4d");
        playerName.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        playerName.setFocusTraversable(false);
        playerName.setPromptText("Player Name:");
        playerName.setAlignment(Pos.BOTTOM_RIGHT);
        playerName.setPrefSize(300, 40);
        return playerName;
    }

    private Button initializeBeginnerButton() {
        Button beginner = new Button("Beginner");
        beginner.setPrefSize(260, 60);
        beginner.setStyle("-fx-background-color: transparent;");
        ImageView beginnerButton = new ImageView(new Image("./media/easyButton.jpg"));
        beginner.setGraphic(beginnerButton);
        beginner.setOnAction(event -> {
            myPlayer.setDifficulty(1);
            myPlayer.setHealth(100);
            myPlayer.setCoins(100);
        });
        return beginner;
    }

    private Button initializeIntermediateButton() {
        Button intermediate = new Button("Intermediate");
        intermediate.setPrefSize(260, 60);
        intermediate.setStyle("-fx-background-color: transparent;");
        ImageView intermediateButton = new ImageView(new Image("./media/intermediateButton.jpg"));
        intermediate.setGraphic(intermediateButton);
        intermediate.setOnAction(event -> {
            myPlayer.setDifficulty(2);
            myPlayer.setHealth(75);
            myPlayer.setCoins(75);
        });
        return intermediate;
    }

    private Button initializeHardcoreButton() {
        Button hardcore = new Button("Hardcore");
        hardcore.setPrefSize(260, 60);
        hardcore.setStyle("-fx-background-color: transparent;");
        ImageView hardcoreButton = new ImageView(new Image("./media/hardcoreButton.jpg"));
        hardcore.setGraphic(hardcoreButton);
        hardcore.setOnAction(event -> {
            myPlayer.setDifficulty(3);
            myPlayer.setHealth(15);
            myPlayer.setCoins(50);
        });
        return hardcore;
    }

    private ComboBox<Enum> initializeWeaponComboBox(HBox hbox7) {
        myPlayer.setAvatar("./media/con2.png");
        ComboBox<Enum> weapon = new ComboBox<>();
        weapon.setStyle("-fx-background-color: #009900");
        weapon.setPromptText("SELECT YOUR WEAPON");
        weapon.setFocusTraversable(false);
        weapon.setPrefSize(290, 35);
        weapon.getItems().addAll(Weapon.SWORD, Weapon.KNIFE, Weapon.STAFF);
        weapon.setOnAction(event -> {
            myPlayer.setWeapon(weapon.getValue());
            if (myPlayer.getWeapon() != null && myPlayer.getWeapon().equals(Weapon.SWORD)) {
                myWeapon = Weapon.SWORD;
                myPlayer.setAvatar(knight);
                avatarImg = new ImageView(new Image(myPlayer.getAvatar()));
                avatarImg.setFitHeight(128 * 2);
                avatarImg.setFitWidth(128 * 2);
                hbox7.getChildren().clear();
                hbox7.getChildren().addAll(avatarImg);
            } else if (myPlayer.getWeapon() != null && myPlayer.getWeapon().equals(Weapon.KNIFE)) {
                myWeapon = Weapon.KNIFE;
                myPlayer.setAvatar(rogue);
                avatarImg = new ImageView(new Image(myPlayer.getAvatar()));
                avatarImg.setFitHeight(128 * 2);
                avatarImg.setFitWidth(128 * 2);
                hbox7.getChildren().clear();
                hbox7.getChildren().addAll(avatarImg);
            } else if (myPlayer.getWeapon() != null && myPlayer.getWeapon().equals(Weapon.STAFF)) {
                myWeapon = Weapon.STAFF;
                myPlayer.setAvatar(mage);
                avatarImg = new ImageView(new Image(mage));
                avatarImg.setFitHeight(128 * 2);
                avatarImg.setFitWidth(128 * 2);
                hbox7.getChildren().clear();
                hbox7.getChildren().addAll(avatarImg);
            }
        });
        return weapon;
    }

    private Button initializeStartGameButton(TextField playerName, Stage primaryStage) {
        Button startGame = new Button();
        startGame.setPrefSize(20, 60);
        startGame.setStyle("-fx-background-color: transparent;");
        ImageView start = new ImageView(new Image("./media/unnamed.png"));
        startGame.setGraphic(start);
        startGame.setOnAction(event -> {
            myPlayer.setName(playerName.getText());
            if (myPlayerIsReadyToPlay()) {
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter("player.csv");
                    pw.println(myPlayer.getName() + "," + myPlayer.getWeapon() + ","
                            + myPlayer.getAvatar() + "," + myPlayer.getDifficulty()
                            + "," + myPlayer.getHealth() + "," + myPlayer.getCoins());
                    if (pw != null) {
                        pw.close();
                    }
                } catch (Exception e) {
                    System.out.print("File not found");

                } finally {
                    if (pw != null) {
                        pw.close();
                    }
                }
                try {
                    IWeapon weapon = myPlayer.setMyWeaponType(myWeapon);
                    myPlayer.setMyWeapon(weapon);
                    InitialGameScreen s3 = new InitialGameScreen(myPlayer);
                    s3.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                createAlertMessages();
                // create other ways to check how a Player instance
                // field may be invalid to start game
            }
        });
        return startGame;
    }

    /*private Image setAvatarImg() {
        if (myPlayer.getWeapon() != null && myPlayer.getWeapon().equals(Weapon.SWORD)) {
            myPlayer.setAvatar(knight);
        } else if (myPlayer.getWeapon() != null && myPlayer.getWeapon().equals(Weapon.KNIFE)) {
            myPlayer.setAvatar(rogue);
        } else if (myPlayer.getWeapon() != null && myPlayer.getWeapon().equals(Weapon.STAFF)) {
            myPlayer.setAvatar(mage);
        } else {
            myPlayer.setAvatar("./media/con2.png");
        }

        return new Image(myPlayer.getAvatar());
    }*/

    /*private Button initializeUpdateAvatarButton() {
        Button updateAvatar = new Button("Update Avatar");
        updateAvatar.setPrefSize(200, 50);
        //updateAvatar.setStyle("-fx-background-color: transparent;");
        return updateAvatar;
    }*/

    private boolean myPlayerIsReadyToPlay() {
        if (!(myPlayer.getDifficulty() == 0 || myPlayer.getWeapon() == null
                || myPlayer.getName().equals("") || myPlayerNameContainsSpace())) {
            return true;
        }
        return false;
    }

    private boolean myPlayerNameContainsSpace() {
        if (myPlayer.getName().contains(" ")) {
            return true;
        }
        return false;
    }

    private void createAlertMessages() {
        String msg = "";
        if (myPlayer.getName().equals("")) {
            msg += "What's your name?\n";
        }
        if (myPlayerNameContainsSpace()) {
            msg += "Username cannot contain a space\n";
        }

        if (myPlayer.getDifficulty() == 0) {
            msg += "Challenge yourself with a difficulty mode!\n";
        }
        if (myPlayer.getWeapon() == null) {
            msg += "You'll need a weapon on your adventure!\n";
        }

        Alert playerNotReadyAlert = new Alert(Alert.AlertType.ERROR);
        playerNotReadyAlert.setTitle("CANNOT START GAME (YET)");
        playerNotReadyAlert.setHeaderText("Make sure you're prepared ...");
        playerNotReadyAlert.setContentText(msg);
        playerNotReadyAlert.showAndWait();
    }

    /**
     * Main method needed for IDE.
     * @param args main method.
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Getter method for myPlayerDifficulty instance variable.
     *
     * @return myPlayerDifficulty
     */

    /*public int getMyPlayerDifficulty() {
        return this.myPlayerDifficulty;
    }*/

    /**
     * Getter method for myPlayerHealth instance variable.
     *
     * @return myPlayerHealth
     */

    /*public int getMyPlayerHealth() {
        return this.myPlayerHealth;
    }*/

    /**
     * Getter method for myPlayerCoins instance variable.
     *
     * @return myPlayerCoins
     */

    /*public int getMyPlayerCoins() {
        return this.myPlayerCoins;
    }*/

    /**
     * Getter method for beginner button instance variable.
     *
     * @return beginner
     */

    public Button getBeginner() {
        return this.beginner;
    }

    /**
     * Getter method for intermediate button instance variable.
     *
     * @return intermediate
     */

    public Button getIntermediate() {
        return this.intermediate;
    }

    /**
     * Getter method for hardcore button instance variable.
     *
     * @return hardcore
     */

    public Button getHardcore() {
        return this.hardcore;
    }

    /**
     * Getter method for myPlayerWeapon instance variable.
     *
     * @return myPlayerWeapon
     */

    /*public Enum getMyPlayerWeapon() {
        return this.myPlayerWeapon;
    }*/

    /**
     * Getter method for playerName text field instance variable.
     *
     * @return playerName
     */

    public TextField getPlayerName() {
        return this.playerName;
    }

    /**
     * Getter method for weapon combo box instance variable.
     *
     * @return combobox
     */
    public ComboBox<Enum> getWeapon() {
        return weapon;
    }

    /**
     * Getter method for myPlayerAvatar instance variable.
     *
     * @return myPlayerAvatar
     */
    /*public String getMyPlayerAvatar() {
        return this.myPlayerAvatar;
    }*/
}