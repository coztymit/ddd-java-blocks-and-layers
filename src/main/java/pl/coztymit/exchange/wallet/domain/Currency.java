package pl.coztymit.exchange.wallet.domain;

import java.util.Objects;

public class Currency {
    private String value;
    public static Currency DEFAULT = new Currency("PLN");
    public static Currency EUR = new Currency("EUR");

    public Currency(String value) {
        if (value.length() != 3)
        {
            throw new RuntimeException();
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(value, currency.value);
    }

    public String toString()
    {
        return value;
    }
}
