package application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ModeController {

	GameFrame gameFrame;

	@FXML
	private Button startButton;

	@FXML
	private TextField localAddressIPTextField;

	@FXML
	private TextField remoteAddressIPTextField;

	@FXML
	private ComboBox<String> modeComboBox;

	@FXML
	private TextArea textArea;

	@FXML
	private Label labelLocalMyXPosition;

	@FXML
	private Label labelLocalMyYPosition;

	@FXML
	private Label labelLocalOpponentXPos;

	@FXML
	private Label labelLocalOpponentYPos;

	@FXML
	private Label labelLocalBallXPos;

	@FXML
	private Label labelLocalBallYPos;

	@FXML
	private Label labelRemoteMyXPosition;

	@FXML
	private Label labelRemoteMyYPosition;

	@FXML
	private Label labelRemoteOpponentXPos;

	@FXML
	private Label labelRemoteOpponentYPos;

	@FXML
	private Label labelRemoteBallXPos;

	@FXML
	private Label labelRemoteBallYPos;

	UDPConn uDPConn;

	public ModeController() {

	}

	// inicjalizacja elementow FXML @KamilKruk
	///////////////////////////////////////////////////////
	@FXML
	public void initialize() {
		modeComboBox.getItems().addAll("Serwer", "Klient");
		setLabelLocalMyXPosition("no comm");
		setLabelLocalMyYPosition("no comm");
		setLabelLocalOpponentXPos("no comm");
		setLabelLocalOpponentYPos("no comm");
		setLabelLocalBallXPos("no comm");
		setLabelLocalBallYPos("no comm");
		setLabelRemoteMyXPosition("no comm");
		setLabelRemoteMyYPosition("no comm");
		setLabelRemoteOpponentXPos("no comm");
		setLabelRemoteOpponentYPos("no comm");
		setLabelRemoteBallXPos("no comm");
		setLabelRemoteBallYPos("no comm");
		Timer timer = new Timer();
		TimerTask task = new DisplayTimer();
		timer.schedule(task, 2000, 100);

	}

	// obsluga handlera przycisku zacznij nowa gre @KamilKruk
	//////////////////////////////////////////////////
	@FXML
	public void onPortTextFieldActionClick(ActionEvent event) throws UnknownHostException {
		InetAddress localAddressIP = InetAddress.getByName(localAddressIPTextField.getText());
		InetAddress remoteAddressIP = InetAddress.getByName(remoteAddressIPTextField.getText());

		if (modeComboBox.getValue().toString().contains("Klient")) {
			uDPConn = new UDPConn(false, localAddressIP, remoteAddressIP, this.textArea);
			this.localAddressIPTextField.setText("192.168.40.95");
			this.remoteAddressIPTextField.setText("192.168.40.94");
		} else {
			uDPConn = new UDPConn(true, localAddressIP, remoteAddressIP, this.textArea);
			this.localAddressIPTextField.setText("192.168.40.94");
			this.remoteAddressIPTextField.setText("192.168.40.95");
		}

		try {
			gameFrame = new GameFrame(uDPConn);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// obsluga handlera combo do wyboru trybu @KamilKruk
	//////////////////////////////////////////////////
	@FXML
	public void onModeComboBoxActionClick(ActionEvent event) {

		if (modeComboBox.getValue().toString().contains("Klient")) {
			this.localAddressIPTextField.setText("192.168.40.95");
			this.remoteAddressIPTextField.setText("192.168.40.94");
		} else {
			this.localAddressIPTextField.setText("192.168.40.94");
			this.remoteAddressIPTextField.setText("192.168.40.95");
		}

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelLocalMyXPosition() {
		return labelLocalMyXPosition.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelLocalMyXPosition(String labelLocalMyXPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalMyXPosition.setText(labelLocalMyXPositionStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelLocalMyYPosition() {
		return labelLocalMyYPosition.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelLocalMyYPosition(String labelLocalMyYPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalMyYPosition.setText(labelLocalMyYPositionStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelLocalOpponentXPos() {
		return labelLocalOpponentXPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelLocalOpponentXPos(String labelLocalOpponentXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalOpponentXPos.setText(labelLocalOpponentXPosStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelLocalOpponentYPos() {
		return labelLocalOpponentYPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelLocalOpponentYPos(String labelLocalOpponentYPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalOpponentYPos.setText(labelLocalOpponentYPosStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelLocalBallXPos() {
		return labelLocalBallXPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelLocalBallXPos(String labelLocalBallXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalBallXPos.setText(labelLocalBallXPosStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelLocalBallYPos() {
		return labelLocalBallYPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelLocalBallYPos(String labelLocalBallYPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalBallYPos.setText(labelLocalBallYPosStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelRemoteMyXPosition() {
		return labelRemoteMyXPosition.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelRemoteMyXPosition(String labelRemoteMyXPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				labelRemoteMyXPosition.setText(labelRemoteMyXPositionStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelRemoteMyYPosition() {
		return labelRemoteMyYPosition.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelRemoteMyYPosition(String labelRemoteMyYPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteMyYPosition.setText(labelRemoteMyYPositionStr);

			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelRemoteOpponentXPos() {
		return labelRemoteOpponentXPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelRemoteOpponentXPos(String labelRemoteOpponentXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteOpponentXPos.setText(labelRemoteOpponentXPosStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelRemoteOpponentYPos() {
		return labelRemoteOpponentYPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelRemoteOpponentYPos(String labelRemoteOpponentYPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteOpponentYPos.setText(labelRemoteOpponentYPosStr);
			}
		});

	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelRemoteBallXPos() {
		return labelRemoteBallXPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelRemoteBallXPos(String labelRemoteBallXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteBallXPos.setText(labelRemoteBallXPosStr);
			}
		});
	}

	// getter dla pobrania tekstu @KamilKruk
	//////////////////////////////////////////////////
	public String getLabelRemoteBallYPos() {
		return labelRemoteBallYPos.getText();
	}

	// setter dla ustawienia tekstu @KamilKruk
	//////////////////////////////////////////////////
	public void setLabelRemoteBallYPos(String labelRemoteBallYPosStr) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteBallYPos.setText(labelRemoteBallYPosStr);
			}
		});

	}

	// zamknij aplikacje @KamilKruk
	//////////////////////////////////////////////////////////////////////////////////////////
	@FXML
	public void exitApplication(ActionEvent event) {
		Platform.exit();
	}

	// timer do cyklicznej aktualizacji stanu labelow @PrzemysławJarek
	///////////////////////////////////////////////////////////////////////////////////////////
	class DisplayTimer extends TimerTask {
		public void run() {
			if (gameFrame != null) {
				setLabelLocalMyYPosition(Integer.toString(gameFrame.getPaddleLocation()));
				setLabelLocalMyXPosition(Integer.toString(gameFrame.uDPConn.sentFrame.myPowerUpId));
				setLabelRemoteMyYPosition(Integer.toString(gameFrame.uDPConn.receivedFrame.myYPosition));
				setLabelRemoteMyXPosition(Integer.toString(gameFrame.uDPConn.receivedFrame.myPowerUpId));
				setLabelLocalBallXPos(Integer.toString(gameFrame.uDPConn.sentFrame.ballXposition));
				setLabelLocalBallYPos(Integer.toString(gameFrame.uDPConn.sentFrame.ballYposition));
				setLabelRemoteBallXPos(Integer.toString(gameFrame.uDPConn.receivedFrame.ballXposition));
				setLabelRemoteBallYPos(Integer.toString(gameFrame.uDPConn.receivedFrame.ballYposition));
				setLabelLocalOpponentXPos(Integer.toString(gameFrame.uDPConn.sentFrame.opponentXresult));
				setLabelLocalOpponentYPos(Integer.toString(gameFrame.uDPConn.sentFrame.opponentYresult));
				setLabelRemoteOpponentXPos(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentXresult));
				setLabelRemoteOpponentYPos(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentXresult));
			}
		}
	}
}
