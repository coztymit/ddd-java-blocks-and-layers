package pl.coztymit.exchange.wallet.domain;

import java.time.LocalDate;

class TransactionDate {
    private LocalDate localDate;

    public TransactionDate(LocalDate localDate) {
        //Można dodać weryfikację czy data nie jest późniejsza niż zakładana data
        this.localDate = localDate;
    }

    public boolean isItTheSameDay(LocalDate date){
        return this.localDate.isEqual(date);
    }


}
