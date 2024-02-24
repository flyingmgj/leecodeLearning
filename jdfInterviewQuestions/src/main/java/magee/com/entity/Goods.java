package magee.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Goods {
    /**
     * 商品id
     */
    private String id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private double price;

    public double getPrice(int buyNumber) {
        return price * buyNumber;
    }

}
