
package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EntryPageController {

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    void PressedLoginButton(ActionEvent event) {
        String userName = LoginUsername.getText();
        String password = LoginPassword.getText();
        System.out.println("username: "+userName+" password: "+password);

    }

}


