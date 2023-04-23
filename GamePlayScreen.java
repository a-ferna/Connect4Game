import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

// This class implements all the different components
// present in the game screen shown while playing.
public class GamePlayScreen {
	
	private Stage primaStage;
	
	private GameButton checker;

	private BorderPane pane;
	
	private GridPane grid;
	
	private MenuBar topMenuBar;
	
	private TextField turnMessage;
	
	private TextField moveDisplay;
	
	private MenuBar themesBar;
	
	private int turn = 0;
	
	private Scene howToPlayScreen;
	
	private Scene gameScene;
	
	private GameControl gameControl;
	
	private Scene winScreen;
	
	private GameButton lastMove;
	
	
	public Scene createGameScreen(Stage primaryStage) {

		primaStage = primaryStage;
		
		gameControl = new GameControl();
		
		pane = new BorderPane();

		addGrid();				//initialize grid with GameButtons
		createTopMenuBar();		//main game options
		createThemesMenu();		//option to choose different themes
		setTurnDisplay();
		setMoveDisplay();
		
		VBox paneTop = new VBox(topMenuBar, turnMessage);
		paneTop.setStyle("-fx-background-color: lightBlue");
		
		VBox paneBottom = new VBox(moveDisplay, themesBar);
		paneBottom.setStyle("-fx-background-color: lightBlue");
		
		pane.setTop(paneTop);
		pane.setCenter(grid);
		pane.setBottom(paneBottom);
		
		gameScene = new Scene(pane, 600, 600);
		return gameScene;
	}
	
	
	
	// can i move to game button?
	private EventHandler<ActionEvent> checkerHandler = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			checker = (GameButton)e.getSource();
			
			int c = GridPane.getColumnIndex(checker);
			int r =GridPane.getRowIndex(checker);
			
			checker.setCoors(GridPane.getRowIndex(checker), GridPane.getColumnIndex(checker));

			if(turn%2 == 0) {
				turnMessage.setText("Player 2");	//next turn
				checker.setStyle("-fx-background-color: crimson");
				checker.setPlayer(1);
				checker.setCoors(GridPane.getRowIndex(checker), GridPane.getColumnIndex(checker));
				turnMessage.setStyle("-fx-background-color: lightgrey;"+"-fx-font-size: 20;"+"-fx-border-color: gold;"+"-fx-border-width: 3;");
				
				gameControl.addChecker(r, c, 1);
				// if return true winning screen
				if (gameControl.checkforWin(1)) {
					grid.setDisable(true);
					PauseTransition pause =new PauseTransition(Duration.seconds(3));
					pause.play();
					pause.setOnFinished(new EventHandler<ActionEvent> () {
						public void handle(ActionEvent action) {
							createWinorTieScreen("Player 1 Won!");
						}
					});
				}
				
				String move = "Player 1 moved to " + (r+1) + "," + (c+1);
				moveDisplay.setText(move);
				//invalid move
				
				
			} else {
				turnMessage.setText("Player 1");	//next turn
				checker.setStyle("-fx-background-color: gold");	
				checker.setPlayer(2);
				turnMessage.setStyle("-fx-background-color: lightgrey;"+"-fx-font-size: 20;"+"-fx-border-color: red;"+"-fx-border-width: 3;");
				
				gameControl.addChecker(r, c, 2);
				// if return true winning screen
				if (gameControl.checkforWin(2)) {
					grid.setDisable(true);
					PauseTransition pause =new PauseTransition(Duration.seconds(3));
					pause.play();
					pause.setOnFinished(new EventHandler<ActionEvent> () {
						public void handle(ActionEvent action) {
							createWinorTieScreen("Player 2 Won!");
						}
					});
				}
				
				String move = "Player 2 moved to " + (r+1) + "," + (c+1);
				moveDisplay.setText(move);
				//invalid move
				

			}
			
			if (gameControl.checkforTie()) {
				grid.setDisable(true);
				PauseTransition pause =new PauseTransition(Duration.seconds(3));
				pause.play();
				pause.setOnFinished(new EventHandler<ActionEvent> () {
					public void handle(ActionEvent action) {
						createWinorTieScreen("It's a tie!");
					}
				});
			}
			
