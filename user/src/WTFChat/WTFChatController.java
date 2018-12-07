package WTFChat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Sign_SignUP.Sign.SignController;
import WTF_Network.TCPConnection;
import WTF_Network.TCPConnectionListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WTFChatController implements TCPConnectionListener {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtField;

    @FXML
    private Button btnSend;


    private final int PORT = 8189;
    private final String IP_ADDR = "192.168.0.100";

    private TCPConnection connection;
    private String UserName2;

    {
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSend(ActionEvent actionEvent) {
        this.
        SignController signController = new SignController();
        String message = txtField.getText();
        if (message.equals("")){
            txtField.setPromptText("Message cannot be empty!");
        }
        txtField.setText(null);
        connection.sendString(message);
    }

    public synchronized void printMessage(String message){
        SignController signController = new SignController();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtArea.appendText(signController.UserName() +  ":  " + message + "\n"); // TODO: 02.12.2018 UserName + message
            }
        });
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMessage("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMessage(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMessage("Connection close!");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMessage("Connection exeption: " + e);
    }
}
