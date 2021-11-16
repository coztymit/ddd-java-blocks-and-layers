package pl.coztymit.exchange.accounting.domain;

import pl.coztymit.exchange.accounting.domain.policy.PLNPositionLimitPolicy;
import pl.coztymit.exchange.accounting.domain.policy.PositionLimitPolicy;

import java.util.List;

public class InvoiceFactory {

    // dla przyspieszenia
    private Seller seller = new Seller("Coztymit", "Jan", "Kowalski", new NIP());
    private Buyer buyer = new Buyer("Coztymit", "Jan", "Kwiatkowski", new NIP());

    public Invoice createInvoice(List<PositionAttributes> positionAttributes)
    {
        Invoice invoice = new Invoice(seller, buyer);

        return prepareInvoice(positionAttributes, invoice);
    }

    public Invoice createInvoice(String number, List<PositionAttributes> positionAttributes)
    {
        Invoice invoice = new Invoice(new Number(number), seller, buyer);

        return prepareInvoice(positionAttributes, invoice);
    }

    Invoice createInvoice(List<PositionAttributes> possitionAttributes, PositionLimitPolicy limitPolicy)
    {

        //Czy nie łamiemy tu wprowadznai agregatu w nieodpowieni stan?
        Invoice invoice = new Invoice(seller, buyer);

        return prepareInvoice(possitionAttributes, invoice);
    }

    static Invoice prepareInvoice(List<PositionAttributes> possitionAttributes, Invoice invoice)
    {
        //okresl polityke ilosci pozycji
        PositionLimitPolicy limitPolicy = new PLNPositionLimitPolicy();

        possitionAttributes.forEach(pos ->
                invoice.addPosition(
                        new Position(new ProductNumber(pos.ProductNumber()), new Money(pos.ProductValue())),
                        limitPolicy));
        return invoice;
    }

}