			checker.setPrevMove(lastMove);
			lastMove = checker;
			checker.setDisable(true);	
			++turn;
		}
	};

	
	public void createWinorTieScreen(String m) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: royalBlue");
		
		Button newGame = new Button("Play new game");
		newGame.setShape(new Rectangle(70,30));
		newGame.setStyle("-fx-font-size: 40;"+"-fx-background-color: gold;"+"-fx-border-color: black;"
						+"-fx-border-width: 1;"+"-fx-font-weight: bold;");
		newGame.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaStage.close();
				JavaFXTemplate newGame = new JavaFXTemplate();
				newGame.startNewGame(new Stage());
			}
		});
		
		Button exit = new Button("Exit");
		exit.setStyle("-fx-font-size: 30;"+"-fx-background-color: red;"+"-fx-border-color: black;"
						+"-fx-border-width: 1;"+"-fx-font-weight: bold;");
		exit.setOnAction(e->{System.exit(0);});
		
		TextField message = new TextField(m);
		message.setStyle("-fx-font-size: 50;"+"-fx-font-weight: bold;");
		message.setAlignment(Pos.CENTER);
		message.setEditable(false);
		
		VBox vbox2 = new VBox(60, message, newGame, exit);
		vbox2.setAlignment(Pos.CENTER);
		pane.setCenter(vbox2);
		
		winScreen = new Scene(pane, 600, 600);
		primaStage.setScene(winScreen);
	}
	
	
	public void createHowToPlayScreen() {
		BorderPane pane = new BorderPane();
		
		Button back = new Button("<- Back");
		back.setStyle("-fx-font-size: 15;"+"-fx-background-color: goldenrod;");
		back.setOnAction(e-> {
			primaStage.setScene(gameScene);
		});
		
		TextField title= new TextField("How to Play");
		title.setAlignment(Pos.CENTER);
		title.setStyle("-fx-font-size: 30;"+"-fx-background-color: gold;");
		title.setEditable(false);
		
		VBox vbox = new VBox(20, back, title);
		vbox.setStyle("-fx-background-color: royalBlue;");
		pane.setTop(vbox);
		
		String rules = "Connect Four is played on a grid of 7 columns and 6 rows.\n"
				+ "It is a two player game where each player takes a turn dropping\n"
				+ "a checker into a slot (one of the columns) on the game board.\n"
				+ "That checker will fall down the column and either land on top\n"
				+ "of another checker or land on the bottom row.\n"
				+"To win the game, a player needs to get 4 of their checkers in\n"
				+ "a vertical, horizontal or row before the other player";
		Text inst = new Text(rules);
		inst.setStyle("-fx-font-size: 20");
		
		Image gamePic = new Image("how.png");
		ImageView v = new ImageView(gamePic);
		v.setFitHeight(250);
		v.setFitWidth(250);
		
		VBox vInst = new VBox(10, inst, v);
		vInst.setAlignment(Pos.CENTER);
		
		pane.setCenter(vInst);
		pane.setStyle("-fx-background-color: lightBlue");
		howToPlayScreen = new Scene(pane, 600, 600);
	}
	
	
	// initialize the playing grid with instances of GameButton that will represent the checkers
	public void addGrid() {
		
		grid = new GridPane();
		
		for(int x = 0; x<7; x++) {
			for(int i = 0; i<6; i++) {
				
				GameButton checker = new GameButton(x, i);
				checker.setStyle("-fx-background-color: lightGray");
				checker.setOnAction(checkerHandler);
				grid.add(checker, x, i);
			}
		}
		
		grid.setAlignment(Pos.CENTER);
		//grid.setStyle("-fx-background-color: royalBlue;");
	}
	
	
	public void setMoveDisplay() {
		moveDisplay = new TextField("Start!");
		moveDisplay.setEditable(true);
		moveDisplay.setAlignment(Pos.CENTER);
		VBox.setMargin(moveDisplay, new Insets(20, 150, 30, 150));
		moveDisplay.setStyle("-fx-background-color: orange;"+"-fx-font-size: 20;"+"-fx-border-color: black;");
	}
	
	//set display showing player's turn
	public void setTurnDisplay() {
		turnMessage = new TextField("Player 1");
		turnMessage.setEditable(false);
		turnMessage.setAlignment(Pos.CENTER);
		VBox.setMargin(turnMessage, new Insets(30, 225, 20, 225));
		turnMessage.setStyle("-fx-background-color: lightgrey;"+"-fx-font-size: 20;"+"-fx-border-color: red;"+"-fx-border-width: 3;");
	}
	
	
	//provides options for different theme options
	public void createThemesMenu() {
		
		themesBar = new MenuBar();
		Menu themesMenu = new Menu("Themes");
		MenuItem theme1 = new MenuItem("theme 1");
		MenuItem theme2 = new MenuItem("theme 2");
		MenuItem theme3 = new MenuItem("theme 3");
		themesMenu.getItems().addAll(theme1, theme2, theme3);
		themesMenu.setStyle("-fx-font-size:17;"+"-fx-background-color: goldenrod");
		themesBar.getMenus().add(themesMenu);
		themesBar.setStyle("-fx-background-color: royalBlue;"+"-fx-border-color: black;");
	}
	
	
	//gets called my createTopMenuBar()
	public Menu createPlayMenu() {
		
		Menu playMenu = new Menu("Game Play");
		MenuItem reverseMove = new MenuItem("Reverse Move");
		playMenu.getItems().addAll(reverseMove);
		
		playMenu.setStyle("-fx-font-size:17;"+"-fx-background-color: goldenrod");
		
		reverseMove.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				GameButton toRemove = lastMove;
				lastMove = lastMove.getprevMove();
				
				gameControl.addChecker(toRemove.getx(), toRemove.gety(), 0);
				toRemove.setStyle("-fx-background-color: lightGray");
				toRemove.setDisable(false);	
				
				String move;
				if (lastMove == null) {
					turn = 0;
					moveDisplay.setText("Start!");
				} else {
					turn = lastMove.getPlayer();
					int c = GridPane.getColumnIndex(lastMove);
					int r =GridPane.getRowIndex(lastMove);
					move = "Player moved to " + (r+1) + "," + (c+1);
					moveDisplay.setText(move);
				}
				
				if(turn%2 == 0) {
					turnMessage.setText("Player 1");	//next turn
					turnMessage.setStyle("-fx-background-color: lightgrey;"+"-fx-font-size: 20;"+"-fx-border-color: red;"+"-fx-border-width: 3;");
					
				} else {
					turnMessage.setText("Player 2");	//next turn	
					turnMessage.setStyle("-fx-background-color: lightgrey;"+"-fx-font-size: 20;"+"-fx-border-color: gold;"+"-fx-border-width: 3;");
				}
		
			}
		});
		
		return playMenu;
	}
	
	
	//gets called my createTopMenuBar()
	public Menu createOptionsMenu() {
		
		Menu optionsMenu = new Menu("Options");
		MenuItem howToPlay = new MenuItem("How to play");
		MenuItem newGame = new MenuItem("New Game");
		MenuItem exit = new MenuItem("Exit Game");
		optionsMenu.getItems().addAll(howToPlay, newGame, exit);
		
		optionsMenu.setStyle("-fx-font-size:17;"+"-fx-background-color: goldenrod");
		
		howToPlay.setOnAction(e-> {
			createHowToPlayScreen();
			primaStage.setScene(howToPlayScreen);
			
		});
		newGame.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaStage.close();
				JavaFXTemplate newGame = new JavaFXTemplate();
				newGame.startNewGame(new Stage());
			}
		});

		 exit.setOnAction(e->{System.exit(0);});
		
		return optionsMenu;
	}
	
	
	public void createTopMenuBar() {
		
		topMenuBar = new MenuBar();
		Menu playGameMenu = createPlayMenu();
		Menu optionsMenu = createOptionsMenu();
		topMenuBar.getMenus().addAll(playGameMenu, optionsMenu);
		
		topMenuBar.setStyle("-fx-background-color: royalBlue;"+"-fx-border-color: black;");
	}
}
