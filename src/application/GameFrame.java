package application;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameFrame {

	public GameFrame() {
		super();
		StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, 500, 500);
        Stage newWindow = new Stage();
        newWindow.setTitle("Gra");
        newWindow.setScene(secondScene);
        newWindow.show();
	}

}
