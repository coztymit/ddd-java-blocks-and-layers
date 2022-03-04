package pl.coztymit.exchange.wallet.domain;


import java.util.Optional;

public interface AccountRepository {

    Optional<Account> find(AccountNumber accountNumber);

    void save(Account account);
}
