package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AssignmentTemplate extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private Scene scene;
	private TabPane root;
	private BorderPane tab1Pane;
	private Tab tab1, tab2;
	private Label questionLabel,scoreLabel;
	private Button answersButtons[] = new Button[4];
	private int score;

	public void start(Stage stage) throws Exception {
		stage.setTitle("Software Architectures � Petar Stanev");
		root = new TabPane();
		scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		// ----------------------------------
		tab1 = new Tab();
		tab1.setText("Game");
		tab1.setClosable(false);
		// write

		// ----Game Start
		// ---Top Start
		tab1Pane = new BorderPane();

		// ----Top End

		setUpStartScreen();

		// ----Game End
		// ------------Bottom Start------------

		// ------------Bottom End------------
		tab1.setContent(tab1Pane);
		// end tab1
		root.getTabs().add(tab1);
		// ----------------------------------

		// ----------------------------------
		tab2 = new Tab();
		tab2.setText("Score");
		tab2.setClosable(false);
		root.getTabs().add(tab2);
		// ----------------------------------
		stage.show();

	}

	public void setUpStartScreen() {
		Button startGame = new Button("startGame");
		startGame.setStyle("-fx-font: 48 arial; -fx-base: #b6e7c9;");

		startGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGame.setVisible(false);
				setUpGame();
			}
		});

		tab1Pane.setCenter(startGame);
	}

	public void setUpGame() {
		scoreLabel = new Label("Score: " + score);
		scoreLabel.setStyle("-fx-font: 32 arial; -fx-base: #b6e7c9;");
		scoreLabel.setAlignment(Pos.CENTER);
		BorderPane topPane = new BorderPane();
		
		//topPane.setPadding(new Insets(10));
		

		questionLabel = new Label();
		questionLabel.setStyle("-fx-font: 48 arial; -fx-base: #b6e7c9;");
		//topPane.setCenter();
		topPane.setRight(scoreLabel);
		tab1Pane.setTop(topPane);
		tab1Pane.setCenter(questionLabel);
		//----- Top end
		
		
		
		//------ Bottom start
		HBox answersBox = new HBox(10);
		answersBox.setAlignment(Pos.CENTER);
		answersBox.setPadding(new Insets(10));
		tab1Pane.setBottom(answersBox);

		for (int i = 0; i < answersButtons.length; i++) {
			answersButtons[i] = new Button();
			answersButtons[i]
					.setStyle("-fx-font: 48 arial; -fx-base: #b6e7c9;");
			answersBox.getChildren().add(answersButtons[i]);
		}

		generateQuestion(10);
	}

	public void generateQuestion(int maximumNumber) {
		Question question = new Question(maximumNumber);
		questionLabel.setText(question.getQuestion());

		for (int i = 0; i < answersButtons.length; i++) {
			answersButtons[i].setText(Integer.toString(question.getAnswer(i)));
			if (i == question.getCorrectAnswerPositon()) {
				answersButtons[i].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						generateQuestion(10);
						updateScore(100);
					}
				});
			} else {
				// score--
				answersButtons[i].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						updateScore(-50);
					}
				});
			}
		}
	}
	
	public void updateScore(int scoreChange){
		score+=scoreChange;
		scoreLabel.setText("Score: "+ score);
	}
}