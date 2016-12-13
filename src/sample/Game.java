package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Created by Bert on 2016-12-12.
 */
public class Game extends Group {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private GraphicsContext gc;
    private Timeline gameLoop;
    private boolean running = false;

    private int x = WIDTH / 2;
    private int y = HEIGHT / 2;
    private final int velocity = 1;
    private int xVelocity = velocity;
    private int yVelocity = 0;
    private double time;

    public Game() {
        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        gc = canvas.getGraphicsContext2D();

        this.getChildren().add(canvas);

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> {
                    time = (System.currentTimeMillis() - timeStart) / 1000.0;  //seconds elapsed since game start
                    clear();
                    drawStage();
                    moveSnake();
                    drawSnake();
                    checkRules();
                });

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);
    }

    private void clear() {
        gc.clearRect(0, 0, WIDTH,HEIGHT);
    }

    private void drawStage() {
        gc.setStroke(Color.RED);
        gc.strokeRect(1, 1, WIDTH - 1, HEIGHT - 1);
    }

    private void moveSnake() {
        x += xVelocity;
        y += yVelocity;
    }

    private void drawSnake() {
        gc.fillRect(x, y, 10, 10);
    }

    private void checkRules() {
        if(x <= 1 || x >= WIDTH - 11 || y <= 1 || y >= HEIGHT - 11) {
            stopGame();
        }
    }

    public void startGame() {
        gameLoop.play();
        running = true;
    }

    public void stopGame() {
        gameLoop.stop();
        running = false;
    }

    public void input(KeyCode key) {
        if (key == KeyCode.UP) {
            xVelocity = 0;
            yVelocity = -velocity;
        }
        else if (key == KeyCode.DOWN) {
            xVelocity = 0;
            yVelocity = velocity;
        }
        else if (key == KeyCode.RIGHT) {
            xVelocity = velocity;
            yVelocity = 0;
        }
        else if(key == KeyCode.LEFT) {
            xVelocity = -velocity;
            yVelocity = 0;
        }
        else if(key == KeyCode.ENTER) {
            if(running) {
                stopGame();
            }
            else {
                startGame();
            }
        }
    }
}
