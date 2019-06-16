package com.github.mouse0w0.game2048;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameUI extends AnchorPane {

    private final Block[][] blocks = new Block[4][4];
    private final Random random = new Random();

    private final Text maxPointText = new Text();
    private final Text currentPointText = new Text();
    private final Text gameOverText = new Text();
    private final BorderPane gameOverLayer = new BorderPane();

    private int maxPoint = 0;
    private int currentPoint = 0;

    public GameUI() {
        setBackground(new Background(new BackgroundFill(Color.gray(0.5), null, null)));

        BorderPane main = new BorderPane();
        main.setPadding(new Insets(10));
        getChildren().add(main);
        AnchorPane.setTopAnchor(main, 0D);
        AnchorPane.setRightAnchor(main, 0D);
        AnchorPane.setBottomAnchor(main, 0D);
        AnchorPane.setLeftAnchor(main, 0D);

        FlowPane top = new FlowPane();
        top.setAlignment(Pos.CENTER_RIGHT);
        top.setHgap(5);
        top.getChildren().addAll(new Text("最佳分数："), maxPointText, new Text("当前分数："), currentPointText);
        main.setTop(top);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 0, 10, 0));
        vBox.setSpacing(10);
        main.setCenter(vBox);

        for (int i = 0; i < 4; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            vBox.getChildren().add(hBox);
            for (int j = 0; j < 4; j++) {
                blocks[i][j] = new Block();
                hBox.getChildren().add(blocks[i][j]);
            }
        }

        Text bottom = new Text("↑/W：上 | ↓/S：下 | ←/A：左 | →/D：右 | R/ESC：重新开始");
        main.setBottom(bottom);

        getChildren().add(gameOverLayer);
        AnchorPane.setTopAnchor(gameOverLayer, 0D);
        AnchorPane.setRightAnchor(gameOverLayer, 0D);
        AnchorPane.setBottomAnchor(gameOverLayer, 0D);
        AnchorPane.setLeftAnchor(gameOverLayer, 0D);
        gameOverLayer.setCenter(gameOverText);
        gameOverLayer.setBackground(new Background(new BackgroundFill(Color.gray(0.7, 0.7), null, null)));
        gameOverText.setFont(new Font(20));
        gameOverLayer.setVisible(false);
    }

    public void up() {
        // 合并块
        for (int v = 1; v < 4; v++) {
            for (int h = 0; h < 4; h++) {
                int number = blocks[v][h].getNumber();
                if (number != 0 && number == blocks[v - 1][h].getNumber()) {
                    blocks[v - 1][h].setNumber(number * 2);
                    blocks[v][h].setNumber(0);
                }
            }
        }

        // 移动块
        for (int v = 1; v < 4; v++) {
            for (int h = 0; h < 4; h++) {
                for (int i = 0; i < v; i++) {
                    int number = blocks[v][h].getNumber();
                    if (blocks[i][h].getNumber() == 0) {
                        blocks[i][h].setNumber(number);
                        blocks[v][h].setNumber(0);
                    }
                }
            }
        }
        update();
    }

    public void down() {
        // 合并块
        for (int v = 2; v >= 0; v--) {
            for (int h = 0; h < 4; h++) {
                int number = blocks[v][h].getNumber();
                if (number != 0 && number == blocks[v + 1][h].getNumber()) {
                    blocks[v + 1][h].setNumber(number * 2);
                    blocks[v][h].setNumber(0);
                }
            }
        }

        // 移动块
        for (int v = 2; v >= 0; v--) {
            for (int h = 0; h < 4; h++) {
                for (int i = 3; i > v; i--) {
                    int number = blocks[v][h].getNumber();
                    if (blocks[i][h].getNumber() == 0) {
                        blocks[i][h].setNumber(number);
                        blocks[v][h].setNumber(0);
                    }
                }
            }
        }
        update();
    }

    public void left() {
        // 合并块
        for (int v = 0; v < 4; v++) {
            for (int h = 1; h < 4; h++) {
                int number = blocks[v][h].getNumber();
                if (number != 0 && number == blocks[v][h - 1].getNumber()) {
                    blocks[v][h - 1].setNumber(number * 2);
                    blocks[v][h].setNumber(0);
                }
            }
        }

        // 移动块
        for (int v = 0; v < 4; v++) {
            for (int h = 1; h < 4; h++) {
                for (int i = 0; i < h; i++) {
                    int number = blocks[v][h].getNumber();
                    if (blocks[v][i].getNumber() == 0) {
                        blocks[v][i].setNumber(number);
                        blocks[v][h].setNumber(0);
                    }
                }
            }
        }
        update();
    }

    public void right() {
        // 合并块
        for (int v = 0; v < 4; v++) {
            for (int h = 2; h >= 0; h--) {
                int number = blocks[v][h].getNumber();
                if (number != 0 && number == blocks[v][h + 1].getNumber()) {
                    blocks[v][h + 1].setNumber(number * 2);
                    blocks[v][h].setNumber(0);
                }
            }
        }

        // 移动块
        for (int v = 0; v < 4; v++) {
            for (int h = 2; h >= 0; h--) {
                for (int i = 3; i > h; i--) {
                    int number = blocks[v][h].getNumber();
                    if (blocks[v][i].getNumber() == 0) {
                        blocks[v][i].setNumber(number);
                        blocks[v][h].setNumber(0);
                    }
                }
            }
        }
        update();
    }

    public void update() {
        List<Block> zeroBlocks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j].getNumber() == 0) {
                    zeroBlocks.add(blocks[i][j]);
                }
            }
        }

        if (zeroBlocks.isEmpty()) {
            gameOver();
            return;
        }

        Block block = zeroBlocks.get(random.nextInt(zeroBlocks.size()));
        block.setNumber(random.nextInt(3) == 0 ? 4 : 2);

        updatePoint();
    }

    public void updatePoint() {
        currentPoint = sumPoint();
        currentPointText.setText(Integer.toString(currentPoint));
        if (currentPoint > maxPoint) {
            maxPoint = currentPoint;
            maxPointText.setText(Integer.toString(maxPoint));
        }
    }

    public void gameOver() {
        updatePoint();
        gameOverText.setText(String.format("游戏结束！您的分数为：%d。按R键重新开始", currentPoint));
        gameOverLayer.setVisible(true);
    }

    public void restart() {
        gameOverLayer.setVisible(false);
        clear();
        for (int i = 0; i < 3; i++) {
            update();
        }
    }

    public void clear() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j].setNumber(0);
            }
        }
    }

    public int sumPoint() {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += blocks[i][j].getNumber();
            }
        }
        return result;
    }
}
