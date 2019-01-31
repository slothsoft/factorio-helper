package de.slothsoft.factorio.helper.ui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FactorioHelper extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"), Messages.RESOURCE_BUNDLE);
		final Parent root = loader.load();
		final Scene scene = new Scene(root, 900, 650);

		stage.setTitle(Messages.getString("FactorioHelper"));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}