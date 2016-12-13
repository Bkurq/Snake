package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Bert on 2016-12-13.
 */
public class Start extends Group {
    private int position = 0;

    public Start() {
        VBox menu = new VBox();
        menu.setSpacing(30);
        menu.setPrefSize(400, 200);
        menu.setAlignment(Pos.CENTER);
        Text newGame = new Text("New Game");
        newGame.setFont(Font.font(30));
        Text exit = new Text("Exit");
        exit.setFont(Font.font(30));
        menu.getChildren().addAll(newGame, exit);
        this.getChildren().add(menu);
    }

    public void input(KeyCode key) {
        if (key == KeyCode.UP) {
            if(position > 0)
                position--;
        }
        else if (key == KeyCode.DOWN) {
            if(position < 1)
                position++;
        }
        else if(key == KeyCode.ENTER) {
            if(position == 0) {

            }
        }
    }
}
