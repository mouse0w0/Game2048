package com.github.mouse0w0.game2048;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class Block extends BorderPane {

    private static final Map<Integer, Color> numberToColor = new HashMap<>();

    static {
        numberToColor.put(0, Color.WHITE);
        for (int i = 1; i < 16; i++) {
            numberToColor.put(1 << i, Color.rgb(255, 255, 255 * (15 - i) / 15));
        }
    }

    private int number;

    private Text text;

    public Block() {
        setPrefSize(100, 100);

        text = new Text();
        text.setFont(new Font(null, 20));
        setCenter(text);

        setNumber(0);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        text.setText(number == 0 ? "" : Integer.toString(number));
        setBackground(new Background(new BackgroundFill(numberToColor.get(number), null, null)));
    }
}
