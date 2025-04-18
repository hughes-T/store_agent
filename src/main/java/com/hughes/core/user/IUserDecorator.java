package com.hughes.core.user;

/**
 * User 装饰器
 *
 * @author hts
 * @date 2025/4/18 13:20
 */
public interface IUserDecorator {

    /**
     * 经销商
     */
    String TRADE_TYPE_DEALER = "dealer";


    String TRADE_TYPE_STORE = "store";

    /**
     * 消费用户
     */
    String TRADE_TYPE_CUSTOMER = "customer";


    String getTradeType();

    Long getUserId();


}
