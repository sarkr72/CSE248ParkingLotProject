package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GetBags;

public class Main extends Application{

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		GetBags bags = new GetBags();
		bags.start1();
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageView.fxml"));
		Scene scene = new Scene(root, 600, 600);
		Stage primaryStage = new Stage();
		primaryStage.setWidth(600);
		primaryStage.setHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}