package pl.coztymit.exchange.wallet.domain.policy;

import pl.coztymit.exchange.wallet.domain.Money;

public interface TransactionLimitPolicy {
    boolean withinTheLimit(Money funds);
}
