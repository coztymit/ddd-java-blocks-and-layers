package pl.coztymit.exchange.wallet.domain;

import java.util.Objects;

//VO
public class TransactionType {
    public static TransactionType CARD = new TransactionType("CARD");
    public static TransactionType CURRENCY_EXCHANGE = new TransactionType("CURRENCY_EXCHANGE");
    private String type;

    private TransactionType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
