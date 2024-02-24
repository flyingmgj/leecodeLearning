import magee.com.entity.Goods;
import magee.com.entity.Price;
import magee.com.entity.strategy.CashReturnStrategy;
import magee.com.entity.strategy.DiscountPriceStrategy;
import org.junit.Test;

public class JunitTest {

    public static Goods APPLE = new Goods("1", "苹果", 8.0d);
    public static Goods STRAWBERRY = new Goods("2", "草莓", 13.0d);
    public static Goods MANGO = new Goods("3", "芒果", 20.0d);



    /**
     * 无折扣购买草莓和芒果
     */
    @Test
    public void test() {
        int apples = 2;
        int strawberries = 8;
        double v = APPLE.getPrice(apples) + STRAWBERRY.getPrice(strawberries);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓共需花费"+ v+ "元");
    }

    @Test
    public void test1() {
        int apples = 2;
        int strawberries = 8;
        int mangos = 3;
        double v = APPLE.getPrice(apples) + STRAWBERRY.getPrice(strawberries) + MANGO.getPrice(mangos);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓" + mangos + "斤芒果共需花费"+ v+ "元");
    }


    /**
     * 草莓打8折
     */
    @Test
    public void test3() {
        int apples = 2;
        int strawberries = 8;
        int mangos = 3;

        Price priceDiscount = new Price(new DiscountPriceStrategy(0.8));
        double v = APPLE.getPrice(apples) + priceDiscount.cost(STRAWBERRY.getPrice(strawberries)) + MANGO.getPrice(mangos);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓" + mangos + "斤芒果共需花费"+ v+ "元");
    }


    /**
     * 草莓打8折，同时购物满 100 减 10 块。
     */
    @Test
    public void test4() {
        int apples = 2;
        int strawberries = 8;
        int mangos = 3;

        Price priceDiscount = new Price(new DiscountPriceStrategy(0.8));
        Price cashReturnDiscount = new Price(new CashReturnStrategy());
        double v = APPLE.getPrice(apples) + priceDiscount.cost(STRAWBERRY.getPrice(strawberries)) + MANGO.getPrice(mangos);
        double cost = cashReturnDiscount.cost(v);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓" + mangos + "斤芒果共需花费"+ cost+ "元");
    }

}
