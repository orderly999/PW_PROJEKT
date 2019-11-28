package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class ModeController {

	@FXML
	private Button startButton;

	@FXML
	private TextField addressIPTextField;

	@FXML
	private TextField portTextField;

	@FXML
	private ComboBox modeComboBox;

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
	public void onPortTextFieldActionClick(ActionEvent event) {

	}
	
	// obsluga handlera combo do wyboru trybu
	//////////////////////////////////////////////////
	public void onModeComboBoxActionClick(ActionEvent event) {

	}

}
