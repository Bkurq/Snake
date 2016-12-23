package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
        theStage.setResizable(false);
        theStage.show();
    }

    private HBox createControls(Game game) {
        HBox menu = new HBox();
        menu.setSpacing(30);
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setAlignment(Pos.CENTER);

        Button newGame = new Button("New");
        newGame.setFont(Font.font(20));
        newGame.setOnAction(event -> {
            game.stopGame();
            game.restart();
        });

        Button pauseGame = new Button("Play/Pause");
        pauseGame.setFont(Font.font(20));
        pauseGame.setOnAction(event -> {
            game.startOrStop();
        });

        Button frameForward= new Button("Frame Forward");
        frameForward.setFont(Font.font(20));
        frameForward.setOnAction(event -> {
            game.frameForward();
        });

        menu.getChildren().addAll(newGame,pauseGame, frameForward);
        return menu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
