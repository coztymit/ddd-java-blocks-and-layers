package pl.coztymit.exchange.wallet.infrastructure;



import pl.coztymit.exchange.wallet.domain.Account;
import pl.coztymit.exchange.wallet.domain.AccountNumber;
import pl.coztymit.exchange.wallet.domain.AccountRepository;

import java.util.Optional;

public class DBAccountRepository implements AccountRepository {
    @Override
    public Optional<Account> find(AccountNumber accountNumber) {
        return Optional.empty();
    }

    @Override
    public void save(Account account) {

    }
}
