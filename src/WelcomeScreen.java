package src;

import javafx.animation.FadeTransition;
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
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class WelcomeScreen extends Application {
    @Override
    public void start(Stage primaryStage) {

        /* Initialization Frame */
        StackPane sP = new StackPane();
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();
        primaryStage.setTitle("WELCOME PLAYER");

        /* Set Welcome Screen Background */
        ImageView background = new ImageView(new Image("./media/game_background_4.png"));
        background.setFitWidth(1200);
        background.setFitHeight(700);

        /* Welcome Player Initial Text */
        Text welcomeText = new Text("WELCOME PLAYER..");
        welcomeText.setFont(Font.font(String.valueOf(welcomeText),
                FontWeight.EXTRA_BOLD, FontPosture.findByName("News Gothic"), 100));
        welcomeText.setFill(Paint.valueOf("Yellow"));
        welcomeText.setStroke(Paint.valueOf("Black"));

        /* Welcome Player Text Fade Transition */
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(5000));
        fade.setFromValue(10);
        fade.setToValue(0.1);
        fade.setCycleCount(1000);
        fade.setAutoReverse(true);
        fade.setNode(welcomeText);
        fade.play();

        /* Start Game Button Instantiation */
        Button startGame = new Button();
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
                s2.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //primaryStage.setScene(s2.scene1);
            //openInitPlayerConfigScreen();
            //primaryStage.close();
        });

        /* Quit Game Button Instantiation */
        Button quitGame = new Button();
        quitGame.setPrefHeight(50);
        quitGame.setPrefWidth(50);

        /* Quit Game Button Specifics */
        ImageView quitGameImage = new ImageView(new Image("./media/Daco_563398.png"));
        quitGame.setGraphic(quitGameImage);
        quitGame.setStyle("-fx-background-color: transparent; -fx-background-radius: 15px; "
                + "-fx-text-fill: #ffffff");
        quitGame.setOnAction(event -> {
            primaryStage.close();
        });


        /* Scene Formatting */
        hBox1.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(quitGame);
        hBox.getChildren().addAll(startGame);
        hBox1.setPadding(new Insets(-100, 0, 0, 40));
        hBox.setPadding(new Insets(20, 0, -40, 0));

        vBox.getChildren().addAll(welcomeText, hBox, hBox1);
        vBox.setAlignment(Pos.TOP_CENTER);

        sP.getChildren().addAll(background, vBox);
        primaryStage.setScene(new Scene(sP));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}