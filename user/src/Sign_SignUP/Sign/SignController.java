package Sign_SignUP.Sign;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sign_SignUP.DBConnect.DatabaseHandler;
import Sign_SignUP.DBConnect.User;
import Sign_SignUP.Sign.Animation.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignController {

    @FXML
    private PasswordField sign_passField;

    @FXML
    private TextField sign_loginField;

    @FXML
    private Button signBtn;

    @FXML
    private Button signUpBtn;

    public String Userrr;


    public void signBtnClick(ActionEvent actionEvent){
        signBtn.setOnAction(event -> {
            String loginTxt = sign_loginField.getText().trim();
            String loginPass = sign_passField.getText().trim();

            if (!loginTxt.equals("") && !loginPass.equals("")) {
                try {
                    loginUser(loginTxt, loginPass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void signUpBtnClick(javafx.event.ActionEvent actionEvent){
        openNewScene("/Sign_SignUP/SignUp/signUp.fxml", "Text");
    }

    public void loginUser(String loginTxt, String loginPass) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginTxt);
        user.setPassword(loginPass);
        ResultSet resultSet = databaseHandler.getUser(user);

        int counter = 0;

        while (resultSet.next()) {
            counter++;
        }

        if (counter >= 1) {
            setUserrr(sign_loginField.getText());
            openNewScene("/WTFChat/wtfChat.fxml", loginTxt);
        } else {
            Shake userLoginAnim = new Shake(sign_loginField);
            Shake userPassAnim = new Shake(sign_passField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

    public void setUserrr(String userrr) {
        Userrr = userrr;
    }

    public String UserName(){
        return Userrr;
    }


    private void openNewScene(String window, String login) {
        signUpBtn.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        MyStage stage = new MyStage(login);
        stage.setScene(new Scene(parent));
        stage.show();
    }


}

