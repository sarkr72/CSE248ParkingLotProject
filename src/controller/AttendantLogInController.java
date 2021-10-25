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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Attendant;
import model.Bags;
import model.GetBags;

public class AttendantLogInController implements Initializable{

	private Bags bags;
	public static String userName;
	private String password;
	
	 @FXML
	    private TextField usernameField;

	    @FXML
	    private TextField passwordField;

	    @FXML
	    private Label usernameLabel;

	    @FXML
	    private Label passwordLabel;

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


	    @FXML
	    void createAcc(ActionEvent event) throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource("/view/CreateAttendantView.fxml"));
			Scene scene = new Scene(root, 600, 600);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setWidth(600);
			window.setHeight(600);
			window.setScene(scene);
			window.show();
	    }
	    
	    @FXML
	    void logIn(ActionEvent event) throws IOException {
	    	userName = usernameField.getText();
	    	password = passwordField.getText();
	    	
	    	Attendant attendant = bags.searchAttendant(userName);
	    	int check = 0;
			if (check == 0) {
				if (attendant != null && attendant.getPassword().compareTo(password) == 0) {
					check = 1;
					changeSceneToLoggedIn(event);
				} else {
					passwordLabel.setText("Password or Username did not match");
				}
			} else {
				logIn(event);
			}
	    }

		private void changeSceneToLoggedIn(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AttendantLoggedInView.fxml"));
			Scene scene = new Scene(root, 600, 600);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setWidth(600);
			window.setHeight(600);
			window.setScene(scene);
			window.show();
		}


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			bags = GetBags.getBags();
		}

}
