package ar.gob.afip.training.tdd.wallet.api;

import ar.gob.afip.training.tdd.wallet.exception.InvalidWalletException;
import org.springframework.stereotype.Component;

@Component
public class WalletValidator {
    public void validate(NewWalletRequest newWalletRequest) throws InvalidWalletException {

    }
}
