package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.Bags;
import model.GetBags;

public class AdminLogInController {

	private Bags bags = GetBags.getBags();
	private Admin admin;
	public static String username;
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
    private Button submitBtn;
	
    @FXML
    void logIn(ActionEvent event) throws IOException {
    	username = usernameField.getText();
		password = passwordField.getText();
		username = username.toLowerCase();
		Admin admin = bags.searchAdmin(username);
		
		int check = 0;
		if (check == 0) {
			if (admin != null && admin.getPassword().compareTo(password) == 0) {
				check = 1;
				changeSceneToLoggedIn(event);
			} else {
				passwordLabel.setText("Password or Username did not match");
			}
		} else {
			logIn(event);
		}
    }
    @FXML
    void createAccount(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/view/CreateAdminView.fxml"));
		Scene scene = new Scene(root, 600, 600);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
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

	private void changeSceneToLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLoggedInView.fxml"));
		Scene scene = new Scene(root, 600, 600);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}
}
