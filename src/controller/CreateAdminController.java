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
import javafx.stage.Stage;
import model.Admin;
import model.Bags;
import model.GetBags;
import model.ReturnTicket;
import model.Ticket;

public class CreateAdminController implements Initializable{

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String id = "";
	private String plateNumber;
	private int ticketNumber;
	private String state;
	private int n = 0;
	private String ticketStatus;
	private int spotNumber;
	private Bags bag;

	@FXML
	private TextField passwordField;

	@FXML
	private TextField usernameField;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private Label userNameLabel;

	@FXML
	private TextField plateNumberF;

	@FXML
	private ComboBox<String> stateBox;

	@FXML
	void createAdminAccount(ActionEvent event) throws IOException {
		firstName = firstNameField.getText();
		lastName = lastNameField.getText();
		username = usernameField.getText();
		password = passwordField.getText();
		username = username.toLowerCase();
		firstName = firstNameField.getText();
		lastName = lastNameField.getText();
		ticketNumber = (int) (Math.random() * 1000);
		id = generateId();
		username = usernameField.getText();
		password = passwordField.getText();
		plateNumber = plateNumberF.getText();
		state = stateBox.getValue();

		String time = (LocalTime.now() + "").substring(0, 8);
		String date = (LocalDate.now() + "").substring(0, 10);
		ticketStatus = "Not Paid";
		spotNumber = (int) Math.random() * 1000;
		if (!bag.getAdmins().containsKey(username)) {
			Ticket ticket = new Ticket(plateNumber, date, time, state, firstName, lastName, ticketNumber + "",
					ticketStatus, spotNumber, "not parked");
			bag.addTicket(ticket);

			ReturnTicket returnTicket = null;
			String departureT = 0 + "";
			Admin admin = new Admin(firstName, lastName, username, password, id, plateNumber, state, ticket, time,
					departureT, 0.0, "not paid", returnTicket);

			ReturnTicket returnTicket1 = new ReturnTicket(admin.getPlateNumber(), admin.getTicket().getDate(),
					admin.getTicket().getArrivalTime() + "", admin.getState(), admin.getFirstName(),
					admin.getLastName(), admin.getTicket().getId(), departureT, 0.0, "not paid");
			admin.setReturnTicket(returnTicket1);

			bag.addAdmin(admin);
			bag.addReturnTicket(returnTicket1);
			bag.getParkingLot().setSpotsTaken(1);
			changeSceneToLoggedIn(event);
		} else {
			userNameLabel.setText("userName already exits");
		}
	}

	@FXML
	void goToHomePage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageView.fxml"));
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

	private void changeSceneToLoggedIn(ActionEvent event) throws IOException {
		File file = new File("Bags.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(bag);
		dos.close();
		fos.close();
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLogInView.fxml"));
		Scene scene = new Scene(root, 600, 600);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 bag = GetBags.getBags();
		ObservableList<String> list = FXCollections.observableArrayList("AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT",
				"DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI",
				"MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA",
				"PR", "RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY");
		stateBox.setItems(list);
	}

}
