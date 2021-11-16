package pl.coztymit.exchange.accounting.domain.policy;

public class EURPositionLimitPolicy implements PositionLimitPolicy{

    private int positionLimit = 5;

    @Override
    public boolean lessOrEqualsLimit(int positionsCount) {
        return positionLimit >= positionsCount;
    }
}
