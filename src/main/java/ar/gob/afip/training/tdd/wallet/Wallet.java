package ar.gob.afip.training.tdd.wallet;


public class Wallet {
    private Double balance = 0d;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void credit(Double amount) {
        this.balance += amount;
    }

    public void debit(Double amount) {
        this.balance -= amount;
    }
}
