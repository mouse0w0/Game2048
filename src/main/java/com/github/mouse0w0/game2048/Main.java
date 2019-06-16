package com.github.mouse0w0.game2048;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        GameUI gameUI = new GameUI();
        Scene scene = new Scene(gameUI);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                case W:
                    gameUI.up();
                    break;
                case DOWN:
                case S:
                    gameUI.down();
                    break;
                case LEFT:
                case A:
                    gameUI.left();
                    break;
                case RIGHT:
                case D:
                    gameUI.right();
                    break;
                case R:
                case ESCAPE:
                    gameUI.restart();
                    break;
            }
        });
        gameUI.restart();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game 2048");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
