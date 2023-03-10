package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.bson.Document;
import pleasefivebank.EntryPage.DataValidation;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static pleasefivebank.EntryPage.EntryPageController.user;

public class EditDetailsController {
    //this controller only includes a method to set up the user data
    //methods to go from page to page
    //and a method to change and update the user information

    String university;

    @FXML
    private ComboBox<?> UniversityOption;

    @FXML
    private Label addressLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label postalLabel;

    @FXML
    private Text IBANlabel;

    @FXML
    private Text accountFrozenLabel;

    @FXML
    private Text accountnumberLabel;

    @FXML
    private TextField cityTextfield;

    @FXML
    private TextField postalcodeTextfield;

    @FXML
    private TextField streetTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private Text personalidLabel;

    @FXML
    private TextField phonenumberTextfield;

    @FXML
    private Label usernameLabel;

    //juan
    @FXML
    void SaveChanges(ActionEvent event) {
        //get user input and validate with regex
        String phoneNumber = phonenumberTextfield.getText();
        boolean phoneVerification = DataValidation.validateField(phoneNumber, phoneLabel, "\\d{10}", "Enter a valid phone number");


        String email = emailTextfield.getText();
        boolean emailVerification = DataValidation.validateField(email, emailLabel, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", "Wrong email format");


        String street = streetTextfield.getText();
        boolean addressVerification = DataValidation.validateField(street, addressLabel, "^\\w+?\\s\\d+$", "Enter a valid address");

        String postal = postalcodeTextfield.getText();
        boolean postalVerification = DataValidation.validateField(postal, postalLabel, "\\d{5}", "Enter a valid postal code");

        String city = cityTextfield.getText();
        boolean cityVerification = DataValidation.validateField(city, cityLabel, "([\\p{L}]+\s)*[\\p{L}]+", "Enter a valid city name");
        String uni = university;

        if (!phoneNumber.equals(user.getPhoneNumber())){
            boolean phoneExists = Mongo.existsInDatabase(phoneNumber, "phone number", phoneLabel, "Phone is already registered");
        }
        if (!email.equals(user.getEmail())){
            boolean checkIfEmailExists = Mongo.existsInDatabase(email, "email", emailLabel, "Email already exists");
        }

        //if data is valid and doesn't use other users unique data make changes effective
        if(phoneVerification && emailVerification && addressVerification &&
        postalVerification && cityVerification){
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setCity(city);
            user.setPostalCode(postal);
            user.setAddress(street);
            user.setUniversity(university);

            //update db by calling the mongo db lines
            Mongo.updateInformation("phone number", user.getPhoneNumber(), user.getPersonnummer());
            Mongo.updateInformation("email", user.getEmail(), user.getPersonnummer());
            Mongo.updateInformation("city", user.getCity(), user.getPersonnummer());
            Mongo.updateInformation("postal code", user.getPostalCode(), user.getPersonnummer());
            Mongo.updateInformation("address", user.getAddress(), user.getPersonnummer());
            Mongo.updateInformation("university", user.getUniversity(), user.getPersonnummer());
            //update json with lottis method
            Mongo.updateJson();
        }
        user.setCity(cityTextfield.getText());


    }
    @FXML
    void Select(ActionEvent event) throws IOException {
        String selection = (String) UniversityOption.getSelectionModel().getSelectedItem();
        university = selection;
    }

    @FXML
    void toHomePage(ActionEvent event) {
        try {
            Main.showLoginPage(user.getFirstName()+ " " + user.getLastName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void setData(String fullName){
        firstnameLabel.setText(user.getFirstName());
        lastnameLabel.setText(user.getLastName());
        IBANlabel.setText(user.getAccountIBAN());
        accountFrozenLabel.setText(user.getFrozen());
        accountnumberLabel.setText(user.getAccountNr());
        cityTextfield.setText(user.getCity());
        postalcodeTextfield.setText(user.getPostalCode());
        streetTextfield.setText(user.getAddress());
        emailTextfield.setText(user.getEmail());
        personalidLabel.setText(user.getPersonnummer());
        phonenumberTextfield.setText(user.getPhoneNumber());
        usernameLabel.setText(user.getUsername1());
    }

}

