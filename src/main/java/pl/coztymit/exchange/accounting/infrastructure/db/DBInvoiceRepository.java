package pl.coztymit.exchange.accounting.infrastructure.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.accounting.domain.Invoice;
import pl.coztymit.exchange.accounting.domain.InvoiceRepository;
import pl.coztymit.exchange.accounting.domain.Number;

@Repository
public class DBInvoiceRepository implements InvoiceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBInvoiceRepository.class);
    @Override
    public void save(Invoice invoice) {
        LOGGER.info("Saved");
    }

    @Override
    public Invoice get(Number number) {
        LOGGER.info("get invoice");
        return null;
    }

    @Override
    public Invoice find(Number number) {
        LOGGER.info("get invoice");
        return null;
    }
}
