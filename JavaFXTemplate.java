import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class JavaFXTemplate extends Application {
	
	GamePlayScreen gamePlay;
	
	Scene welcomeScreen;
	
	Scene gameScreen;
	
	Button start;
	
	Stage gameStage;
	
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		startNewGame(primaryStage);
	}
	
	public void startNewGame(Stage primaryStage) {
		primaryStage.setTitle("Welcome to Connect Four!");
		
		gamePlay = new GamePlayScreen();

		// game start with Welcome Screen
		createWelcomeScreen(primaryStage);
		primaryStage.setScene(welcomeScreen);
		
		primaryStage.show();
	}
		
	
	private void createWelcomeScreen(Stage primaryStage) {
		
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: royalBlue");
		
		Image pic = new Image("conect4.jpg");
		ImageView v = new ImageView(pic);
		v.setFitHeight(400);
		v.setFitWidth(600);
		pane.setCenter(v);

		start =  new Button("Start Game");
		start.setShape(new Rectangle(70,30));
		start.setStyle("-fx-font-size: 30;"+"-fx-background-color: gold;"+"-fx-border-color: black;"
						+"-fx-border-width: 1;"+"-fx-font-weight: bold;");
		BorderPane.setMargin(start, new Insets(0,200,50,200));
		pane.setBottom(start);
		//start button switches to game screen
		start.setOnAction(e-> { gameScreen = gamePlay.createGameScreen(primaryStage);
								primaryStage.setScene(gameScreen);});

		
		welcomeScreen = new Scene(pane, 600, 600);
	}
}



