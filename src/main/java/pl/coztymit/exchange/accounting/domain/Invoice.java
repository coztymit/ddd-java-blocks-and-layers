package pl.coztymit.exchange.accounting.domain;

import pl.coztymit.exchange.accounting.domain.exception.CannotAddPositionToApprovedInvoiceException;
import pl.coztymit.exchange.accounting.domain.policy.PositionLimitPolicy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Aggregate
public class Invoice {

    private Number number;
    private ApproveStatus approveStatus = ApproveStatus.DRAFT;

    private Money positionsValue = Money.ZERO_PLN;
    private List<Position> positions = new ArrayList<>();
    private List<Correction> correction;
    private Seller seller;
    private Buyer buyer;

    //TO należy zamienić na politykę
    private static Money positionValueLimit = new Money(new BigDecimal("10000"));
    //TO należy zamienić na politykę
    private static int positionLimit = 10;


    Invoice(Seller seller, Buyer buyer)
    {
        this.seller = seller;
        this.buyer = buyer;
        this.number = new Number();

    }

    Invoice(Number number, Seller seller, Buyer buyer)
    {
        this.seller = seller;
        this.buyer = buyer;
        this.number = number;
    }

    public ApproveStatus approve()
    {
        this.approveStatus = ApproveStatus.APPROVED;
        return ApproveStatus.APPROVED;
    }

    void addPosition(Position position, PositionLimitPolicy positionLimitPolicy)
    {
        //InvariantCheck(position);

        if(!positionLimitPolicy.lessOrEqualsLimit(this.positions.size() + 1))
        {

            throw new RuntimeException();
        }

        if (approveStatus.equals(ApproveStatus.DRAFT))
        {
            positions.add(position);
            positionsValue = positionsValue.Add(position.positionValue());
        }
    }

    void addPositions(List<Position> positions)
    {
        //sprawdzenie invariant

        if (approveStatus.equals(ApproveStatus.DRAFT))
        {
            positions.forEach(pos ->
                    {
                            this.positionsValue = positionsValue.Add(pos.positionValue());
            this.positions.add(pos);
                }
                    );
        }
        else
        {
            throw new CannotAddPositionToApprovedInvoiceException();
        }
    }


    //invariant
    private boolean lessOrEqualsPositionLimit(int newPositionCount)
    {
        if(this.positions.size() + newPositionCount > 10)
        {
            return false;
        }
        return true;
    }
    //invariant
    private boolean valueLessOrEqualsMoneyLimit(Money newPositionValue)
    {
        var oldAndNewPositionValue = positionsValue.Add(newPositionValue);
        if (oldAndNewPositionValue.lessThan(positionValueLimit)) {
            return false;
        }
        return true;
    }

    private void invariantCheck(Position position)
    {
        if (!lessOrEqualsPositionLimit(1))
        {
            throw new RuntimeException();
        }
        if (!valueLessOrEqualsMoneyLimit(position.positionValue()))
        {
            throw new RuntimeException();
        }
    }

    public String invoiceNumber()
    {
        return this.number.toString();
    }

    public String toString()
    {
        return number.toString() + " : " + approveStatus.toString() + " : "
                + positionsValue.toString() + " : " + seller.toString();
    }
}
