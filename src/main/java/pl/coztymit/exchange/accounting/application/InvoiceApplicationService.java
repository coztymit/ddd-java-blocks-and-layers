package pl.coztymit.exchange.accounting.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.accounting.domain.*;
import pl.coztymit.exchange.accounting.domain.Number;
import pl.coztymit.exchange.accounting.domain.policy.PositionLimitPolicy;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceApplicationService {
    private List<NotificationSender> senders;

    private InvoiceRepository invoiceRepository;
    private InvoiceFactory factory;

    @Autowired
    public InvoiceApplicationService(List<NotificationSender> senders, InvoiceRepository invoiceRepository) {
        this.senders = senders;
        this.invoiceRepository = invoiceRepository;
        this.factory = new InvoiceFactory();
    }

    @Transactional
    public CreateStatus createInvoice() {

        List<PositionAttributes> invoicePositions = new ArrayList<>();
        invoicePositions.add(new InvoicePosition(new BigDecimal("100"), "PLN"));
        Invoice invoice = this.factory.createInvoice(invoicePositions);
        this.invoiceRepository.save(invoice);

        /*foreach (NotificationSender sender in senders) {
          sender.sendNotification(invoice.ToString());
        }
        To oczywiscie moze zostac wzbogacone */

        return CreateStatus.Correct(invoice.invoiceNumber());
    }

    @Transactional
    public CreateStatus createInvoice(String invoiceNumber)
    {

        List<PositionAttributes> invoicePositions = new ArrayList<>();
        invoicePositions.add(new InvoicePosition(new BigDecimal("100"), "PLN"));

        Invoice invoice = factory.createInvoice(invoiceNumber, invoicePositions);
        invoiceRepository.save(invoice);


        /*foreach (NotificationSender sender in senders) {
           sender.SendNotification(invoice.ToString());
         }

        To oczywiscie moze zostac wzbogacone */
        return CreateStatus.Correct(invoice.invoiceNumber());

    }
    public final CreateStatus createInvoiceByBookKeeper() {
        // Transformacja z ebiektów komunikacji

        List<PositionAttributes> invoicePositions = InvoiceApplicationService.createPosition();
        BookKeeper bookKeeper = new BookKeeper();
        PositionLimitPolicy limitPolicy = bookKeeper.definePositionLimitPolicy();
        Invoice invoice = bookKeeper.createInvoice(invoicePositions, limitPolicy);
        invoiceRepository.save(invoice);
        return CreateStatus.Correct(invoice.invoiceNumber());
    }

    private static List<PositionAttributes> createPosition() {
        List<PositionAttributes> invoicePositions =  new ArrayList<>();
        invoicePositions.add(new InvoicePosition(new BigDecimal(100), "PLN"));
        return invoicePositions;
    }

    public final CreateStatus createCorrection(String number) {
        Invoice invoice = invoiceRepository.get(new Number(number));
        throw new RuntimeException();
    }

    public final void ApproveInvoice(String number) {
        Invoice invoice = invoiceRepository.get(new Number(number));
        invoice.approve();
        invoiceRepository.save(invoice);
        BookKeeper bookKeeper = new BookKeeper();
        Payment payment = bookKeeper.createPayment(invoice);
    }
}
