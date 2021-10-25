package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Admin;
import model.Bags;
import model.GetBags;
import model.ParkingLot;
import model.SpotNAndDistance;
import model.Ticket;

public class FindParkingController implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bags bags = GetBags.getBags();
	private String userName = AttendantLogInController.userName;
	private ParkingLot parkingLot = bags.getParkingLot();
	public static SpotNAndDistance sNd2;
	private ObservableList<SpotNAndDistance> spotAndDistanceList = FXCollections
			.observableArrayList(parkingLot.getSpotMap().values());

	@FXML
	private Label label;
	@FXML
	private TableView<SpotNAndDistance> tableView;

	@FXML
	private TableColumn<SpotNAndDistance, Integer> spotColumn;

	@FXML
	private TableColumn<SpotNAndDistance, Integer> distanceColumn;

	@FXML
	void parkTheCar(ActionEvent event) throws IOException {
		if (tableView.getSelectionModel().selectedItemProperty() == null) {
			label.setText("please select the spot number first");
		} else {
			if (bags.getAttendantsHistory().get(userName).getTicket().getParkStatus().compareTo("not parked") == 0) {
				parkingLot.setSpotsTaken(1);
				SpotNAndDistance sNd = tableView.getSelectionModel().getSelectedItem();
				sNd2 = tableView.getSelectionModel().getSelectedItem();
				parkingLot.getSpotMap().remove(sNd.getDistance());
				bags.getAttendantsHistory().get(userName).getTicket().setSpotNumber(sNd.getSpotNumber());
				bags.getAttendantsHistory().get(userName).getTicket().setParkStatus("parked");
				bags.getAttendantsHistory().get(userName).getReturnTicket().setStatus("not paid");
				String ticketNumber = ((int) (Math.random() * 1000))+"";
				bags.getAttendantsHistory().get(userName).getTicket().setId(ticketNumber);
				File file = new File("Bags.dat");
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream dos = new ObjectOutputStream(fos);
				dos.writeObject(bags);
				dos.close();
				fos.close();
				String aggFileName = "Tickets.txt";
				FileWriter fstream = new FileWriter(aggFileName);
				BufferedWriter out = new BufferedWriter(fstream);
				for (Entry<String, Ticket> entry : bags.getTickets().entrySet()) {
					out.write(entry.getKey() + "\t" + entry.getValue() + "\n");
					out.flush(); // Flush the buffer and write all changes to the disk
				}
				out.close();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/AttendantLoggedInView.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root, 600, 600);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.show();
			} else {
				label.setText("You have already parked the car");
			}
				

		}

	}


	@FXML
	void backToLoggedIn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AttendantLoggedInView.fxml"));
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
		int n = 0;
		Collections.sort(spotAndDistanceList);
		spotColumn.setCellValueFactory(new PropertyValueFactory<SpotNAndDistance, Integer>("spotNumber"));
		distanceColumn.setCellValueFactory(new PropertyValueFactory<SpotNAndDistance, Integer>("distance"));
		tableView.setItems(spotAndDistanceList);
	}

}
