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
	private ComboBox modeComboBox;

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

	// boolean isServer

	UDPConn uDPConn;

	public ModeController() {
		///
	}

	// inicjalizacja elementow FXML
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

	// obsluga handlera przycisku zacznij nowa gre
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

		//
		try {
			gameFrame = new GameFrame(uDPConn);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// obsluga handlera combo do wyboru trybu
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

	public String getLabelLocalMyXPosition() {
		return labelLocalMyXPosition.getText();
	}

	public void setLabelLocalMyXPosition(String labelLocalMyXPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalMyXPosition.setText(labelLocalMyXPositionStr);
			}
		});

	}

	public String getLabelLocalMyYPosition() {
		return labelLocalMyYPosition.getText();
	}

	public void setLabelLocalMyYPosition(String labelLocalMyYPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalMyYPosition.setText(labelLocalMyYPositionStr);
			}
		});

	}

	public String getLabelLocalOpponentXPos() {
		return labelLocalOpponentXPos.getText();
	}

	public void setLabelLocalOpponentXPos(String labelLocalOpponentXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalOpponentXPos.setText(labelLocalOpponentXPosStr);
			}
		});

	}

	public String getLabelLocalOpponentYPos() {
		return labelLocalOpponentYPos.getText();
	}

	public void setLabelLocalOpponentYPos(String labelLocalOpponentYPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalOpponentYPos.setText(labelLocalOpponentYPosStr);
			}
		});

	}

	public String getLabelLocalBallXPos() {
		return labelLocalBallXPos.getText();
	}

	public void setLabelLocalBallXPos(String labelLocalBallXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalBallXPos.setText(labelLocalBallXPosStr);
			}
		});

	}

	public String getLabelLocalBallYPos() {
		return labelLocalBallYPos.getText();
	}

	public void setLabelLocalBallYPos(String labelLocalBallYPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelLocalBallYPos.setText(labelLocalBallYPosStr);
			}
		});

	}

	public String getLabelRemoteMyXPosition() {
		return labelRemoteMyXPosition.getText();
	}

	public void setLabelRemoteMyXPosition(String labelRemoteMyXPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				labelRemoteMyXPosition.setText(labelRemoteMyXPositionStr);
			}
		});

	}

	public String getLabelRemoteMyYPosition() {
		return labelRemoteMyYPosition.getText();
	}

	public void setLabelRemoteMyYPosition(String labelRemoteMyYPositionStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteMyYPosition.setText(labelRemoteMyYPositionStr);

			}
		});

	}

	public String getLabelRemoteOpponentXPos() {
		return labelRemoteOpponentXPos.getText();
	}

	public void setLabelRemoteOpponentXPos(String labelRemoteOpponentXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteOpponentXPos.setText(labelRemoteOpponentXPosStr);
			}
		});

	}

	public String getLabelRemoteOpponentYPos() {
		return labelRemoteOpponentYPos.getText();
	}

	public void setLabelRemoteOpponentYPos(String labelRemoteOpponentYPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteOpponentYPos.setText(labelRemoteOpponentYPosStr);
			}
		});

	}

	public String getLabelRemoteBallXPos() {
		return labelRemoteBallXPos.getText();
	}

	public void setLabelRemoteBallXPos(String labelRemoteBallXPosStr) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteBallXPos.setText(labelRemoteBallXPosStr);
			}
		});
	}

	public String getLabelRemoteBallYPos() {
		return labelRemoteBallYPos.getText();
	}

	public void setLabelRemoteBallYPos(String labelRemoteBallYPosStr) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				labelRemoteBallYPos.setText(labelRemoteBallYPosStr);
			}
		});

	}

	// zamknij aplikacje
	//////////////////////////////////////////////////////////////////////////////////////////

	@FXML
	public void exitApplication(ActionEvent event) {
		Platform.exit();
	}

	// timer do cyklicznej aktualizacji stanu labelow
	///////////////////////////////////////////////////////////////////////////////////////////
	class DisplayTimer extends TimerTask {
		public void run() {
			if (gameFrame != null) {
				setLabelLocalMyYPosition(Integer.toString(gameFrame.getPaddleLocation()));
				setLabelLocalMyXPosition(Integer.toString(gameFrame.uDPConn.sentFrame.myXPosition));							
				setLabelRemoteMyYPosition(Integer.toString(gameFrame.uDPConn.receivedFrame.myYPosition));
				setLabelRemoteMyXPosition(Integer.toString(gameFrame.uDPConn.receivedFrame.myXPosition));						
				setLabelLocalBallXPos(Integer.toString(gameFrame.uDPConn.sentFrame.ballXposition));
				setLabelLocalBallYPos(Integer.toString(gameFrame.uDPConn.sentFrame.ballYposition));
				setLabelRemoteBallXPos(Integer.toString(gameFrame.uDPConn.receivedFrame.ballXposition));
				setLabelRemoteBallYPos(Integer.toString(gameFrame.uDPConn.receivedFrame.ballYposition));
				setLabelLocalOpponentXPos(Integer.toString(gameFrame.uDPConn.sentFrame.opponentXposition));
				setLabelLocalOpponentYPos(Integer.toString(gameFrame.uDPConn.sentFrame.opponentYposition));
				setLabelRemoteOpponentXPos(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentXposition));
				setLabelRemoteOpponentYPos(Integer.toString(gameFrame.uDPConn.receivedFrame.opponentXposition));
			}
		}
	}
}
