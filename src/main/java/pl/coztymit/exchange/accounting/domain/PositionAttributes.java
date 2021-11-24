package pl.coztymit.exchange.accounting.domain;

import java.math.BigDecimal;

public interface PositionAttributes {
    String productNumber();
    BigDecimal productValue();
    String valueCurrency();
}
