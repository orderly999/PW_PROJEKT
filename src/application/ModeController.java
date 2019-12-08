package application;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ModeController {

	@FXML
	private Button startButton;

	@FXML
	private TextField addressIPTextField;

	@FXML
	private TextField oponnentAddressIPTextField;

	@FXML
	private ComboBox modeComboBox;

	@FXML
	private TextArea textArea;

	public ModeController() {
		///

	}

	// inicjalizacja elementow FXML
	///////////////////////////////////////////////////////
	@FXML
	public void initialize() {
		modeComboBox.getItems().addAll("Serwer", "Klient");

	}

	// obsluga handlera przycisku zacznij nowa gre
	//////////////////////////////////////////////////
	@FXML
	public void onPortTextFieldActionClick(ActionEvent event) throws UnknownHostException {
		InetAddress ia = InetAddress.getByName("127.0.0.1");
		InetAddress ib = InetAddress.getByName("127.0.0.1");
		UDPConn uDPConn = new UDPConn(false, ia, ib, this.textArea);
		GameFrame gameFrame = new GameFrame();
	}

	// obsluga handlera combo do wyboru trybu
	//////////////////////////////////////////////////
	@FXML
	public void onModeComboBoxActionClick(ActionEvent event) {

	}
}
