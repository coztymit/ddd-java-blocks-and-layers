package pl.coztymit.exchange.accounting.domain;

import pl.coztymit.exchange.accounting.domain.policy.PLNPositionLimitPolicy;
import pl.coztymit.exchange.accounting.domain.policy.PositionLimitPolicy;

import java.util.List;

public class BookKeeper {
    InvoiceFactory factory = new InvoiceFactory();

    public Invoice createInvoice(List<PositionAttributes> positionAttributes, PositionLimitPolicy limitPolicy)
    {
        //Okresla polityki
        return factory.createInvoice(positionAttributes, limitPolicy);
    }

    public Invoice createInvoiceCorrection(List<PositionAttributes> possitionAttributes)
    {
        return factory.createInvoice(possitionAttributes);
    }

    public Payment createPayment(Invoice invoice)
    {
        // na podstawie warunków biznesowych tworzy nowey obiekt platnosci
        return new Payment();
    }

    public PositionLimitPolicy definePositionLimitPolicy()
    {
        //tu powinna byc implementacja przypadkow okreslania polityki
        return new PLNPositionLimitPolicy();
    }
}
