package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Attendant;
import model.Bags;
import model.GetBags;
import model.ParkingLot;

public class AttendantLoggedInController implements Initializable {

	private Bags bags = GetBags.getBags();
	private ParkingLot parkingLot = bags.getParkingLot();
	private String username = AttendantLogInController.userName;
	private Attendant attendant = bags.searchAttendant(username);
	public static String plateN;
	public static int ticketNumber;
	@FXML
	private TextArea attendantTextArea;

	@FXML
	private TextArea ticketTextArea;

	@FXML
	private TextArea returnTicketTextArea;
	@FXML
	private MenuItem returnTicketMenu;
	@FXML
	private MenuItem ticketMenu;
	@FXML
	private MenuItem profileMenu;
	@FXML
	private Pane pane;

	@FXML
	private TextField plateNumberField;

	@FXML
	private TextField stateF;
	@FXML

	private ListView<String> listView;

	@FXML
	void logOut(ActionEvent event) throws IOException {
		File file = new File("Bags.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(bags);
		dos.close();
		fos.close();
		changeScene("/view/HomePageView.fxml", event);
	}

	@FXML
	void showProfile(ActionEvent event) throws IOException {
		changeScene("/view/AttendantProfileView.fxml", event);

	}

	@FXML
	void showReturnTicket(ActionEvent event) throws IOException {
		changeScene("/view/ShowReturnTicketView.fxml", event);
	}

	@FXML
	void showTicket(ActionEvent event) throws IOException {
		changeScene("/view/ShowTicketView.fxml", event);
	}

	@FXML
	void findParking(ActionEvent event) throws IOException {
		plateN = (plateNumberField.getText());
		if(attendant.getPlateNumber().compareTo(plateN) == 0) {
		changeScene("/view/FindParkingView.fxml", event);
		}
	}

	public void changeScene(String sceneName, ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(sceneName));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		returnTicketTextArea.appendText("Haven't return yet");
	}

}
