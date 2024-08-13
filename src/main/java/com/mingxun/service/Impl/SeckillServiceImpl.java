package com.mingxun.service.Impl;

import com.mingxun.exception.SellException;
import com.mingxun.service.RedisLock;
import com.mingxun.service.SecKillService;
import com.mingxun.utils.KeyUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImpl implements SecKillService {

    private static final int TIMEOUT = 10 * 1000; // 超时时间 10s
    @Autowired
    private RedisLock redisLock;
    /*
    *  国庆活动，粥特价，限量100000份*/

    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;
    static
    {
        /*
        * 模拟多个表，商品信息表，库存表，秒杀成功订单表*/
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {
        return  "国庆活动，粥特价，限量份"
                + products.get(productId)
                + " 还剩：" + stock.get(productId) + " 份"
                + " 该商品成功下单用户数目: "
                + orders.size() + " 人";
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        // 加锁
        // 定义一个时间
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "诶呦喂");
        }

        // 1.查询该订单库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            // 2. 下单（模拟不同用户openid不同）
            orders.put(KeyUtil.genUniqueKey(), productId);
        //     3. 减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        // 解锁
        redisLock.unlock(productId, String.valueOf(time));
    }

    @Override
    public String querySecKillProductInfo(String productId) {
            return this.queryMap(productId);
    }
}
