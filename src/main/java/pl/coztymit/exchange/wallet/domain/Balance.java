package pl.coztymit.exchange.wallet.domain;

//VO
public class Balance {
    public static Balance INITIAL_BALANCE = new Balance(Money.ZERO_PLN);
    private Money value;

    public Balance(Money value) {
        this.value = value;
    }

    //Tu wprowadzimy politykę
    public Balance addFunds(Money funds) {
        return new Balance(this.value.add(funds));
    }

    public Balance withdrawFunds(Money funds) {
        //dopytać biznesu czy możemy robić debet
        return new Balance(this.value.sub(funds));
    }
}
