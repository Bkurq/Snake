package sample;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Bert on 2016-12-12.
 */
public class Game extends Group {
    private Canvas board;
    private GraphicsContext pen;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public Game() {
        board = new Canvas(WIDTH, HEIGHT);
        pen = board.getGraphicsContext2D();

        this.getChildren().add(board);
        runGame();
        board.setOnKeyPressed(event -> {
            event.getCode();});

    }

    public void runGame() {
        pen.setFill(Color.BLACK);
        pen.fillRect(300, 300, 5, 5);
    }

}
