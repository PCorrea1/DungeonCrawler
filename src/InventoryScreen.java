package src;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

//import java.io.PrintWriter;

public class InventoryScreen extends Application {
    private Player myPlayer;

    private final String knight = "./media/MainHeroesPNG/Knight/knight.png";
    private final String mage = "./media/MainHeroesPNG/Mage/mage.png";
    private final String rogue = "./media/MainHeroesPNG/Rogue/rogue.png";
    private ComboBox<Item> weaponComboBox;
    private ComboBox<Item> potionComboBox;

    public InventoryScreen(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public InventoryScreen() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane sP = new StackPane();
        HBox returnButtonhbox = new HBox();
        returnButtonhbox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox comboboxBox = new VBox();
        VBox fullScreen = new VBox(190);
        HBox weapons = new HBox(30);
        HBox potions = new HBox(30);
        primaryStage.setTitle("Inventory");
        ImageView background = setupBackground();

        // for testing purposes
        /*if (myPlayer.getInventory() == null) {
            throw new NullPointerException("Inventory null");
        } else if (myPlayer.getInventory().getWeapons() == null) {
            throw new NullPointerException("Weapons null");
        } else if (myPlayer.getInventory().getPotions() == null) {
            throw new NullPointerException("Potions null");
        }*/

        Text weaponText = new Text("Weapons: " + myPlayer.getInventory().getWeapons().size());
        weaponText.setFont(Font.font(String.valueOf(weaponText),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 30));
        weaponText.setFill(Paint.valueOf("Blue"));
        weaponText.setStroke(Paint.valueOf("Black"));
        weaponComboBox = setupWeaponComboBox();

        Text potionText = new Text("Potions: " + myPlayer.getInventory().getPotions().size());
        potionText.setFont(Font.font(String.valueOf(potionText),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 30));
        potionText.setFill(Paint.valueOf("Blue"));

        potionText.setStroke(Paint.valueOf("Black"));
        potionComboBox = setupPotionComboBox();

        Button returnButton = setupReturnButton(primaryStage);
        returnButtonhbox.getChildren().addAll(returnButton);

        Button updatePlayer = updatePlayer(primaryStage);


        weapons.getChildren().addAll(weaponText, weaponComboBox);
        potions.getChildren().addAll(potionText, potionComboBox);
        comboboxBox.getChildren().addAll(weapons, potions);
        fullScreen.getChildren().addAll(comboboxBox, updatePlayer, returnButtonhbox);


        sP.getChildren().addAll(background, fullScreen);
        primaryStage.setScene(new Scene(sP));
        primaryStage.show();
    }

    private ImageView setupBackground() {
        ImageView background = new ImageView(new Image("./media/insideBackpack.jpg"));
        background.setFitWidth(1200);
        background.setFitHeight(700);
        return background;
    }

    private Button setupReturnButton(Stage primaryStage) {
        Button returnButton = new Button();
        returnButton.setStyle("-fx-background-color: transparent;");
        ImageView returnButtonImg = new ImageView(new Image("./media/returnButton.png"));
        returnButtonImg.setFitHeight(100);
        returnButtonImg.setFitWidth(300);
        returnButton.setGraphic(returnButtonImg);
        returnButton.setOnAction(event -> {
            if (myPlayer.getPath() == null) {
                try {
                    InitialGameScreen initialGameScreen = new InitialGameScreen(myPlayer);
                    initialGameScreen.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (myPlayer.getCurrentRoom().getId() != 99999) {
                    try {
                        RoomScreen roomScreen = new RoomScreen(myPlayer);
                        roomScreen.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        ChallengeRoomScreen challengeRoomScreen = new ChallengeRoomScreen(myPlayer);
                        challengeRoomScreen.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return returnButton;
    }

    private ComboBox<Item> setupPotionComboBox() {
        ComboBox<Item> comboBox = new ComboBox<>();
        comboBox.setStyle("-fx-background-color: #009900");
        comboBox.setPromptText("Select a Potion");
        comboBox.setFocusTraversable(false);
        comboBox.setPrefSize(290, 35);
        for (Item item : myPlayer.getInventory().getPotions()) {
            comboBox.getItems().add(item);
        }
        return comboBox;
    }

    private ComboBox<Item> setupWeaponComboBox() {
        ComboBox<Item> weaponComboBox = new ComboBox<>();
        weaponComboBox.setStyle("-fx-background-color: #009900");
        weaponComboBox.setPromptText("Select a Weapon");
        weaponComboBox.setFocusTraversable(false);
        weaponComboBox.setPrefSize(290, 35);
        System.out.println("InventoryScreen -- Inventory.Weapons.size: "
                + myPlayer.getInventory().getWeapons().size());
        for (Item weapon : myPlayer.getInventory().getWeapons()) {
            System.out.println("InventoryScreen -- Weapon.getItemName() : " + weapon.getItemName());
            weaponComboBox.getItems().add(weapon);
        }
        weaponComboBox.setOnAction(event -> {
            Item item = weaponComboBox.getValue();
            if (item.getItemName().equals("Sword")) {
                myPlayer.setAvatar(knight);
                myPlayer.setWeapon(Weapon.SWORD);
            } else if (item.getItemName().equals("Knife")) {
                myPlayer.setAvatar(rogue);
                myPlayer.setWeapon(Weapon.KNIFE);
            } else if (item.getItemName().equals("Staff")) {
                myPlayer.setAvatar(mage);
                myPlayer.setWeapon(Weapon.STAFF);
            }
        });
        return weaponComboBox;
    }
    private Button updatePlayer(Stage primaryStage) {
        Button updatePlayer = new Button("Apply Changes");
        updatePlayer.setPrefSize(200, 60);
        updatePlayer.setOnAction(event -> {
            // if weapon, assign weapon, remove from inventory
            // if health potion, increment health
            // if strength potion, increment weapon delta

            IWeapon weapon = (IWeapon) weaponComboBox.getValue();
            Potion potion = (Potion) potionComboBox.getValue();

            if (weapon != null) {
                myPlayer.setMyWeapon(weapon);
                //myPlayer.getInventory().removeItem(weapon);
            }
            if (potion != null) {
                if (potion.getPotionType().equals("health")) {
                    myPlayer.setHealth(myPlayer.getHealth() + potion.getImpactDelta());
                    myPlayer.getInventory().removeItem(potion);
                    myPlayer.setHealthPotsUsed(myPlayer.getHealthPotsUsed() + 1);
                    System.out.println("Health Pots Used: " + myPlayer.getHealthPotsUsed());
                } else if (potion.getPotionType().equals("strength")) {
                    myPlayer.getMyWeapon().setImpactDelta(myPlayer.getMyWeapon().getImpactDelta()
                            + potion.getImpactDelta());
                    myPlayer.getInventory().removeItem(potion);
                    myPlayer.setStrengthPotsUsed(myPlayer.getStrengthPotsUsed() + 1);
                    System.out.println("Strength Pots Used: " + myPlayer.getStrengthPotsUsed());
                }
            }

            if (myPlayer.getPath() == null) {
                try {
                    InitialGameScreen initialGameScreen = new InitialGameScreen(myPlayer);
                    initialGameScreen.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (myPlayer.getCurrentRoom().getId() != 99999) {
                    try {
                        RoomScreen roomScreen = new RoomScreen(myPlayer);
                        roomScreen.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        ChallengeRoomScreen challengeRoomScreen = new ChallengeRoomScreen(myPlayer);
                        challengeRoomScreen.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return updatePlayer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
