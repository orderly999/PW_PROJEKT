package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;;

public class GameFrame {
	
	private int paddleLocation = 0;

	Group root = new Group();
	
	UDPConn uDPConn;

	// obiekty palatek
	///////////////////////////////////////////////////////
//	Rectangle myPaddle = new Rectangle(20, 100, 20, 100);
//	Rectangle oppPaddle = new Rectangle(760, 400, 20, 100);

	public Box  leftPaddle = new Box(20, 200, 50);
	public Box  rightPaddle= new Box(20, 200, 50);
	// obiekt kuli
	/////////////////////////////////////////////
	public Sphere ball = new Sphere();
	
	byte serverResult = 0;
	byte clientResult = 0;
	
	Text serverResultText;
	Text clientResultText;

	public GameFrame(UDPConn uDPConn) throws InterruptedException {
		super();
		this.uDPConn = uDPConn;
		
		serverResultText = addReflectionText(root, "0", 300, 200);
		addReflectionText(root, ":", 370, 200);
		clientResultText = addReflectionText(root, "0", 410, 200);
		
		addBall(root);
		addMyPaddle(root);
		addOppPaddle(root);
		
		Scene secondScene = new Scene(root, 800, 600);
		Timeline tl = new Timeline();
		GameController gc = new GameController(this, uDPConn.isServer);
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(10),  event -> {
            try {
				gc.run();

				uDPConn.sentFrame.myYPosition = paddleLocation;
				rightPaddle.setTranslateY(uDPConn.receivedFrame.myYPosition);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            }));
		
		
		secondScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				if (paddleLocation > 0)
					paddleLocation-=10;
			}

			if (e.getCode() == KeyCode.DOWN) {
				paddleLocation+=10;
			}
					
			leftPaddle.setTranslateY(paddleLocation);
			
			
			System.out.println("paddleLocation: " + Integer.toString(getPaddleLocation()));
			System.out.println("keyCode: " + e.getCode().toString());
		});
		
		Stage newWindow = new Stage();
		newWindow.setTitle("Gra");
		newWindow.setScene(secondScene);
		newWindow.show();
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();	
		
		
		// zamknij aplikacje
		/////////////////////////////////////////////
		newWindow.setOnCloseRequest(event -> {
			Platform.exit();
			if (!uDPConn.serverSocket.isClosed())
			uDPConn.serverSocket.close();
		});
		
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
		ball.setTranslateX(400);
		ball.setTranslateY(400);
		root.getChildren().add(ball);
		// Group root = new Group(sphere);
		// return root;
	}

	// dodanie parametrow paletki
	/////////////////////////////////////////
	public void addMyPaddle(Group root) {
		leftPaddle.setTranslateX(20);
		// myPaddle.setTranslateY(150);
		root.getChildren().add(leftPaddle);

	}

	// dodanie parametrow paletki
	/////////////////////////////////////////
	public void addOppPaddle(Group root) {
		rightPaddle.setTranslateX(780);
		rightPaddle.setTranslateY(150);
		root.getChildren().add(rightPaddle);
	}
	
	// dodanie tekstu
    /////////////////////////////////////////
		public Text addReflectionText(Group root, String text, int x, int y) {
			Text t  = new Text();
					t.setX(20);
					t.setY(50);
					t.setCache(true);
					t.setText(text);
					t.setFill(Color.LIGHTSLATEGREY);
					t.setFont(Font.font(null, FontWeight.BOLD, 80));
					Reflection r = new Reflection();
					t.setEffect(r);
			        t.setTranslateY(y);
			        t.setTranslateX(x);
			    	root.getChildren().add(t);
			    	return t;
		}

}
