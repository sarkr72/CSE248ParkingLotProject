package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Admin;
import model.Bags;
import model.GetBags;

public class ShowReturnTicketForAdminController implements Serializable, Initializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bags bags = GetBags.getBags();
	private String username = AdminLogInController.username;
	private Admin admin;
	@FXML
	private TextArea textArea;

	@FXML
	void returnAndPay(ActionEvent event) {
		textArea.clear();
		if(bags.getAdmins().containsKey(username)) {
			admin = bags.getAdmins().get(username);
		if (admin.getTicket().getParkStatus().compareTo("parked") == 0 && admin.getReturnTicket().getStatus().contains("not paid")) {
			double timeDiff = 0;
			String time = (LocalTime.now() + "").substring(0, 8);
			String date = (LocalDate.now() + "");
				if(Double.parseDouble(time.substring(6, 8)) > Double.parseDouble(admin.getTicket().getArrivalTime().substring(6, 8))) {
				timeDiff = Double.parseDouble(time.substring(6, 8))
						- Double.parseDouble(admin.getTicket().getArrivalTime().substring(6, 8)); // got seconds only for project.
			} else {
				timeDiff = Double.parseDouble(admin.getTicket().getArrivalTime().substring(6, 8))
						- Double.parseDouble(time.substring(6, 8)); // got seconds only for project.
			}
			double fee = timeDiff * .5;
			admin.getReturnTicket().setDepartureTime(time);
			admin.getReturnTicket().setFee(fee);
			admin.getReturnTicket().setStatus("paid");
			
			bags.addReturnTicket(admin.getReturnTicket());
			bags.getAdmins().remove(admin.getUserName());

			admin.setDepartureTime(time);
			admin.setFee(fee);
			admin.setStatus("paid");
			admin.getTicket().setParkStatus("not parked");
			textArea.appendText("\t\tInvoice" + "\t# " + admin.getTicket().getId() + "\n\n Date: " + date
					+ "\n\n Departure Time:" + (admin.getReturnTicket().getDepartureTime()+"").subSequence(0, 8) + "\n\nSpot Number: " + admin.getTicket().getSpotNumber() + "\n\n Fee: $"
					+ admin.getReturnTicket().getFee() + "\n\n First name: " + admin.getFirstName()
					+ "\t Last name: " + admin.getLastName() + "\n\n Id: " + admin.getId() + "\n\n Plate number: "
					+ admin.getPlateNumber() + "\t State: " + admin.getState());
		} else if(admin.getReturnTicket().getStatus().compareTo("paid") == 0){
			textArea.appendText("You have already paid");
			textArea.setStyle("-fx-text-fill: red;");
		}else {
			textArea.appendText("please park the car first");
		}
	}}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AdminLoggedInView.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textArea.appendText("		\nPaid or haven't got the ticket");
	}
}
