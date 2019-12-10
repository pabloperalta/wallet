package ar.gob.afip.training.tdd.wallet.api;

import ar.gob.afip.training.tdd.wallet.core.WalletBusiness;
import ar.gob.afip.training.tdd.wallet.exception.InvalidWalletException;
import ar.gob.afip.training.tdd.wallet.exception.WalletNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

class WalletControllerTest {

    private WalletController walletController;
    private WalletBusiness walletBusiness;
    private WalletValidator walletValidator;

    @BeforeEach
    public void setUp() {
        this.walletBusiness = mock(WalletBusiness.class);
        this.walletValidator = mock(WalletValidator.class);
        walletController = new WalletController(this.walletBusiness, this.walletValidator);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 873L, 43728L})
    public void testGetBalanceCallsWalletBusinessGetBalanceWithGivenId_WhenGettingBalanceForGivenId(long id) throws WalletNotFoundException {
        this.walletController.getBalance(id);

        verify(this.walletBusiness).getBalance(id);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvSource({
            "1, 23, 34234",
            "2, 532, 7.3"
    })
    public void testGetBalanceReturnsSameAsWalletBusinessGetBalanceForId_WhenGetingBalanceOfWalletWithId(int id, Double expected) throws WalletNotFoundException {
        when(this.walletBusiness.getBalance(id)).thenReturn(expected);

        Double result = this.walletController.getBalance(id);

        assertThat(result).isSameAs(expected);
    }

    @Test
    public void testGetBalanceReturns0WhenWalletBusinessThrowsWalletNotFoundException_WhenGettingTheBalanceOfANonExistingWallet() throws WalletNotFoundException {
        int id = 1;
        when(this.walletBusiness.getBalance(id)).thenThrow(new WalletNotFoundException("La billetera solicitada no ha sido encontrada"));

        Double result = this.walletController.getBalance(id);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testNewWalletThrowsInvalidWalletExceptionWhenWalletValidatorThrowsInvalidWalletException_WhenCreatingAnInvalidWallet() throws WalletNotFoundException, InvalidWalletException {
        NewWalletRequest newWalletRequest = new NewWalletRequest();
        String message = "La billetera que se intento crear es invalida";
        doThrow(new InvalidWalletException(message)).when(this.walletValidator).validate(newWalletRequest);

        try {
            this.walletController.newWallet(newWalletRequest);
            fail();
        } catch (Exception e) {
            assertThat(e).isExactlyInstanceOf(InvalidWalletException.class);
            assertThat(e).hasMessage(message);

            // Las siguientes dos assertions son iguales. Testeo que no se haya creado ninguna billetera
            verify(this.walletBusiness, times(0)).newWallet(any());

            verify(this.walletBusiness, never()).newWallet(any());
        }
    }

}