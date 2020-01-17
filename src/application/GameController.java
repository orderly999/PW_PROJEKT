package application;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class GameController {

	GameFrame gameFrame;
	boolean isServer;

//	Sphere ball =  gameFrame.ball;
//	Box leftPaddle =  gameFrame.myPaddle;
//	Box rightPaddle =   gameFrame.oppPaddle;
	boolean ifStarted = false;
	boolean ifCollision;
	String collisionType;

	double right = -1;
	double down = 1;
	double speed = 2;

	int leftHP = 3;
	int rightHP = 3;

	final PhongMaterial material1 = new PhongMaterial();
	final PhongMaterial material2 = new PhongMaterial();

	public GameController(GameFrame gameFrame, boolean isServer) {
		this.gameFrame = gameFrame;
		this.isServer = isServer;
	}

	public void run() throws InterruptedException {

		if (isServer) {
			if (!ifStarted) {
				HPManagement();
				ifStarted = true;
			}

			ifCollision = false;

			if (gameFrame.ball.getTranslateY() > 585) {
				ifCollision = true;
				collisionType = "down";
			} else if (gameFrame.ball.getTranslateY() < 15) {
				ifCollision = true;
				collisionType = "up";
			} else if (gameFrame.ball.getTranslateX() < 45
					&& gameFrame.leftPaddle.getTranslateY() - gameFrame.ball.getTranslateY() >= -100
					&& gameFrame.leftPaddle.getTranslateY() - gameFrame.ball.getTranslateY() <= 100) {
				ifCollision = true;
				collisionType = "left paddle";
			} else if (gameFrame.ball.getTranslateX() < 45) {
				ifCollision = true;
				collisionType = "left point";
			} else if (gameFrame.ball.getTranslateX() > 750
					&& gameFrame.rightPaddle.getTranslateY() - gameFrame.ball.getTranslateY() >= -100
					&& gameFrame.rightPaddle.getTranslateY() - gameFrame.ball.getTranslateY() <= 100) {
				ifCollision = true;
				collisionType = "right paddle";
			} else if (gameFrame.ball.getTranslateX() > 750) {
				ifCollision = true;
				collisionType = "right point";
			}

			if (!ifCollision) {
				// moveBall(down * speed, right * speed);
			} else if (collisionType == "down") {
				down = -1;
				speed = speed + 0.1;
			} else if (collisionType == "up") {
				down = 1;
				speed = speed + 0.1;
			} else if (collisionType == "left paddle") {
				right = 1;
				speed = speed + 0.1;
			} else if (collisionType == "left point") {
				gameFrame.ball.setTranslateX(400);
				gameFrame.ball.setTranslateY(400);
				speed = 2;
				leftHP = leftHP - 1;

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						gameFrame.clientResultText.setText(Integer.toString(++gameFrame.clientResult));
						gameFrame.uDPConn.sentFrame.opponentYposition = gameFrame.clientResult;
					}
				});

				HPManagement();
			} else if (collisionType == "right paddle") {
				right = -1;
				speed = speed + 0.1;
			} else if (collisionType == "right point") {
				gameFrame.ball.setTranslateX(400);
				gameFrame.ball.setTranslateY(400);

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						gameFrame.serverResultText.setText(Integer.toString(++gameFrame.serverResult));
						gameFrame.uDPConn.sentFrame.opponentXposition = gameFrame.serverResult;
					}
				});

				speed = 2;
				rightHP = rightHP - 1;
				HPManagement();
			}
		}

		if (isServer) {
			moveBall(down * speed, right * speed);
			gameFrame.uDPConn.sentFrame.ballXposition = (int) gameFrame.ball.getTranslateX();
			gameFrame.uDPConn.sentFrame.ballYposition = (int) gameFrame.ball.getTranslateY();
		}

		if (!isServer) {
			gameFrame.ball.setTranslateX(800 - gameFrame.uDPConn.receivedFrame.ballXposition);
			gameFrame.ball.setTranslateY(gameFrame.uDPConn.receivedFrame.ballYposition);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					gameFrame.clientResultText
							.setText(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentXposition));
				}
			});

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					gameFrame.serverResultText
							.setText(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentYposition));
				}
			});

		}

		if (!isServer) {
			material1.setDiffuseColor(Color.RED);
			gameFrame.leftPaddle.setMaterial(material1);
			gameFrame.rightPaddle.setMaterial(material1);
		}

	}

	public void moveBall(double down, double right) {
		gameFrame.ball.setTranslateX(gameFrame.ball.getTranslateX() + right);
		gameFrame.ball.setTranslateY(gameFrame.ball.getTranslateY() + down);

	}

	public void HPManagement() {
		if (leftHP == 3) {
			material1.setDiffuseColor(Color.GREEN);
			System.out.println(("Coloring"));
			gameFrame.leftPaddle.setMaterial(material1);
		} else if (leftHP == 2) {
			material1.setDiffuseColor(Color.YELLOW);
			System.out.println(("Coloring"));
			gameFrame.leftPaddle.setMaterial(material1);
		} else if (leftHP == 1) {
			material1.setDiffuseColor(Color.RED);
			System.out.println(("Coloring"));
			gameFrame.leftPaddle.setMaterial(material1);
		}

		if (rightHP == 3) {
			material2.setDiffuseColor(Color.GREEN);
			System.out.println(("Coloring"));
			gameFrame.rightPaddle.setMaterial(material2);
		} else if (rightHP == 2) {
			material2.setDiffuseColor(Color.YELLOW);
			System.out.println(("Coloring"));
			gameFrame.rightPaddle.setMaterial(material2);
		} else if (rightHP == 1) {
			material2.setDiffuseColor(Color.RED);
			System.out.println(("Coloring"));
			gameFrame.rightPaddle.setMaterial(material2);
		}
	}

}
