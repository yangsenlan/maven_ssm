package com.oracle.ssm.mapper;

import com.oracle.ssm.model.Orders;
import com.oracle.ssm.model.OrdersGoodsRelation;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

	void insertOrdersGoodsRelation(OrdersGoodsRelation relation);
}