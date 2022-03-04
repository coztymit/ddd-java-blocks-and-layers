package pl.coztymit.exchange.wallet.domain;


import pl.coztymit.exchange.wallet.domain.trader.Trader;

public class AccountFactory {

    public Account create(AccountNumber accountNumber, Trader trader){
        return new Account(accountNumber, trader);
    }
}
