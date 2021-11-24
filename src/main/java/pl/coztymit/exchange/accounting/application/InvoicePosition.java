package pl.coztymit.exchange.accounting.application;

import pl.coztymit.exchange.accounting.domain.PositionAttributes;

import java.math.BigDecimal;

public class InvoicePosition implements PositionAttributes{
    private String number;
    private BigDecimal value;
    private String currency;

    public InvoicePosition(BigDecimal productValue, String currency)
    {
        this.number = "randomnumber";
        this.value = productValue;
        this.currency = currency;
    }
    @Override
    public String productNumber() {
        return number;
    }

    @Override
    public BigDecimal productValue() {
        return value;
    }

    @Override
    public String valueCurrency() {
        return currency;
    }
}
