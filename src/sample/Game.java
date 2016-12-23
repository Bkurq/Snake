package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by Bert on 2016-12-12.
 */
public class Game extends Group{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private GraphicsContext gc;
    private Timeline gameLoop;
    private boolean running;
    private BooleanProperty gameOver = new SimpleBooleanProperty(false);

    private int x[] = new int[10];
    private int y[] = new int[10];
    private int length;
    private final int SIDE_LENGTH = 10;
    private final int velocity = 1;
    private int xVelocity = velocity;
    private int yVelocity = 0;
    private double time;
    private long timeStart;

    public Game() {
        restart();

        gameOver.addListener((observable, oldValue, newValue) -> {
            gc.setFont(Font.font(40));
            gc.setFill(Color.RED);
            gc.fillText("GAME OVER", 210, 500);
            gc.setFill(Color.BLACK);
        });

        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);

        timeStart = System.currentTimeMillis();
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
        gameLoop.setCycleCount(1);
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
    }

    public void restart() {
        running = false;
        xVelocity = velocity;
        yVelocity = 0;
        length = 3;
        for(int a = 0; a < length; a++) {
            x[a] = WIDTH / 2 + (2-a) * SIDE_LENGTH;
            y[a] = HEIGHT / 2;
        }
        gameOver.set(false);
        timeStart = System.currentTimeMillis();
    }

    public void startOrStop() {
        if(running)
            stopGame();
        else
            startGame();
    }

    public int input(KeyCode key) {
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
        return -1;
    }

    private void clear() {
        gc.clearRect(0, 0, WIDTH,HEIGHT);
    }

    private void drawStage() {
        gc.setStroke(Color.RED);
        gc.strokeRect(1, 1, WIDTH - 1, HEIGHT - 1);
        gc.setStroke(Color.BLACK);
    }

    private void moveSnake() {
        for(int i = (length-1); i > 0; i--) {
            x[i] = x[i - 1] - 10;
            y[i] = y[i - 1];

        }
        x[0] += xVelocity;
        y[0] += yVelocity;
    }

    private void drawSnake() {
        for(int i = 0; i < length; i++) {
            gc.fillRect(x[i], y[i], 10, 10);
        }
    }

    private void checkRules() {
        if(x[0] <= 1 || x[0] >= WIDTH - 11 || y[0] <= 1 || y[0] >= HEIGHT - 11) {
            gameOver.set(true);
            stopGame();
        }
    }

    public double getTime() {
        return time / 1000;
    }

    public void frameForward() {
        if(!gameOver.getValue()) {
            gameLoop.setCycleCount(1);
            gameLoop.play();
            gameLoop.setCycleCount(Timeline.INDEFINITE);
        }
    }

    public void startGame() {
        if(!gameOver.getValue()) {
            gameLoop.play();
            running = true;
        }
    }

    public void stopGame() {
        gameLoop.stop();
        running = false;
    }
}