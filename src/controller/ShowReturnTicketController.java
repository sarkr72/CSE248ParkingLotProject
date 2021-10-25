package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Attendant;
import model.Bags;
import model.GetBags;
import model.ParkingLot;
import model.Ticket;
import model.SpotNAndDistance;

public class ShowReturnTicketController implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bags bags = GetBags.getBags();
	private String username = AttendantLogInController.userName;
	private Attendant attendant = bags.searchAttendant(username);
	private String ticketNumber = attendant.getTicket().getId();
	private ParkingLot parkingLot = bags.getParkingLot();
	private SpotNAndDistance sNd2 = FindParkingController.sNd2;
	
	@FXML
	private Pane pane;

	@FXML
	private TextArea textArea;
	@FXML
	private TextField ticketNField;

	@FXML
	private Label ticketNLabel;

	@FXML
	void goToLoggedIn(ActionEvent event) throws IOException {
		File file = new File("Bags.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(bags);
		dos.close();
		fos.close();
		String aggFileName = "ReturnTickets.txt";
		FileWriter fstream = new FileWriter(aggFileName);
		BufferedWriter out = new BufferedWriter(fstream);
		for (Entry<String, Ticket> entry : bags.getTickets().entrySet()) {
			out.write(entry.getKey() + "\t" + entry.getValue() + "\n");
			out.flush(); // Flush the buffer and write all changes to the disk
		}
		out.close();
		Parent root = FXMLLoader.load(getClass().getResource("/view/AttendantLoggedInView.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void returnAndPay(ActionEvent event) {
		textArea.clear();
		String str = ticketNField.getText();
		if (str == null) {
					ticketNLabel.setText("Enter the ticker number");
				}else {
			if (ticketNumber.compareTo(ticketNField.getText()) == 0) {
				if (attendant.getTicket().getParkStatus().compareTo("parked") == 0
						&& attendant.getReturnTicket().getStatus().contains("not paid")) {
					ticketNLabel.setText("");
					double timeDiff = 0;
					String time = (LocalTime.now() + "").substring(0, 8);
					String date = (LocalDate.now() + "");
					if (Double.parseDouble(time.substring(6, 8)) > Double
							.parseDouble(attendant.getTicket().getArrivalTime().substring(6, 8))) {
						timeDiff = Double.parseDouble(time.substring(6, 8))
								- Double.parseDouble(attendant.getTicket().getArrivalTime().substring(6, 8)); // got
																												// seconds
																												// only
																												// for
																												// project.
					} else {
						timeDiff = Double.parseDouble(attendant.getTicket().getArrivalTime().substring(6, 8))
								- Double.parseDouble(time.substring(6, 8)); // got seconds only for project.
					}
					double fee = timeDiff * .5;
					attendant.getReturnTicket().setDepartureTime(time);
					attendant.getReturnTicket().setFee(fee);
					attendant.getReturnTicket().setStatus("paid");

					bags.addReturnTicket(attendant.getReturnTicket());
					bags.getAttendants().remove(attendant.getUserName());
					
					
					attendant.setDepartureTime(time);
					attendant.setFee(fee);
					attendant.setStatus("paid");
					attendant.getTicket().setParkStatus("not parked");
					parkingLot.addSpot(sNd2);
					textArea.appendText("\t\tInvoice" + "\t# " + attendant.getTicket().getId() + "\n\n Date: " + date
							+ "\n\n Departure Time:"
							+ (attendant.getReturnTicket().getDepartureTime() + "").subSequence(0, 8)
							+ "\n\nSpot Number: " + attendant.getTicket().getSpotNumber() + "\n\n Fee: $"
							+ attendant.getReturnTicket().getFee() + "\n\n First name: " + attendant.getFirstName()
							+ "\t Last name: " + attendant.getLastName() + "\n\n Id: " + attendant.getId()
							+ "\n\n Plate number: " + attendant.getPlateNumber() + "\t State: " + attendant.getState());
				} else if (attendant.getReturnTicket().getStatus().compareTo("paid") == 0) {
					textArea.appendText("You have already paid");
					textArea.setStyle("-fx-text-fill: red;");
				} else {
					textArea.appendText("please park the car first");
				}
			}else {
				ticketNLabel.setText("Invalid ticket number");
			}
		
	}}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textArea.appendText("		\nPaid or haven't got the ticket");

	}
}
