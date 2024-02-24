package magee.com.common.enums;

public enum DISCOUNTENUM {
    /**
     * 无折扣
     */
    NORMAL("normal"),
    /**
     * 打折
     */
    CASH_DISCOUNT("cash_discount"),
    /**
     * 满减
     */
    CASH_RENTURN("cash_return");


    private String type;

    private DISCOUNTENUM(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
