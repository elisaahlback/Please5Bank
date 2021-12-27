package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pleasefivebank.EntryPage.EntryPageController;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class MakeTransferController {
    private static String tempReceiver;
    private static String tempReceiverIBAN;
    private static long tempQuantity;
    private static String tempConcept;

    @FXML
    private TextField IBAN;

    @FXML
    private TextField Name;

    @FXML
    private Button NameLabel;

    @FXML
    private Button SendMoney;

    @FXML
    private TextField amount;

    @FXML
    private TextArea message;
    //juan
    @FXML
    void LogOut(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    //juan
    @FXML
    void ToCards(ActionEvent event) {
        try {
            Main.showCardsPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    //juan
    @FXML
    void ToDetails(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AccountDetails.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AccountDetailsController accountDetailsController = fxmlLoader.getController();
            User currentUser = EntryPageController.user;
            if(!currentUser.equals(null)) {
                accountDetailsController.setInformation(EntryPageController.user);
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
    void ToLoans(ActionEvent event) {
        try {
            Main.showLoansPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void ToNotifications(ActionEvent event) {

    }

    //juan
    @FXML
    void ToTransactions(ActionEvent event) {
        try {
            Main.showTransactionsPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    //juan
    @FXML
    void SendMoney(ActionEvent event) {
        String receiver = Name.getText();
        String receiverIban = IBAN.getText();
        long quantity = Long.parseLong(amount.getText());
        String concept = message.getText();
        if((Mongo.isAccount(receiver, receiverIban)) && (quantity>0) && (!concept.isEmpty())){
            tempReceiver = receiver;
            tempReceiverIBAN = receiverIban;
            tempQuantity = quantity;
            tempConcept = concept;
            Transaction purchase = new Transaction(tempReceiver, receiverIban, tempQuantity, tempConcept);
            String date = Mongo.formatTime();// we register the time
            purchase.setDate(date);
            //validate balance
            //update balance
            purchase.setStatus("approved");
            Mongo.coll3.insertOne(purchase.save());
        }

    }

    //juan
    public void setName(String name){
        NameLabel.setText(name);
    }



}
