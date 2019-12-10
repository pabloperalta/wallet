package ar.gob.afip.training.tdd.wallet.core;

import ar.gob.afip.training.tdd.wallet.api.NewWalletRequest;
import ar.gob.afip.training.tdd.wallet.exception.WalletNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class WalletBusiness {
    public Double getBalance(long id) throws WalletNotFoundException {
        return null;
    }

    public void newWallet(NewWalletRequest newWalletRequest) {

    }
}
