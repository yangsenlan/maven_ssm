package com.oracle.ssm.service;

import com.oracle.ssm.model.Orders;

public interface OrdersService {

	Orders insertOrdersByGoodsUser(int id, String userId);

}
