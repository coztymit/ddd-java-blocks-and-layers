package pl.coztymit.exchange.wallet.domain.policy;



import pl.coztymit.exchange.wallet.domain.Money;

import java.math.BigDecimal;

public class PLNTransactionLimitPolicy implements TransactionLimitPolicy{
    private Money upperTransactionLimit = new Money(new BigDecimal(15000));
    private Money lowerTransactionLimit = new Money(new BigDecimal(50));
    @Override
    public boolean withinTheLimit(Money funds) {
        if(funds.lessOrEqualsThan(upperTransactionLimit) && funds.greaterOrEqualsThan(lowerTransactionLimit)){
            return true;
        }
        return false;
    }
}
