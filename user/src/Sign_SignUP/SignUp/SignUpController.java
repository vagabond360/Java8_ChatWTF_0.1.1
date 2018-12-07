package Sign_SignUP.SignUp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Sign_SignUP.DBConnect.DatabaseHandler;
import Sign_SignUP.DBConnect.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField signUp_password;

    @FXML
    private TextField signUp_login;

    @FXML
    private Button signUp_signUpButton;

    @FXML
    private TextField signUp_secondName;

    @FXML
    private TextField signUp_firstName;

    @FXML
    private RadioButton sigmUp_maleGender;

    @FXML
    private RadioButton signUp_femaleGender;

    @FXML
    void initialize() {
        signUp_signUpButton.setOnAction(event -> {
            signUpNewUser();
            openNewScene("/Sign_SignUp/Sign/sign.fxml");
        });
    }

    private void signUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String firstName = signUp_firstName.getText();
        String secondName = signUp_secondName.getText();
        String UserName = signUp_login.getText();
        String password = signUp_password.getText();
        String gender = "";
        if (sigmUp_maleGender.isSelected()){
            gender = "Male";
        }else if (signUp_femaleGender.isSelected()){
            gender = "Female";
        }

        User user = new User(firstName,secondName,UserName,password,gender);

        databaseHandler.signUpUser(user);
    }

    private void openNewScene(String window){
        signUp_signUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
