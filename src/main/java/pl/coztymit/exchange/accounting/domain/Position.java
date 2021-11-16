package pl.coztymit.exchange.accounting.domain;

class Position {
    //nazwa nie przypadkowa ale nie do realnego systemu
    //pojawił się obcy kontekst - PRODUCT
    private PositionBusinessId id;
    private ProductNumber productNumber;
    private Money productValue;

    Position(ProductNumber productNumber, Money productValue)
    {
        id = new PositionBusinessId();
        this.productNumber = productNumber;
        this.productValue = productValue;
    }


    public Money positionValue()
    {
        return productValue;
    }

    public Money positionNumber()
    {
        return productValue;
    }
}
