package application;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

/**
 * @author PrzemysławJarek
 * 
 */
public class GameController {

	GameFrame gameFrame;
	boolean isServer;
	boolean ifStarted = false;
	boolean ifCollision;
	boolean ifPowerUp = false;
	int randomized = 3;
	String collisionType;
	double right = -1;
	double down = 1;
	double speed = 2;
	final PhongMaterial material1 = new PhongMaterial();
	final PhongMaterial material2 = new PhongMaterial();

	/**
	 * @author PrzemysławJarek
	 * @param gameFrame
	 * @param isServer
	 */
	public GameController(GameFrame gameFrame, boolean isServer) {
		this.gameFrame = gameFrame;
		this.isServer = isServer;
	}

	/**
	 * callback do funkcji cyklicznie generujacej polozenie elementow
	 * @author PrzemysławJarek
	 * @throws InterruptedException
	 */
	public void run() throws InterruptedException {

		if (!isServer) {
			gameFrame.ball.setTranslateX(800 - gameFrame.uDPConn.receivedFrame.ballXposition);
			gameFrame.ball.setTranslateY(gameFrame.uDPConn.receivedFrame.ballYposition);
			randomized = gameFrame.uDPConn.receivedFrame.myPowerUpId;
			powerUpChange();

		}
		if (isServer)
			gameFrame.uDPConn.sentFrame.myPowerUpId = randomized;
		{
			if (!ifStarted) {
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

				boolean ifPowerUpArea = ifPowerUpArea();

				if (isServer) {
					if (randomized != 3 && ifPowerUpArea == true) {
						powerUpOn();
					}
				}
				if (isServer) {
					if (speed < 1) {
						speed = 1;
					}
				}
			} else if (collisionType == "down") {
				if (isServer) {
					down = -1;
					speed = speed + 0.1;
					powerUpChange();
				}

			} else if (collisionType == "up") {
				if (isServer) {
					down = 1;
					speed = speed + 0.1;
					powerUpChange();
				}

			} else if (collisionType == "left paddle") {
				if (isServer) {
					right = 1;
					speed = speed + 0.1;
				}
			} else if (collisionType == "left point") {
				if (isServer) {
					gameFrame.ball.setTranslateX(400);
					gameFrame.ball.setTranslateY(400);
					speed = 2;
					powerUpClear();
				}

				if (isServer) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							gameFrame.clientResultText.setText(Integer.toString(++gameFrame.clientResult));
							gameFrame.uDPConn.sentFrame.opponentYresult = gameFrame.clientResult;

							if (gameFrame.clientResult > 8)
								gameFrame.clientResult = 0;
						}
					});
				}
			} else if (collisionType == "right paddle") {
				if (isServer) {
					right = -1;
					speed = speed + 0.1;
				}
			} else if (collisionType == "right point") {
				if (isServer) {
					gameFrame.ball.setTranslateX(400);
					gameFrame.ball.setTranslateY(400);
				}
				if (isServer) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							gameFrame.serverResultText.setText(Integer.toString(++gameFrame.serverResult));
							gameFrame.uDPConn.sentFrame.opponentXresult = gameFrame.serverResult;
							if (gameFrame.serverResult > 8)
								gameFrame.serverResult = 0;
						}
					});
				}

				if (isServer)
					powerUpClear();
				speed = 2;

			}

			if (isServer) {
				moveBall(down * speed, right * speed);
				gameFrame.uDPConn.sentFrame.ballXposition = (int) gameFrame.ball.getTranslateX();
				gameFrame.uDPConn.sentFrame.ballYposition = (int) gameFrame.ball.getTranslateY();
			}

			if (!isServer) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						gameFrame.clientResultText
								.setText(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentXresult));
					}
				});

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						gameFrame.serverResultText
								.setText(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentYresult));
					}
				});
			}
		}

		material2.setDiffuseColor(Color.BISQUE);
		gameFrame.leftPaddle.setMaterial(material2);
		gameFrame.rightPaddle.setMaterial(material2);
	}

	/**
	 * wlaczenie powerupu
	 * @author PrzemysławJarek
	 */
	private void powerUpOn() {
		// Zielony - speed *2
		if (randomized == 1) {
			speed = speed * 2;
		} else if (randomized == 2) {

			// Zolty - odwrocenie kierunku lotu
			right = right * -1;
		} else if (randomized == 0) {
			// Niebieski - speed * 0,5
			speed = speed * 0.5;
		}

		powerUpClear();
	}

	/**
	 * wylaczenie powerupu
	 * @author PrzemysławJarek
	 */
	private void powerUpClear() {
		randomized = 3;
		material1.setDiffuseColor(Color.WHITE);
		gameFrame.powerUpBox.setMaterial(material1);
	}

	/**
	 * sprawdzenie czy pilka jest w zasiegu powerupu
	 * @author PrzemysławJarek
	 * @return
	 */
	private boolean ifPowerUpArea() {

		if (gameFrame.ball.getTranslateX() > 350 && gameFrame.ball.getTranslateX() < 550
				&& gameFrame.ball.getTranslateY() > 250 && gameFrame.ball.getTranslateY() < 450) {
			return true;
		}
		return false;
	}

	/**
	 * zmiana typu powerupu 
	 * @author PrzemysławJarek
	 */
	private void powerUpChange() {
		if (isServer) {
			int sum = (int) gameFrame.leftPaddle.getTranslateY() + (int) gameFrame.rightPaddle.getTranslateY()
					+ (int) gameFrame.ball.getTranslateY();
			randomized = sum % 3;
		}
		if (randomized == 1) {
			material1.setDiffuseColor(Color.GREEN);
		} else if (randomized == 2) {
			material1.setDiffuseColor(Color.YELLOW);
		} else {
			material1.setDiffuseColor(Color.BLUE);
		}
		gameFrame.powerUpBox.setMaterial(material1);

		if (!isServer && randomized == 3) {
			material1.setDiffuseColor(Color.WHITE);
			gameFrame.powerUpBox.setMaterial(material1);
		}

	}

	/**
	 * funkcja do przemieszczania pilki
	 * @author PrzemysławJarek
	 * @param down
	 * @param right
	 */
	public void moveBall(double down, double right) {
		gameFrame.ball.setTranslateX(gameFrame.ball.getTranslateX() + right);
		gameFrame.ball.setTranslateY(gameFrame.ball.getTranslateY() + down);

	}

}
