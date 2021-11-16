package pl.coztymit.exchange.accounting.domain.policy;

public class PLNPositionLimitPolicy implements PositionLimitPolicy{

    private int positionLimit = 10;

    @Override
    public boolean lessOrEqualsLimit(int positionsCount) {
        return positionLimit >= positionsCount;
    }
}
