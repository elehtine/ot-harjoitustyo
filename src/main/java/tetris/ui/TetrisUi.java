package tetris.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;

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
		painter.paint(game.getGrid(), game.getColors(), PX_WIDTH);


		root.getChildren().add(canvas);
		Scene scene = new Scene(root);

		primaryStage.setTitle("Tetris");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

