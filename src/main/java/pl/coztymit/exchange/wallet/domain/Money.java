package pl.coztymit.exchange.wallet.domain;


import pl.coztymit.exchange.accounting.domain.exception.DifferentCurrenciesException;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    public static Money ZERO_PLN = new Money(BigDecimal.ZERO);
    private BigDecimal value;
    private Currency currency;

    public Money(BigDecimal value)
    {
        this.value = value;
        currency = Currency.DEFAULT;
    }
    public Money(BigDecimal value, Currency currency)
    {
        this.value = value;
        this.currency = currency;
    }

    public Money add(Money money)
    {
        if (!this.currency.equals(money.currency)) {
            throw new RuntimeException();
        }
        return new Money(this.value.add(money.value), this.currency);
    }

    public Money sub(Money money)
    {
        if (!this.currency.equals(money.currency))
        {
            //TODO specify exception
            throw new RuntimeException();
        }
        return new Money(this.value.subtract(money.value), this.currency);
    }

    public Money add(Money money, Money money2)
    {
        if (!money2.currency.equals(money.currency))
        {
            throw new DifferentCurrenciesException(money.currency.toString(), money2.currency.toString());
        }
        return new Money(money.value.add(money2.value), this.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value) &&
                Objects.equals(currency, money.currency);
    }


    public boolean lessOrEqualsThan(Money valueToCompere)
    {
        //NIE WIEM CZY TO JEST DOBRZE NAPISANE - TESTY JEDNOSTKOWE!!!
        if (this.value.compareTo(valueToCompere.value) <= 0)
        {
            return true;
        }
        return false;
    }
    public boolean greaterOrEqualsThan(Money valueToCompere)
    {
        //NIE WIEM CZY TO JEST DOBRZE NAPISANE - TESTY JEDNOSTKOWE!!!
        if (this.value.compareTo(valueToCompere.value) >= 0)
        {
            return true;
        }
        return false;
    }

    public String toString()
    {
        return value.toString() + " currency:" + this.currency.toString();
    }
}
