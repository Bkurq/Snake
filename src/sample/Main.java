package sample;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception{
        theStage.setTitle("Snake");
        VBox root = new VBox();
        Game game = new Game();
        HBox controls = createControls(game);
        root.getChildren().addAll(game, controls);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            game.input(event.getCode());
            event.consume();
        });
        theStage.setScene(scene);
        theStage.show();
    }

    private HBox createControls(Game game) {
        HBox menu = new HBox();
        menu.setSpacing(30);
        menu.setPadding(new Insets(30, 30, 30, 30));
        menu.setAlignment(Pos.CENTER);

        ScaleTransition scaleUp = new ScaleTransition(new Duration(200));
        scaleUp.setFromX(1);
        scaleUp.setFromY(1);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);

        ScaleTransition scaleDown = new ScaleTransition(new Duration(200));
        scaleDown.setFromX(1.1);
        scaleDown.setFromY(1.1);
        scaleDown.setToX(1);
        scaleDown.setToY(1);

        Button newGame = new Button("New");
        newGame.setFont(Font.font(20));
        newGame.setOnAction(event -> {
            game.restart();
            game.startGame();
        });

        Button pauseGame = new Button("Pause");
        pauseGame.setFont(Font.font(20));
        pauseGame.setOnAction(event -> {
            game.startOrStop();
        });

        Button exit = new Button("Exit");
        exit.setFont(Font.font(20));
        exit.setOnAction(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(newGame,pauseGame, exit);
        return menu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
