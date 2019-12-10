package ar.gob.afip.training.tdd.wallet.api;

import ar.gob.afip.training.tdd.wallet.core.WalletBusiness;
import ar.gob.afip.training.tdd.wallet.exception.InvalidWalletException;
import ar.gob.afip.training.tdd.wallet.exception.WalletNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("wallet")
@RequestMapping("/wallet")
public class WalletController {


    private WalletBusiness walletBusiness;
    private WalletValidator walletValidator;

    @Autowired
    public WalletController(WalletBusiness walletBusiness, WalletValidator walletValidator) {
        this.walletBusiness = walletBusiness;
        this.walletValidator = walletValidator;
    }

    public Double getBalance(long id) {
        try {
            return this.walletBusiness.getBalance(id);
        } catch (WalletNotFoundException e) {
            return 0d;
        }
    }

    public void newWallet(NewWalletRequest newWalletRequest) throws InvalidWalletException {
        this.walletValidator.validate(newWalletRequest);

    }
}