package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Attendant;
import model.Bags;
import model.GetBags;
import model.ReturnTicket;
import model.Ticket;

public class CreateAttendantController implements Initializable {

	private Bags bags;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String id = "";
	private String plateNumber;
	private int ticketNumber;
	private String state;
	private int n = 0;
	private String ticketStatus;
	private int spotNumber;

	@FXML
	private Pane pane;
	@FXML
	private TextField firstNameField;

	@FXML
	private TextField plateNumberField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField idField;

	@FXML
	private TextField stateField;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;
	@FXML
	private Label userNameLabel;
	@FXML
	private ComboBox<String> statesBox;

	@FXML
	void createAttendantAccount(ActionEvent event) throws IOException {
		firstName = firstNameField.getText();
		lastName = lastNameField.getText();
		ticketNumber = (int) (Math.random() * 1000);
		id = generateId();
		userName = usernameField.getText();
		password = passwordField.getText();
		plateNumber = plateNumberField.getText();
		state = statesBox.getValue();
		
		String time = (LocalTime.now() + "").substring(0, 8);
		String date = (LocalDate.now() + "").substring(0, 10);
		ticketStatus = "Not Paid";
		spotNumber = (int) Math.random() * 1000;
		if (!bags.getAttendants().containsKey(userName)) {
			Ticket ticket = new Ticket(plateNumber, date, time, state, firstName, lastName, ticketNumber + "",
					ticketStatus, spotNumber, "not parked");
			bags.addTicket(ticket);

			ReturnTicket returnTicket = null;
			String departureT = 0 + "";
			Attendant attendant = new Attendant(firstName, lastName, id, plateNumber, state, ticket, userName, password,
					time, departureT, 0.0, "not paid", returnTicket);

			ReturnTicket returnTicket1 = new ReturnTicket(attendant.getPlateNumber(), attendant.getTicket().getDate(),
					attendant.getTicket().getArrivalTime() + "", attendant.getState(), attendant.getFirstName(),
					attendant.getLastName(), attendant.getTicket().getId(), departureT, 0.0, "not paid");
			attendant.setReturnTicket(returnTicket1);

			bags.addAttendant(attendant);
			bags.addReturnTicket(returnTicket1);
			bags.addAttendantHistory(attendant);
			bags.getParkingLot().setSpotsTaken(1);
			changeSceneToLogIn(event);
		} else {
			userNameLabel.setText("userName already exits");
		}
	}

	private void changeSceneToLogIn(ActionEvent event) throws IOException {
		File file = new File("Bags.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(bags);
		dos.close();
		fos.close();
		Parent root = FXMLLoader.load(getClass().getResource("/view/AttendantLogInView.fxml"));
		Scene scene = new Scene(root, 600, 600);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToAttendantLogInPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AttendantLogInView.fxml"));
		Scene scene = new Scene(root, 600, 600);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	public String generateId() {
		while (n < 9) {
			id += getNewId();
			n++;
		}
		return id;
	}

	public String getNewId() {
		return this.id = ((int) (Math.random() * 10) + "");
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bags = GetBags.getBags();
		ObservableList<String> list = FXCollections.observableArrayList("AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT",
				"DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI",
				"MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA",
				"PR", "RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY");
		statesBox.setItems(list);
	}

	@FXML
	void stateBox(ActionEvent event) {
		

	}
}
