package pl.coztymit.exchange.accounting.domain;

import org.springframework.stereotype.Repository;


public interface InvoiceRepository {

    void save(Invoice invoice);
    Invoice get(Number number);
    Invoice find(Number number);
}
