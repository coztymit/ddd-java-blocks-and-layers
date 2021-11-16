package pl.coztymit.exchange.accounting.domain;

import java.math.BigDecimal;

public interface PositionAttributes {
    String ProductNumber();
    BigDecimal ProductValue();
    String ValueCurrency();
}
