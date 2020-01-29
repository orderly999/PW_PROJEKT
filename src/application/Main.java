package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		//  @PrzemysławJarek
			///////////////////////////////////////////////////////////
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("PONG.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			SampleController sampleController = new SampleController();
			sampleController.setPrimaryStage(primaryStage);

			// zamknij aplikacje @KamilKruk
			/////////////////////////////////////////////
			primaryStage.setOnCloseRequest(event -> {
				Platform.exit();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	// zamknij aplikacje @KamilKruk
	//////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void stop() {
		Platform.exit();
	}

}
