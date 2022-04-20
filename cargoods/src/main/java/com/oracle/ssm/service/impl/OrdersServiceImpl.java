package com.oracle.ssm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.ssm.mapper.GoodsMapper;
import com.oracle.ssm.mapper.OrdersMapper;
import com.oracle.ssm.model.Orders;
import com.oracle.ssm.model.OrdersGoodsRelation;
import com.oracle.ssm.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	

	@Override
	public Orders insertOrdersByGoodsUser(int id, String userId) {
		//��һ���������ɶ�������
		Orders orders=new Orders();
		orders.setOrdersMoney(goodsMapper.selectByPrimaryKey(id).getGoodPrice());
		orders.setOrderTime(new Date());
		orders.setShopCount("1");
		//1������δ����  2�����Ѹ��� 3���� �ѷ��� 4 �������ջ�
		orders.setStaus(1);
		orders.setUpdateTime(new Date());
		orders.setUserId(Integer.parseInt(userId));
		ordersMapper.insertSelective(orders);
		//�ڶ��������붩����Ʒ��ϵ��
		OrdersGoodsRelation relation=new OrdersGoodsRelation();
		relation.setGoodsId(id);
		relation.setOrdersId(orders.getOrderId());
		ordersMapper.insertOrdersGoodsRelation(relation);
		//�����ű�������֮��
		//�������  �Ż�ȯ  ���� ..........
		return orders;
	}

}
