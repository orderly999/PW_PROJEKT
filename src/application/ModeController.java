package application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import application.UDPConn.ConnectionTimer;
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
		UDPConn uDPConn;

		if (modeComboBox.getValue().toString().contains("Klient"))
			uDPConn = new UDPConn(false, localAddressIP, remoteAddressIP, this.textArea);
		else
			uDPConn = new UDPConn(true, localAddressIP, remoteAddressIP, this.textArea);

		//
		gameFrame = new GameFrame();
	}

	// obsluga handlera combo do wyboru trybu
	//////////////////////////////////////////////////
	@FXML
	public void onModeComboBoxActionClick(ActionEvent event) {

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

			}
		});

		this.labelRemoteOpponentYPos.setText(labelRemoteOpponentYPosStr);
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

	// timer do cyklicznej aktualizacji stanu labelow
	///////////////////////////////////////////////////////////////////////////////////////////
	class DisplayTimer extends TimerTask {
		public void run() {
			if (gameFrame != null) {
				setLabelLocalMyYPosition(Integer.toString(gameFrame.getPaddleLocation()));

			}
		}
	}
}
