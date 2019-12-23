package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class GameFrame {

	private int paddleLocation = 0;

	Group root = new Group();   
  
	// obiekt kuli
	/////////////////////////////////////////////
	Sphere ball = new Sphere();

	// obiekty palatek
	///////////////////////////////////////////////////////
//	Rectangle myPaddle = new Rectangle(20, 100, 20, 100);
//	Rectangle oppPaddle = new Rectangle(760, 400, 20, 100);
	
	Box myPaddle = new Box(20, 200, 50); 
	Box oppPaddle = new Box(20, 200, 50); 
	
	public GameFrame() {
		super();
		addBall(root);
		addMyPaddle(root);
		addOppPaddle(root);
		Scene secondScene = new Scene(root, 800, 600);
		secondScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				if (paddleLocation > 0)
					paddleLocation-=10;
			}

			if (e.getCode() == KeyCode.DOWN) {
				paddleLocation+=10;
			}
			
			myPaddle.setTranslateY(paddleLocation);
			
			System.out.println("paddleLocation: " + Integer.toString(getPaddleLocation()));
			System.out.println("keyCode: " + e.getCode().toString());

		});

		Stage newWindow = new Stage();
		newWindow.setTitle("Gra");
		newWindow.setScene(secondScene);
		newWindow.show();
	}

	public int getPaddleLocation() {
		return paddleLocation;
	}

	public void setPaddleLocation(int paddleLocation) {
		this.paddleLocation = paddleLocation;
	}

	// dodanie parametrow kuli
	/////////////////////////////////////////
	public void addBall(Group root) {
		ball.setRadius(15.0);
		ball.setTranslateX(200);
		ball.setTranslateY(150);
		root.getChildren().add(ball);
		//Group root = new Group(sphere);
		//return root;
	}

	// dodanie parametrow paletki
	/////////////////////////////////////////
	public void addMyPaddle(Group root) {
		myPaddle.setTranslateX(20);
	//	myPaddle.setTranslateY(150);
		root.getChildren().add(myPaddle);
		
	}

	// dodanie parametrow paletki
	/////////////////////////////////////////
	public void addOppPaddle(Group root) {
		oppPaddle.setTranslateX(780);
		oppPaddle.setTranslateY(150);
		root.getChildren().add(oppPaddle);
	}

}
