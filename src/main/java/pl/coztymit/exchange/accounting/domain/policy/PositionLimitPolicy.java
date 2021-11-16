package pl.coztymit.exchange.accounting.domain.policy;

public interface PositionLimitPolicy {
    boolean lessOrEqualsLimit(int positionsCount);
}
