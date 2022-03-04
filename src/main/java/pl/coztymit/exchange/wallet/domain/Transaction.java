package pl.coztymit.exchange.wallet.domain;

import javax.persistence.Entity;

//Encja wewnętrzna agreagatu
@Entity
class Transaction {
    private TransactionNumber number;
    private TransactionType type;
    private Money value;
    private TransactionDate date;

    public TransactionDate transactionDate() {
        return date;
    }

    public TransactionType type() {
        return type;
    }


}
