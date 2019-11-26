package tetris.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

import tetris.domain.*;

public class TetrisUi extends Application {

	private Scene gameScene;
	private final int PX_WIDTH = 20;

	@Override
	public void start(Stage primaryStage) {
		// Tetris game scene
		// Add other scenes later

		Group root = new Group();
		Canvas canvas = new Canvas(500, 600);

		Game game = new Game();
		Painter painter = new Painter(canvas.getGraphicsContext2D());
		//painter.paint(game.getGrid(), game.getColors(), PX_WIDTH);
		int[][] grid = game.getGrid();
		grid[5][15] = 1;
		painter.paint(grid, game.getColors(), PX_WIDTH);


		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
				System.out.println(event.toString());
                switch (event.getCode()) {
                    case UP:    game.rotate(); break;
                    case DOWN:  game.drop(); break;
                    case LEFT:  game.move(-1); break;
                    case RIGHT: game.move(1); break;
                    case SPACE: game.hardDrop(); break;
                }
				painter.paint(game.getGrid(), game.getColors(), PX_WIDTH);
            }
        });

		primaryStage.setTitle("Tetris");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

