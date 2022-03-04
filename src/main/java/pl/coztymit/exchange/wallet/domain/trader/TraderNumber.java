package pl.coztymit.exchange.wallet.domain.trader;

public class TraderNumber {
    private String value;

    public TraderNumber(String number) {
        if(number==null || number.length()!=10){
            throw new RuntimeException("Incorrect trader number");
        }
        this.value = number;
    }
}
