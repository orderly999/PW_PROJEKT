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

/**
 * @author KamilKruk
 * 
 */
public class GameFrame {
	
	private int paddleLocation = 0;

	Group root = new Group();
	
	UDPConn uDPConn;

	// obiekty palatek @PrzemysławJarek
	///////////////////////////////////////////////////////

	public Box  leftPaddle = new Box(20, 200, 50);
	public Box  rightPaddle= new Box(20, 200, 50);
	public Box powerUpBox = new Box(50,50,50);
	
	// obiekt kuli @PrzemysławJarek
	/////////////////////////////////////////////
	public Sphere ball = new Sphere();
	
	byte serverResult = 0;
	byte clientResult = 0;
	
	Text serverResultText;
	Text clientResultText;
	
	/**
	 * inicjalizacja ramki gry 
	 * @param uDPConn
	 * @throws InterruptedException
	 * @author PrzemysławJarek
	 */
	public GameFrame(UDPConn uDPConn) throws InterruptedException {
		super();
		this.uDPConn = uDPConn;
		
		serverResultText = addReflectionText(root, "0", 300, 20);
		addReflectionText(root, ":", 370, 20);
		clientResultText = addReflectionText(root, "0", 410, 20);
		
		addBall(root);
		addMyPaddle(root);
		addOppPaddle(root);
		addPowerUpBox(root);
		
		Scene secondScene = new Scene(root, 800, 600);
		Timeline tl = new Timeline();
		GameController gc = new GameController(this, uDPConn.isServer);
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(20),  event -> {
            try {
            	if (this.uDPConn.isClientActive)
				gc.run();

				uDPConn.sentFrame.myYPosition = paddleLocation;
				rightPaddle.setTranslateY(uDPConn.receivedFrame.myYPosition);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
            }));
		
		secondScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				if (paddleLocation > 100)
					paddleLocation-=10;
			}

			if (e.getCode() == KeyCode.DOWN) {
				if (paddleLocation < 500)
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
		
		
		// zamknij aplikacje @KamilKruk
		/////////////////////////////////////////////
		newWindow.setOnCloseRequest(event -> {
			Platform.exit();
			if (!uDPConn.serverSocket.isClosed())
			uDPConn.serverSocket.close();
		});
		
	}

	/**
	 * getter dla polozenia paletki 
	 * @return
	 * @author PrzemysławJarek
	 */
	public int getPaddleLocation() {
		return paddleLocation;
	}

	/**
	 * setter dla polozenia paletki
	 * @param paddleLocation
	 * @author PrzemysławJarek
	 */
	public void setPaddleLocation(int paddleLocation) {
		this.paddleLocation = paddleLocation;
	}

	/**
	 * dodanie parametrow kuli
	 * @param root
	 * @author PrzemysławJarek
	 */
	public void addBall(Group root) {
		ball.setRadius(15.0);
		ball.setTranslateX(400);
		ball.setTranslateY(400);
		root.getChildren().add(ball);
	}

	/**
	 * dodanie parametrow kuli
	 * @param root
	 * @author PrzemysławJarek
	 */
	public void addPowerUpBox(Group root) {
		powerUpBox.setTranslateX(400);
		powerUpBox.setTranslateY(300);
		root.getChildren().add(powerUpBox);
	}

	/**
	 * dodanie parametrow paletki 
	 * @param root
	 * @author PrzemysławJarek
	 */
	public void addMyPaddle(Group root) {
		leftPaddle.setTranslateX(20);
		// myPaddle.setTranslateY(150);
		root.getChildren().add(leftPaddle);

	}

	/**
	 * dodanie parametrow paletki 
	 * @param root
	 * @author PrzemysławJarek
	 */
	public void addOppPaddle(Group root) {
		rightPaddle.setTranslateX(780);
		rightPaddle.setTranslateY(150);
		root.getChildren().add(rightPaddle);
	}
	
		/**
		 * dodanie tekstu wyswietlajacego wynik
		 * @param root
		 * @param text
		 * @param x
		 * @param y
		 * @author KamilKruk
		 * @return
		 */
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
