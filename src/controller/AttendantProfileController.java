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

public class AttendantProfileController implements Initializable{

	private Bags bags = GetBags.getBags();
	private String username = AttendantLogInController.userName;
	private Attendant attendant = bags.searchAttendant(username);
	
	@FXML
    private TextArea profileTextArea;
	@FXML
    private Pane pane;
	
	 @FXML
	    void goBackToLoggedIn(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("/view/AttendantLoggedInView.fxml"));
			Scene scene = new Scene(root, 600, 600);
			Stage window = (Stage)pane.getScene().getWindow();
			window.setWidth(600);
			window.setHeight(600);
			window.setScene(scene);
			window.show();
	    }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		profileTextArea.appendText("First name: " + attendant.getFirstName() + "\t Last name: "
				+ attendant.getLastName() + "\n\nid: " + attendant.getId() + "\n\nUsername: " + attendant.getUserName()
				+ "\t Password: " + attendant.getPassword() + "\n\nPlate number: " + attendant.getPlateNumber()
				+ "\t State: " + attendant.getState());
	}
	
	
	
	
}
