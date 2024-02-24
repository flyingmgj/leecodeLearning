package magee.com.entity.strategy;

public class DiscountPriceStrategy implements MemberStrategy{

    private double discount_number;

    public DiscountPriceStrategy(double discount_number) {
        //可添加判断0~1
        this.discount_number = discount_number;
    }

    public double salePrice(double price) {
        return price * discount_number;
    }
}
