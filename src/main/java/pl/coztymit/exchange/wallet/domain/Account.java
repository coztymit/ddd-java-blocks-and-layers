package pl.coztymit.exchange.wallet.domain;

import pl.coztymit.exchange.wallet.domain.policy.TransactionLimitPolicy;
import pl.coztymit.exchange.wallet.domain.trader.Trader;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

// Aggregat
@Entity
public class Account {

    //limity inwariantów
    private int cardTransactionDailyLimit = 1;

    //Zdefiniowane VO
    private Balance balance;
    private AccountNumber accountNumber;
    private Trader trader;

    //Pomocnicza struktura
    private List<Transaction> transactions;

    //Tworzy konto w złotówkach
    Account(AccountNumber accountNumber, Trader trader) {
        this.accountNumber = accountNumber;
        this.trader = trader;
        this.balance = Balance.INITIAL_BALANCE;
    }

    //Tworzy konto o dowolnej walucie
    Account(AccountNumber accountNumber, Trader trader, Balance balanceInitValue) {
        this.accountNumber = accountNumber;
        this.trader = trader;
        this.balance = balanceInitValue;
    }

    //Zasil konto
    public void depositFunds(Money funds, TransactionType transactionType, TransactionLimitPolicy limitPolicy){
        if(limitPolicy.withinTheLimit(funds) && !exhaustedTransactionLimitForToday(TransactionType.CARD, limitPolicy)){
            balance = balance.addFunds(funds);
        }
        // obsługa statusu operacji
        //Obsługa wyjątku
    }

    //Zmniejsz stan konta
    public void withdrawFunds(Money funds, TransactionType transactionType, TransactionLimitPolicy limitPolicy){
        if(limitPolicy.withinTheLimit(funds) && !exhaustedTransactionLimitForToday(TransactionType.CARD, limitPolicy)){
            balance = balance.withdrawFunds(funds);
        }
    }

    private boolean exhaustedTransactionLimitForToday(TransactionType transactionType, TransactionLimitPolicy limitPolicy){
        if(transactionType.equals(TransactionType.CARD) &&  countDailyTransactionByType(TransactionType.CARD) <= cardTransactionDailyLimit){
           return false;
        }
        return true;
    }

    private long countDailyTransactionByType(TransactionType transactionType){

        return transactions.stream()
                .filter(trans -> trans.type().equals(transactionType) && trans.transactionDate().isItTheSameDay(LocalDate.now()))
                .count();
    }
}
