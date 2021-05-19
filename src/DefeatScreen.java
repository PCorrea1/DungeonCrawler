package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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



public class DefeatScreen extends Application {
    private Player myPlayer;
    private ImageView playerAvatar = new ImageView();
    private InitialConfigurationScreen iCS;

    private Button quitGame;
    private Button startGame;

    public DefeatScreen(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane sP = new StackPane();
        stage.setTitle("DEFEATED");
        VBox vbox = new VBox(-140);
        VBox vBox1 = new VBox();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();
        ImageView background = new ImageView(new Image("./media/Defeated.png"));
        background.setFitWidth(1200);
        background.setFitHeight(700);

        Text text = new Text("You Dealt " + myPlayer.getDamageDealt() + " Damage");
        text.setFont(Font.font(String.valueOf(text),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 20));
        text.setFill(Paint.valueOf("Yellow"));
        text.setStroke(Paint.valueOf("Black"));
        Text text1 = new Text("You Took " + myPlayer.getHealthPotsUsed() + " Health Potions");
        text1.setFont(Font.font(String.valueOf(text),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 20));
        text1.setFill(Paint.valueOf("Yellow"));
        text1.setStroke(Paint.valueOf("Black"));
        Text text2 = new Text("You Took " + myPlayer.getStrengthPotsUsed() + " Strength Potions");
        text2.setFont(Font.font(String.valueOf(text),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 20));
        text2.setFill(Paint.valueOf("Yellow"));
        text2.setStroke(Paint.valueOf("Black"));
        Text text3 = new Text("You Killed " + myPlayer.getMonstersKilled() + " Monsters");
        text3.setFont(Font.font(String.valueOf(text),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 20));
        text3.setFill(Paint.valueOf("Yellow"));
        text3.setStroke(Paint.valueOf("Black"));
        VBox vBox = new VBox();


        /* Show Avatar */
        playerAvatar = new ImageView(myPlayer.getAvatar());
        playerAvatar.setFitHeight(300);
        playerAvatar.setFitWidth(300);

        /* Quit Game Button Instantiation */
        quitGame = new Button("Quit");
        quitGame.setPrefHeight(50);
        quitGame.setPrefWidth(50);

        /* Quit Game Button Specifics */
        ImageView quitGameImage = new ImageView(new Image("./media/Daco_563398.png"));
        quitGame.setGraphic(quitGameImage);
        quitGame.setStyle("-fx-background-color: transparent; -fx-background-radius: 15px; "
                + "-fx-text-fill: #ffffff");
        quitGame.setOnAction(event -> {
            stage.close();
        });

        /* Start Game Button Instantiation */
        startGame = new Button("Play");
        startGame.setPrefWidth(50);
        startGame.setPrefHeight(50);

        /* Start Game Button Specifics */
        ImageView startGameImage = new ImageView(new Image("./media/pngwing.com.png"));
        startGameImage.setFitHeight(350);
        startGameImage.setFitWidth(550);
        startGame.setStyle("-fx-background-color: transparent; -fx-background-radius: 15px;"
                + " -fx-text-fill: #ffffff");
        startGame.setGraphic(startGameImage);
        startGame.setOnAction(event -> {
            InitialConfigurationScreen s2 = new InitialConfigurationScreen();
            try {
                s2.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        vbox.setAlignment(Pos.TOP_CENTER);
        hBox1.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text, text1, text2, text3);
        hBox1.getChildren().addAll(quitGame);
        hBox.getChildren().addAll(startGame, vBox);
        vbox.getChildren().addAll(playerAvatar);
        hBox1.setPadding(new Insets(-100, 0, 0, 40));
        hBox.setPadding(new Insets(20, 0, -40, 0));

        vBox1.getChildren().addAll(hBox, hBox1, vbox);
        vBox1.setAlignment(Pos.TOP_CENTER);

        sP.getChildren().addAll(background, vBox1);

        /* adding to main stage */
        stage.setScene(new Scene(sP));
        stage.show();
    }

    public Button getQuitGame() {
        return quitGame;
    }

    public void setQuitGame(Button quitGame) {
        this.quitGame = quitGame;
    }

    public Button getStartGame() {
        return startGame;
    }

    public void setStartGame(Button startGame) {
        this.startGame = startGame;
    }

    public ImageView getPlayerAvatar() {
        return this.playerAvatar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
