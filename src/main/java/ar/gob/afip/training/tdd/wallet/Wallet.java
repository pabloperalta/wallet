package ar.gob.afip.training.tdd.wallet;


import ar.gob.afip.training.tdd.wallet.exception.WalletBusinessException;

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

    public void debit(Double amount) throws WalletBusinessException {
        if (amount > this.balance) {
            throw new WalletBusinessException("No tenes saldo suficiente");
        }

        this.balance -= amount;
    }
}
