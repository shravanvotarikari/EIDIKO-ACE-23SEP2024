
class Transaction {
    private String date;
    private String type;
    private double amount;
    
    public Transaction() {	}
    
    
    // Constructor
    public Transaction(String date, String type, double amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}