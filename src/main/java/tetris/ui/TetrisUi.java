package tetris.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import java.util.*;
import java.io.*;

import tetris.domain.Game;
import tetris.domain.UserHighScoreService;
import tetris.dao.FileUserDao;
import tetris.dao.FileHighScoreDao;

/**
 * Class handles ui window, texts and buttons
 */
public class TetrisUi extends Application {

	private Game game;
	private Painter painter;

	private UserHighScoreService userHighScoreService;

	private Scene gameScene;
	private Scene mainMenuScene;
	private Scene loginScene;
	private Scene createUserScene;
	private Scene highScoreScene;

	private String username;
	private String errorMessage;

	private final static int PX_WIDTH = 20;
	private final static String DEFAULT_NAME = "Unnamed";

	@Override
	public void init() throws Exception {
		username = DEFAULT_NAME;

		Properties properties = new Properties();
		properties.load(new FileInputStream("config.properties"));

        String userFile = properties.getProperty("userFile");
        String todoFile = properties.getProperty("highScoreFile");

        FileUserDao userDao = new FileUserDao(userFile);
        FileHighScoreDao highScoreDao = new FileHighScoreDao(todoFile);

		userHighScoreService = new UserHighScoreService(userDao, highScoreDao);
	}

	@Override
	public void start(Stage primaryStage) {
		
		// Tetris game scene
		
		Canvas canvas = new Canvas(500, 600);
		Group root = new Group();
		root.getChildren().addAll(canvas);
		Scene gameScene = new Scene(root);
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    game.rotate(); break;
                    case DOWN:  game.drop(); break;
                    case LEFT:  game.move(-1); break;
                    case RIGHT: game.move(1); break;
                    case SPACE: game.hardDrop(); break;
                    case ESCAPE:
						stopGames();
						primaryStage.setScene(mainMenuScene);
						break;
                }
            }
        });


		// Main menu scene

		HBox mainMenuPane = new HBox(10);
        VBox buttonPane = new VBox(10);
        mainMenuPane.setPadding(new Insets(10));

        Label userNameLabel = new Label(username);
        
        Button changeUserButton = new Button("Change User");
        Button createUserButton = new Button("Create User");
        Button gameButton = new Button("Play Tetris");
        Button highScoreButton = new Button("Show High Scores");
        buttonPane.getChildren().addAll(changeUserButton, createUserButton, gameButton, highScoreButton);

        changeUserButton.setOnAction(e -> {
			primaryStage.setScene(loginScene);
			errorMessage = "";
		});
        createUserButton.setOnAction(e -> {
			primaryStage.setScene(createUserScene);
			errorMessage = "";
		});
        gameButton.setOnAction(e -> {
			primaryStage.setScene(gameScene);
			game = new Game();
			painter = new Painter(canvas.getGraphicsContext2D(), game, userHighScoreService);
			game.start();
			painter.start();
			errorMessage = "";
		});
        highScoreButton.setOnAction(e -> {
			primaryStage.setScene(highScoreScene);
			errorMessage = "";
		});
        
        mainMenuPane.getChildren().addAll(buttonPane, userNameLabel);
        mainMenuScene = new Scene(mainMenuPane, 300, 250);    
		
		// Login scene
		

		VBox loginPane = new VBox(10);
		HBox userNamePane = new HBox(10);
		TextField loginUsernameInput = new TextField();
		TextField loginPasswordInput = new TextField();
		Label loginUsernameLabel = new Label("Username: ");
		Label loginPasswordLabel = new Label("Password: ");
		Label loginErrorLabel = new Label(errorMessage);

		Button loginButton = new Button("Login");
		Button mainMenuButton = new Button("Back");

		mainMenuButton.setOnAction(e -> primaryStage.setScene(mainMenuScene));
		loginButton.setOnAction(e -> {
			String username = loginUsernameInput.getText();
			String password = loginPasswordInput.getText();
			if (userHighScoreService.isCorrectLogin(username, password)) {
				this.username = username;
				userNameLabel.setText(username);
				errorMessage = "";
				primaryStage.setScene(mainMenuScene);
			} else {
				errorMessage = "Incorrect username or password";
				loginErrorLabel.setText(errorMessage);
			}
		});
		
		userNamePane.getChildren().addAll(loginUsernameLabel, loginUsernameInput,
				loginPasswordLabel, loginPasswordInput,
				loginButton, mainMenuButton);
		loginPane.getChildren().addAll(userNamePane, loginErrorLabel);
		loginScene = new Scene(loginPane);

		// Create user scene


		VBox createUserPane = new VBox(10);
		HBox newUserPane = new HBox(10);
		TextField newUsernameInput = new TextField();
		TextField newPasswordInput = new TextField();
		Label newUsernameLabel = new Label(errorMessage);
		Label newPasswordLabel = new Label(errorMessage);
		Label createUserErrorLabel = new Label(errorMessage);

		Button createButton = new Button("Create");
		Button backMainMenuButton = new Button("Back");

		backMainMenuButton.setOnAction(e -> primaryStage.setScene(mainMenuScene));
		createButton.setOnAction(e -> {
			String username = newUsernameInput.getText();
			String password = newPasswordInput.getText();
			if (userHighScoreService.isOkUsername(username)) {
				this.username = username;
				userNameLabel.setText(username);
				userHighScoreService.addUser(username, password);
				errorMessage = "";
				primaryStage.setScene(mainMenuScene);
			} else {
				errorMessage = "Incorrect username or password";
				createUserErrorLabel.setText(errorMessage);
			}
		});
		
		newUserPane.getChildren().addAll(newUsernameLabel, newUsernameInput,
				newPasswordLabel, newPasswordInput,
				createButton, backMainMenuButton);
		createUserPane.getChildren().addAll(newUserPane, createUserErrorLabel);
		createUserScene = new Scene(createUserPane);


		// Set first scene
		primaryStage.setTitle("Tetris");
		primaryStage.setScene(mainMenuScene);
		primaryStage.show();

	}

	@Override
	public void stop() {
		stopGames();
	}

	/**
	 * Method for stopping game threads
	 *
	 * If multiplayer mode, stop all games
	 */
	private void stopGames() {
		game.terminate();
		painter.terminate();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

