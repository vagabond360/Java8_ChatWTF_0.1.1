package WTFChat;

import Sign_SignUP.Sign.SignController;
import WTF_Network.TCPConnection;
import WTF_Network.TCPConnectionListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WTFChatController implements TCPConnectionListener, Initializable {

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

    private final int PORT = 8188;
    private final String IP_ADDR = "192.168.0.100";

    private SignController signController = new SignController();
    public String userName = signController.getUser();
    private TCPConnection connection;

    {
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnSend() {
        String message = txtField.getText();
        if (message.equals("")){
            txtField.setPromptText("Message cannot be empty!");
        }
        txtField.setText(null);
        connection.sendString(signController.getUser(), message);
    }

    public synchronized void printMessage(String message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtArea.appendText(message + "\n");
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
