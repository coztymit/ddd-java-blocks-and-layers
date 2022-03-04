package pl.coztymit.exchange.wallet.domain.policy;

import pl.coztymit.exchange.wallet.domain.Currency;
import pl.coztymit.exchange.wallet.domain.Money;


import java.math.BigDecimal;

public class EuroTransactionLimitPolicy implements TransactionLimitPolicy{
    private Money upperTransactionLimit = new Money(new BigDecimal(4000), Currency.EUR);
    private Money lowerTransactionLimit = new Money(new BigDecimal(12), Currency.EUR);
    @Override
    public boolean withinTheLimit(Money funds) {
        if(funds.lessOrEqualsThan(upperTransactionLimit) && funds.greaterOrEqualsThan(lowerTransactionLimit)){
            return true;
        }
        return false;
    }
}
