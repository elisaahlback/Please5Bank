package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Transaction {

    private String date;
    private final String receiver;
    private final String receiverIBAN;
    private final long quantity;
    private final String concept;
    private String status;

    public Transaction(String receiver, String receiverIBAN, long quantity, String concept){//in TransactionsController we set the date and status
        this.date = "";
        this.receiver = "";
        this.receiverIBAN = "";
        this.quantity = 0;
        this.concept = "";
        this.status = "";
    }

    public String getDate() {
        return date;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getReceiverIBAN() { return receiverIBAN; }

    public long getQuantity() {
        return quantity;
    }

    public String getConcept() {
        return concept;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void setDate(String date) { this.date = date; }

    //andreea
    public Document save() {//method to save transaction in collection 4
        Document transaction = new Document("_id", new ObjectId()).append("receiver", this.receiver).
        append("receiver iban", this.receiverIBAN).append("quantity", this.quantity).
                append("date", this.date).append("concept", this.concept).append("status", this.status);
        return transaction;
    }
}
