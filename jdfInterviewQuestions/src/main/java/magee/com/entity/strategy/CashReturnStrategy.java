package magee.com.entity.strategy;

public class CashReturnStrategy implements MemberStrategy{

    /**
     * 满100减10
     * @param price
     * @return
     */
    public double salePrice(double price) {
        return price - Math.floor(price / 100)*10;
    }
}
