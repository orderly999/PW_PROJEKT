package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {
	
	Stage primaryStage;
	@FXML 
	private MenuItem menuItemCreators;
	@FXML 
	private MenuItem newGameItemCreators;
	
	// wyswietlenie okna z informacjami o tworcach
	///////////////////////////////////////////////////////////
	public void onMenuItemCreatorsActionClick(ActionEvent event)
	{
		 Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         VBox dialogVbox = new VBox(20); 
         try {
			dialogVbox.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("Tworcy.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
         Scene dialogScene = new Scene(dialogVbox);
         dialog.setScene(dialogScene);
         dialog.show();
	}
	
	
	// wyswietlenie okna z polem opcji polaczenia 
	//////////////////////////////////////////////////
	public void onNewGameActionClick(ActionEvent event)
	{
		 Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         VBox dialogVbox = new VBox(20); 
         try {
			dialogVbox.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("Mode.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
         Scene dialogScene = new Scene(dialogVbox);
         dialog.setScene(dialogScene);
         dialog.show();
       
	}
	
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
