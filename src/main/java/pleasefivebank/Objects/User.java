package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;
import pleasefivebank.Mongo;
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class User {
    //variables storing basic user info
    private String firstName;
    private final String birthdate;
    private String phoneNumber;
    private final String personnummer;
    private String email;
    private String address;
    private String city;
    private String postalCode;
    private String middleName;
    private String lastName;
    private String university;
    private Document doc;

    //account info for the user
    protected String cardNumber;
    protected String expirationDate;
    protected String balance;
    protected int rewardPoints;
    protected String frozen;
    protected final String accountNr;
    protected final String accountIBAN;

    //constructor that also makes the document of the user
    public User(String cardNumber,String expirationDate ,String name, String middleName, String lastName, String address, String city, String postalCode,
                String birthDate, String phoneNumber, String personNummer, String email, String university, String accountNr, String accountIBAN, String balance, String frozen) {
        this.firstName = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthDate;
        this.phoneNumber = phoneNumber;
        this.personnummer = personNummer;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.university = university;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;


        this.accountNr = accountNr;
        this.accountIBAN = accountIBAN;
        this.balance = balance;
        this.frozen = "false";
        this.rewardPoints = 0;
        toDocument();
    }

    //andreea
    public Document toDocument(){
        doc = new Document("_id", new ObjectId()).append("first name", this.firstName).
                append("middle name", this.middleName).append("last name", this.lastName).
                append("birth date", this.birthdate).append("personnummer", this.personnummer).
                append("phone number", this. phoneNumber).append("email", this.email).
                append("address", this.address).append("city", this.city).
                append("postal code", this.postalCode).append("university", this.university).
                append("account number", this.accountNr).
                append("account IBAN", this.accountIBAN).append("balance", this.balance).
                append("frozen", this.frozen).append("reward points", this.rewardPoints).
                append("card number", this.cardNumber).append("expiration date", this.expirationDate).
                append("transactions", asList(new Document("sent", ""/*this.account.sent*/), new Document("received", ""/*this.account.received*/))).
                append("loans", asList(new Document("status", ""), new Document("quantity", 0),
                                        new Document("due date", "")));
        return doc;
    }

    //All of the following getters and setters were done by Juan

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPersonnummer() {
        return this.personnummer;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity(){return this.city;}

    public String getPostalCode(){return this.postalCode;}

    public String getUniversity() { return this.university; }

    public String getCardNumber(){return this.cardNumber;}

    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setCity(String city){this.city = city;}

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getBalance() {
        return balance;
    }

    public String getFrozen() {return frozen;}

    public String getExpirationDate(){return this.expirationDate;}

    public String getAccountNr() {
        return accountNr;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    //Ergi
    public void freezeAccount() {
        this.frozen = "true";
        Mongo.updateInformation("frozen",frozen,personnummer);
    }
    //Juan and Ergi
    public String getUsername1(){
        Object key= Mongo.extractKey2("personnummer",this.getPersonnummer());
        String username = Mongo.getUsername(key.toString(),"user name").toString();
        return username;
    }

}

