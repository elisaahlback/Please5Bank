package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import pleasefivebank.Main;
import pleasefivebank.Objects.User;
import pleasefivebank.Utilities.Utilities;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;
import static pleasefivebank.Main.showTransactionsPage;

public class CardsController {
    //this controller only includes a method to set up the user data
    //and methods to go from page to page

    @FXML
    private Text AccountNumber;

    @FXML
    private Text BIC;

    @FXML
    private Text Balance;

    @FXML
    private Text CardName;

    @FXML
    private Text CardNumber;

    @FXML
    private Text ExpirationDate;

    @FXML
    private Text IBAN;

    @FXML
    private Button NameDisplay;

    //Juan and Ergi
    @FXML
    void ToDetails(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AccountDetails.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AccountDetailsController accountDetailsController = fxmlLoader.getController();
            User currentUser = user;
            if(!currentUser.equals(null)) {
                accountDetailsController.setInformation(user);
                mainWindow.setScene(scene);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //juan
    @FXML
    void ToHome(ActionEvent event) {
        try {
            Main.showLoginPage(user.getFirstName()+ " " + user.getLastName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan
    @FXML
    void ToNotifications(ActionEvent event) {
        try {
            Main.showNotificationsPage();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    //juan
    @FXML
    void ToLoans(ActionEvent event) {
        try {
            Main.showLoansPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    //juan
    @FXML
    void ToTransactions(ActionEvent event) {
        try {
            showTransactionsPage(user.getFirstName() + " " + user.getLastName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private BorderPane borderpane;

    @FXML
    private StackPane rootPane;

    //Ergi
    @FXML
    void FreezeCard(ActionEvent event) {
        Utilities.popup("Your card is now frozen",borderpane,rootPane);
    }

    //elisa
    @FXML
    void ContactUsButton(MouseEvent event) {
        try {
            Main.showContactUs(user);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan
    @FXML
    public void Logout(ActionEvent event) {
        //save the activity
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //juan
    public void setName(){
        NameDisplay.setText(user.getFirstName()+ " " + user.getLastName());
        CardName.setText(user.getFirstName()+ " " + user.getLastName());
        CardNumber.setText(user.getCardNumber());
        ExpirationDate.setText(user.getExpirationDate());
        float balance = Float.parseFloat(user.getBalance());
        balance -= balance % 0.001;
        Balance.setText("+" + balance +" SEK");
        AccountNumber.setText(user.getAccountNr());
        IBAN.setText(user.getAccountIBAN());
        BIC.setText("PFBSEGBGXXX");
    }

}




