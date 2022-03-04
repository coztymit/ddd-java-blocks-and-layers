package pl.coztymit.exchange.wallet.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.wallet.domain.*;
import pl.coztymit.exchange.wallet.domain.policy.PLNTransactionLimitPolicy;
import pl.coztymit.exchange.wallet.domain.policy.TransactionLimitPolicy;
import pl.coztymit.exchange.wallet.domain.trader.Trader;
import pl.coztymit.exchange.wallet.domain.trader.TraderNumber;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountAppService {
    private AccountRepository accountRepository;
    private AccountFactory accountFactory;


    @Autowired
    public AccountAppService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.accountFactory = new AccountFactory();
    }

    //Zasil konto
    @Transactional
    public void depositFundsByCard(Money funds, String accountNumber, String traderNumber){
        Trader trader = new Trader(new TraderNumber(traderNumber));
        Account account = prepareAccount(accountNumber, trader);

        TransactionLimitPolicy policy = new PLNTransactionLimitPolicy();

        account.depositFunds(funds, TransactionType.CARD, policy);
        accountRepository.save(account);

    }

    //Zasil konto
    @Transactional
    public void depositFundsByCurrencyExchange(Money funds, String accountNumber, String traderNumber){
        Trader trader = new Trader(new TraderNumber(traderNumber));
        Account account = prepareAccount(accountNumber, trader);
        TransactionLimitPolicy policy = new PLNTransactionLimitPolicy();
        account.depositFunds(funds,TransactionType.CURRENCY_EXCHANGE, policy);
        accountRepository.save(account);

    }

    //Zasil konto
    @Transactional
    public void depositFunds(Money funds, TransactionType transactionType, String accountNumber, String traderNumber){
        Trader trader = new Trader(new TraderNumber(traderNumber));
        Account account = prepareAccount(accountNumber, trader);
        TransactionLimitPolicy policy = new PLNTransactionLimitPolicy();

        account.depositFunds(funds, transactionType, policy);
        accountRepository.save(account);

    }

    //Zmniejsz stan konta
    @Transactional
    public void withdrawFunds(Money funds, TransactionType transactionType, String accountNumber, String traderNumber){
        Trader trader = new Trader(new TraderNumber(traderNumber));
        Account account = prepareAccount(accountNumber, trader);
        TransactionLimitPolicy policy = new PLNTransactionLimitPolicy();
        account.withdrawFunds(funds, transactionType, policy);

    }

    private Account prepareAccount(String number, Trader trader) {
        AccountNumber accountNumber = new AccountNumber(number);

        Optional<Account> account = accountRepository.find(accountNumber);
        if(account.isPresent()){
            return account.get();
        }
        return accountFactory.create(accountNumber, trader);
    }
}
