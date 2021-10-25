package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Attendant;
import model.Bags;
import model.GetBags;
import model.ReturnTicket;

public class AdminLoggedInController implements Initializable {

	private Bags bags = GetBags.getBags();
	private ObservableList<Attendant> list = FXCollections.observableArrayList(bags.getAttendantsHistory().values());
//	private ObservableList<Object> list2 = FXCollections.observableArrayList(bags.getTickets());
//			.filter(s -> s.getReturnTime().substring(0, 7).compareTo("OverDue") == 0).collect(Collectors.toList()););
	private Attendant attendant;
	public static String userName;

	@FXML
	private Pane pane;
	@FXML
	private TableView<Attendant> tableView;
	@FXML
	private TableColumn<Attendant, String> attendantC;

	@FXML
	private TableColumn<Attendant, String> firstNameC;

	@FXML
	private TableColumn<Attendant, String> lastNameC;

	@FXML
	private TableColumn<Attendant, String> arrivalTimeC;

	@FXML
	private TableColumn<Attendant, String> departureTimeC;

	@FXML
	private TableColumn<Attendant, Double> feeC;

	@FXML
	private TableColumn<Attendant, String> statusC;
	@FXML
	private Button deleteBtn;
	@FXML
	private TextField searchField;

	@FXML
	private Label searchLabel;

	@FXML
	private Label deleteLabel;

	@FXML
	private Label viewReceiptLabel;

	@FXML
	void searchAttendant(ActionEvent event) {
		ObservableList<Attendant> list = FXCollections.observableArrayList(bags.getAttendantsHistory().values());
		FilteredList<Attendant> filteredBag = new FilteredList<>(list, e1 -> true);
		String[] checkWord = new String[1];
		searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredBag.setPredicate((Predicate<? super Attendant>) attendant -> {
				String lowerCase = newValue.toLowerCase();
				String str = "";
				str += lowerCase;
				if (attendant.getTicket().getId().toLowerCase().contains(lowerCase)) { // search by ticket Number
					attendant.setFirstName(attendant.getFirstName());
					attendant.setLastName(attendant.getLastName());
					return true;
				} else if (attendant.getFirstName().toLowerCase().contains(lowerCase)) { // search by first name
					attendant.setFirstName(attendant.getFirstName());
					attendant.setLastName(attendant.getLastName());
					return true;
				} else if (attendant.getLastName().toLowerCase().contains(lowerCase)) { // search by last name
					attendant.setFirstName(attendant.getFirstName());
					attendant.setLastName(attendant.getLastName());
					return true;
				}
				checkWord[0] = str;
				return false;
			});
		});
		
		SortedList<Attendant> sorted = new SortedList<>(filteredBag);
		sorted.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sorted);
		}

	@FXML
	void viewReceipts(ActionEvent event) throws IOException {
		attendant = tableView.getSelectionModel().getSelectedItem();
		if (attendant != null) {
			userName = attendant.getUserName();
			changeScene("/view/ViewBothReceiptsView.fxml", event);
		}
	}

	@FXML
	void deleteAttendant(ActionEvent event) {
		Attendant attendant = tableView.getSelectionModel().getSelectedItem();
		ObservableList<Attendant> list = FXCollections.observableArrayList();
		if (attendant != null) {
			bags.getAttendantsHistory().remove(attendant.getUserName());
			firstNameC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("firstName"));
			lastNameC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("lastName"));
			arrivalTimeC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("arrivalTime"));
			departureTimeC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("departureTime"));
			statusC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("status"));
			feeC.setCellValueFactory(new PropertyValueFactory<Attendant, Double>("fee"));
			list.addAll(bags.getAttendantsHistory().values());
			tableView.refresh();
			tableView.setItems(list);
		}
	}

	@FXML
	void createAttendant(ActionEvent event) throws IOException {
		changeScene("/view/CreateAttendantView.fxml", event);
	}

	@FXML
	void homePage(ActionEvent event) throws IOException {
		changeScene("/view/HomePageView.fxml", event);
	}

	@FXML
	void showAllAttendants(ActionEvent event) {
		deleteBtn.isVisible();

	}

	public static String getUset() {
		return userName;
	}

    @FXML
    void parkMyCar(ActionEvent event) throws IOException {
    	changeScene("/view/FindParkingForAdmin.fxml", event);
    }

    @FXML
    void returnMyCar(ActionEvent event) throws IOException {
    	changeScene("/view/ShowReturnTickerForAdminView.fxml", event);
    }
	public void changeScene(String str, ActionEvent event) throws IOException {
		File file = new File("Bags.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(bags);
		dos.close();
		fos.close();
		Parent root = FXMLLoader.load(getClass().getResource(str));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firstNameC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("firstName"));
		lastNameC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("lastName"));
		arrivalTimeC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("arrivalTime"));
		departureTimeC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("departureTime"));
		statusC.setCellValueFactory(new PropertyValueFactory<Attendant, String>("status"));
		feeC.setCellValueFactory(new PropertyValueFactory<Attendant, Double>("fee"));
		tableView.setItems(list);
	}
}