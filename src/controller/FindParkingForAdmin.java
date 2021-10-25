package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Collections;
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

public class FindParkingForAdmin implements Initializable{
	private Bags bags = GetBags.getBags();
	private ParkingLot parkingLot = bags.getParkingLot();
	private String adminUserN = AdminLogInController.username;
	private ObservableList<SpotNAndDistance> spotAndDistanceList = FXCollections
			.observableArrayList(parkingLot.getSpotMap().values());
	private static SpotNAndDistance sNd2;
	
	@FXML
	private TableView<SpotNAndDistance> tableView;

	@FXML
	private TableColumn<SpotNAndDistance, Integer> spotNCol;

	@FXML
	private TableColumn<SpotNAndDistance, Integer> distanceCol;

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

	@FXML
	void submit(ActionEvent event) throws IOException {
		if (adminUserN != null) {
			if (bags.getAdmins().containsKey(adminUserN)) {
				Admin admin = bags.getAdmins().get(adminUserN);
				if (admin.getTicket().getParkStatus().compareTo("not parked") == 0) {
					parkingLot.setSpotsTaken(1);
					SpotNAndDistance sNd = tableView.getSelectionModel().getSelectedItem();
					sNd2 = tableView.getSelectionModel().getSelectedItem();
					parkingLot.getSpotMap().remove(sNd.getDistance());
					admin.getTicket().setSpotNumber(sNd.getSpotNumber());
					admin.getTicket().setParkStatus("parked");
					admin.getReturnTicket().setStatus("not paid");
					File file = new File("Bags.dat");
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream dos = new ObjectOutputStream(fos);
					dos.writeObject(bags);
					dos.close();
					fos.close();
					String aggFileName = "AdminTickets.txt";
					FileWriter fstream = new FileWriter(aggFileName);
					BufferedWriter out = new BufferedWriter(fstream);
					for (Entry<String, Ticket> entry : bags.getTickets().entrySet()) {
						out.write(entry.getKey() + "\t" + entry.getValue() + "\n");
						out.flush(); // Flush the buffer and write all changes to the disk
					}
					out.close();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/AdminLoggedInView.fxml"));
					Parent root = loader.load();
					Scene scene = new Scene(root, 600, 600);
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(scene);
					window.show();
				} 
	}
}}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int n = 0;
		Collections.sort(spotAndDistanceList);
		spotNCol.setCellValueFactory(new PropertyValueFactory<SpotNAndDistance, Integer>("spotNumber"));
		distanceCol.setCellValueFactory(new PropertyValueFactory<SpotNAndDistance, Integer>("distance"));
		tableView.setItems(spotAndDistanceList);
	}}
