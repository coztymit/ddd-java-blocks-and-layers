package pl.coztymit.exchange.accounting.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service
        ;
import pl.coztymit.exchange.accounting.domain.*;
import pl.coztymit.exchange.accounting.domain.Number;
import pl.coztymit.exchange.accounting.domain.policy.LineLimitPolicy;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceApplicationService {


    private InvoiceRepository invoiceRepository;
    private InvoiceFactory factory;


    @Autowired
    public InvoiceApplicationService(List<NotificationSender> senders, InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.factory = new InvoiceFactory();
    }


    @Transactional
    public CreateStatus createInvoice() {

        Invoice invoice = this.factory.createInvoice(null);
        this.invoiceRepository.save(invoice);

        return CreateStatus.Correct(invoice.invoiceNumber());
    }

    @Transactional
    public CreateStatus createInvoice(String invoiceNumber)
    {

        List<LineAttributes> invoiceLines = new ArrayList<>();
        invoiceLines.add(new InvoiceLine(new BigDecimal("100"), "PLN"));

        Invoice invoice = factory.createInvoice(invoiceNumber, invoiceLines);
        invoiceRepository.save(invoice);


        /*foreach (NotificationSender sender in senders) {
           sender.SendNotification(invoice.ToString());
         }

        To oczywiscie moze zostac wzbogacone */
        return CreateStatus.Correct(invoice.invoiceNumber());

    }
    public final CreateStatus createInvoiceByBookKeeper() {
        // Transformacja z ebiektów komunikacji

        List<LineAttributes> invoiceLines = InvoiceApplicationService.createLines();
        BookKeeper bookKeeper = new BookKeeper();
        LineLimitPolicy limitPolicy = bookKeeper.definePositionLimitPolicy();
        Invoice invoice = bookKeeper.createInvoice(invoiceLines, limitPolicy);
        invoiceRepository.save(invoice);
        return CreateStatus.Correct(invoice.invoiceNumber());
    }

    private static List<LineAttributes> createLines() {
        List<LineAttributes> invoicePositions =  new ArrayList<>();
        invoicePositions.add(new InvoiceLine(new BigDecimal(100), "PLN"));
        return invoicePositions;
    }

    public final CreateStatus createCorrection(String number) {
        Invoice invoice = invoiceRepository.get(new Number(number));
        throw new RuntimeException();
    }

    public final void approveInvoice(String number) {
        Invoice invoice = invoiceRepository.get(new Number(number));
        invoice.approve();
        invoiceRepository.save(invoice);
        BookKeeper bookKeeper = new BookKeeper();
        Payment payment = bookKeeper.createPayment(invoice);

    }
}
