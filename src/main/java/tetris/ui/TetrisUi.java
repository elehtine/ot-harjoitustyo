package tetris.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

import tetris.domain.Game;

public class TetrisUi extends Application {

	private Game game;
	private Painter painter;
	private Scene gameScene;
	private final int PX_WIDTH = 20;

	@Override
	public void start(Stage primaryStage) {
		// Tetris game scene
		// Add other scenes later
		Canvas canvas = new Canvas(500, 600);
		game = new Game();
		painter = new Painter(canvas.getGraphicsContext2D(), game);
		game.start();
		painter.start();

		Group root = new Group();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    game.rotate(); break;
                    case DOWN:  game.drop(); break;
                    case LEFT:  game.move(-1); break;
                    case RIGHT: game.move(1); break;
                    case SPACE: game.hardDrop(); break;
                }
            }
        });
		primaryStage.setTitle("Tetris");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		game.terminate();
		painter.terminate();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

