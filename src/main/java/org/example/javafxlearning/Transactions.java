package org.example.javafxlearning;
public class Transactions {
    String addedSpent;
    String type;
    Double amount;
    public Transactions(String addedSpent, String type, Double amount){
        this.addedSpent = addedSpent;
        this.type = type;
        this.amount = amount;
    }

    public String getAddedSpent() { return addedSpent; }
    public void setAddedSpent(String addedSpent) { this.addedSpent = addedSpent; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

}

