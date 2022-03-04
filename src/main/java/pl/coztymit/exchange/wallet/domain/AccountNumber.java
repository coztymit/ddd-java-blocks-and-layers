package pl.coztymit.exchange.wallet.domain;


import java.util.Random;

//VO
public class AccountNumber {
    private String value;

    public AccountNumber(String value) {
        if (value == null || value.length()!=5) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public static AccountNumber randomNumber() {
        Random random = new Random();
        return new AccountNumber("12345");
    }
}
