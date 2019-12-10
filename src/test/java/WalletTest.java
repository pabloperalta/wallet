import ar.gob.afip.training.tdd.wallet.Wallet;
import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test class
 */
public class WalletTest {

    @Test
    void testGetBalanceReturns0_WhenGettingBalanceFromNewWallet() {
        // Given
        Wallet wallet = new Wallet();

        // When
        Double balance = wallet.getBalance();

        //Then
        assertThat(balance).isEqualTo(0);
    }

    @Test
    void testGetBalanceReturns100_WhenGettingBalanceOfWalletWith100() {
        Wallet wallet = new Wallet();
        wallet.setBalance(100d);

        Double balance = wallet.getBalance();

        assertThat(balance).isEqualTo(Double.valueOf(100));
    }

    @Test
    public void testWalletHas150Balance_WhenCredit150ToNewWallet() {
        Wallet wallet = new Wallet();
        wallet.credit(150d);

        Double balance = wallet.getBalance();

        assertThat(balance).isEqualTo(Double.valueOf(150));
    }

    @Test
    public void testWalletHas12345Balance_WhenCredit12345ToNewWallet() {
        Wallet wallet = new Wallet();
        wallet.credit(12345d);

        Double balance = wallet.getBalance();

        assertThat(balance).isEqualTo(Double.valueOf(12345));
    }

    @Test
    public void testWalletHas10Balance_WhenDebit90FromWalletWith100() {
        Wallet wallet = new Wallet();
        wallet.setBalance(100d);

        wallet.debit(90d);

        assertThat(wallet.getBalance()).isEqualTo(Double.valueOf(10));
    }

    @Test
    public void testWalletHas895Balance_WhenDebit105FromWalletWith1000() {
        Wallet wallet = new Wallet();
        wallet.setBalance(1000d);

        wallet.debit(105d);

        assertThat(wallet.getBalance()).isEqualTo(Double.valueOf(895));
    }
}