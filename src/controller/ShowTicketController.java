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
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Attendant;
import model.Bags;
import model.GetBags;

public class ShowTicketController implements Initializable {
	private Bags bags = GetBags.getBags();
	private String username = AttendantLogInController.userName;
	private Attendant attendant = bags.searchAttendant(username);
	@FXML
	private TextArea textArea;

	@FXML
	private Pane pane;

	@FXML
	void goToLoggedIn(ActionEvent event) throws IOException {
		File file = new File("Bags.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(bags);
		dos.close();
		fos.close();
		Parent root = FXMLLoader.load(getClass().getResource("/view/AttendantLoggedInView.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(attendant.getTicket().getParkStatus().compareTo("parked") == 0) {
		textArea.appendText("\t\tArrival Ticket" + "# " + attendant.getTicket().getId() + "\n\n Date: "
				+ attendant.getTicket().getDate() + "\n\n Arrival Time: "
				+ (attendant.getTicket().getArrivalTime() + "").subSequence(0, 8) + "\n\nSpot Number: " + attendant.getTicket().getSpotNumber() + "\n\n First name: "
				+ attendant.getFirstName() + "\t Last name: " + attendant.getLastName() + "\n\n Id: " + attendant.getId()
				+ "\n\n Plate number: " + attendant.getPlateNumber() + "\t State: " + attendant.getState());
		}else {
			textArea.appendText("You haven't parked the car yet");
		}
	}

}
