import magee.com.entity.Goods;
import magee.com.entity.Price;
import magee.com.entity.strategy.DiscountPriceStrategy;
import magee.com.service.OrderService;
import org.junit.Assert;
import org.junit.Test;

public class JunitTest {

    public static Goods APPLE = new Goods("1", "苹果", 8.0d);
    public static Goods STRAWBERRY = new Goods("2", "草莓", 13.0d);
    public static Goods MANGO = new Goods("3", "芒果", 20.0d);

    public OrderService service = new OrderService();


    /**
     * 无折扣购买草莓和芒果
     */
    @Test
    public void test() {
        int apples = 2;
        int strawberries = 8;
        double v = service.noDiscount(2, 8, 0);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓共需花费"+ v+ "元");
        Assert.assertTrue( 120.d == v);
    }

    @Test
    public void test1() {
        int apples = 2;
        int strawberries = 8;
        int mangoes = 3;
        double v = service.noDiscount(2, 8, 3);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓" + mangoes + "斤芒果共需花费"+ v+ "元");
        Assert.assertTrue( 180.d == v);
    }


    /**
     * 草莓打8折
     */
    @Test
    public void test3() {
        int apples = 2;
        int strawberries = 8;
        int mangoes = 3;

        Price priceDiscount = new Price(new DiscountPriceStrategy(0.8));
        double v = service.discountOnlyStrawberry(2,8,3);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓" + mangoes + "斤芒果共需花费"+ v+ "元");
        Assert.assertTrue( 159.2d == v);
    }


    /**
     * 草莓打8折，同时购物满 100 减 10 块。
     */
    @Test
    public void test4() {
        int apples = 2;
        int strawberries = 8;
        int mangoes = 3;

        double v = service.discountCashReturn(2, 8, 3);
        System.out.println("购买"+apples+"斤苹果和"+ strawberries + "斤草莓" + mangoes + "斤芒果共需花费"+ v+ "元");
        Assert.assertTrue( 149.2 == v);
    }

}
