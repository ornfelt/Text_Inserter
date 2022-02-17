package controllers;

import file.IMessageSaver;
import file.SaveMessageToFile;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import file.*;

/**
 * FXML Controller class
 *
 * @author Jonas
 */
public class MainWindowController implements Initializable {

    @FXML
    private CheckBox cbRubberyLang;
    @FXML
    private CheckBox cbBase64;
    @FXML
    private CheckBox cbSuperImp;
    @FXML
    private TextField txtMessage;

    private IMessageSaver messageSaver;

    boolean rubberyChecked, base64Checked, superImpChecked;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messageSaver = new SaveMessageToFile();
    }

    @FXML
    private void handleInsertButton() {
        String message = txtMessage.getText();
        if (message.length() == 0) {
            showAlert("Please insert a message first.", "Fail");
            return;
        }

        try {
            TextMessage textMessage = new RegularMessage(message);
            System.out.println("regMessage är: " + textMessage.getMessage());

            //Encodes text
            if (rubberyChecked) {
                textMessage = new RubberLang(textMessage);
            }
            if (base64Checked) {
                textMessage = new BaseLang(textMessage);
            }
            if (superImpChecked) {
                textMessage = new SuperLang(textMessage);
            }

            message = textMessage.getMessage();
            String fileSaved = messageSaver.saveMessage(message);
            showAlert("Message saved in file: " + fileSaved, "Success");
        } catch (Exception ex) {
            showAlert(ex.getMessage(), "Fail");
        } finally {
            txtMessage.setText("");
        }
    }

    @FXML
    private void handleCbChange() {
        rubberyChecked = cbRubberyLang.isSelected();
        base64Checked = cbBase64.isSelected();
        superImpChecked = cbSuperImp.isSelected();

//        System.out.println("Rubbery selected: " + rubberyChecked);
//        System.out.println("Base64 selected: " + base64Checked);
//        System.out.println("Super implementation selected: " + superImpChecked);
    }

    private void showAlert(String message, String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);

        alert.showAndWait();
    }
}
