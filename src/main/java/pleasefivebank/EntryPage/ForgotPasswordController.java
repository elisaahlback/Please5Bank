package pleasefivebank.EntryPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ForgotPasswordController {

    @FXML
    private TextField Email;

    @FXML
    private TextField Username;

    @FXML
    void NewPasswordPressed(ActionEvent event){
        String email = Email.getText();
        String userName = Username.getText();


    }

}
