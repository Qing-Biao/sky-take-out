package com.sky.mapper;

import com.sky.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);
}
