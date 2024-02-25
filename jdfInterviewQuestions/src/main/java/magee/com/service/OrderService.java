package magee.com.service;

import magee.com.entity.Goods;
import magee.com.entity.Price;
import magee.com.entity.strategy.CashReturnStrategy;
import magee.com.entity.strategy.DiscountPriceStrategy;

public class OrderService {

    public static Goods APPLE = new Goods("1", "苹果", 8.0d);
    public static Goods STRAWBERRY = new Goods("2", "草莓", 13.0d);
    public static Goods MANGO = new Goods("3", "芒果", 20.0d);


    /**
     * 无折扣购买
     */
    public double noDiscount(int apples, int strawberries, int mangoes) {
        double v = APPLE.getPrice(apples) + STRAWBERRY.getPrice(strawberries) + MANGO.getPrice(mangoes);
        return v;
    }

    /**
     * 只有草莓打8折
     * @param apples
     * @param strawberries
     * @param mangoes
     * @return
     */
    public double discountOnlyStrawberry(int apples, int strawberries, int mangoes) {
        Price priceDiscount = new Price(new DiscountPriceStrategy(0.8));
        double v = APPLE.getPrice(apples) + priceDiscount.cost(STRAWBERRY.getPrice(strawberries)) + MANGO.getPrice(mangoes);

        return v;
    }

    /**
     * 草莓打8折，且全场100满减10
     * @param apples
     * @param strawberries
     * @param mangoes
     * @return
     */
    public double discountCashReturn(int apples, int strawberries, int mangoes) {
        Price priceDiscount = new Price(new DiscountPriceStrategy(0.8));
        Price cashReturnDiscount = new Price(new CashReturnStrategy());
        double v = APPLE.getPrice(apples) + priceDiscount.cost(STRAWBERRY.getPrice(strawberries)) + MANGO.getPrice(mangoes);
        //满减
        double cost = cashReturnDiscount.cost(v);
        return cost;
    }


}
