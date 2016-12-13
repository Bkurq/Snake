package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception{
        theStage.setTitle("Snake");

        Game root = new Game();
        Start start = new Start();
        Scene theScene = new Scene(start);
        theScene.setOnKeyPressed(event -> {
            root.input(event.getCode());
        });

        theStage.setScene(theScene);
        theStage.show();
        //root.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
