/*Reference: Roberts, B. (2015). JavaFX Java GUI Tutorial - 5 - Creating Alert Boxes. YouTube.
Available at: https://www.youtube.com/watch?v=SpL3EToqaXA [Accessed 20 April 2020]*/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public static void displayPopUp(String title, String message) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(450);

		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("Close window");
		closeButton.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add("stylesheet.css");
		window.setScene(scene);
		window.showAndWait();

	}// end of method displayPopUp

}// end of AlertBox
