package magee.com.entity;

import magee.com.entity.strategy.MemberStrategy;

public class Price {

    private MemberStrategy strategy;

    public Price(MemberStrategy strategy) {
        this.strategy=strategy;
    }

    public double cost(double price) {
        return this.strategy.salePrice(price);
    }

}
