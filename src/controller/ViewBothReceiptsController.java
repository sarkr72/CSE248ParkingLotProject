package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Attendant;
import model.Bags;
import model.GetBags;

public class ViewBothReceiptsController implements Initializable {
	@FXML
	private Pane pane;
	private Bags bags = GetBags.getBags();
	private Attendant attendant;

	@FXML
	private TextArea ticketTextarea;

	@FXML
	private TextArea invoiceTextarea;

	@FXML
	void goBackToAdminLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLoggedInView.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		attendant = bags.getAttendantsHistory().get(AdminLoggedInController.userName);
		if (attendant.getTicket() != null) {
			if(attendant.getTicket().getParkStatus().compareTo("parked") == 0) {
			ticketTextarea.appendText("\t\tArrival Ticket" + "# " + attendant.getTicket().getId() + "\n\n Date: "
					+ attendant.getTicket().getDate() + "\n\n Arrival Time: "
					+ (attendant.getTicket().getArrivalTime() + "").substring(0, 8) + "\n\nSpot Number: "
					+ attendant.getTicket().getSpotNumber() + "\n\n First name: " + attendant.getFirstName()
					+ "\t Last name: " + attendant.getLastName() + "\n\n Id: " + attendant.getId()
					+ "\n\n Plate number: " + attendant.getPlateNumber() + "\t State: " + attendant.getState());
			}else {
				ticketTextarea.appendText("you haven't parked the car yet");
			}
		}
		if (attendant.getReturnTicket() != null) {
			if(attendant.getReturnTicket().getStatus().contains("not paid")) {
				invoiceTextarea.appendText("You haven't returned the ticket yet");
		}else {
			invoiceTextarea.appendText("\t\tInvoice" + "\t# " + attendant.getTicket().getId() + "\n\n Date: "
					+ attendant.getReturnTicket().getDate() + "\n\n Departure Time:"
					+ (attendant.getReturnTicket().getDepartureTime() + "").substring(0, 8) + "\n\nSpot Number: "
					+ attendant.getTicket().getSpotNumber() + "\n\n Fee: $" + attendant.getReturnTicket().getFee()
					+ "\n\n First name: " + attendant.getFirstName() + "\t Last name: " + attendant.getLastName()
					+ "\n\n Id: " + attendant.getId() + "\n\n Plate number: " + attendant.getPlateNumber()
					+ "\t State: " + attendant.getState());
			
		}}
	}
}
