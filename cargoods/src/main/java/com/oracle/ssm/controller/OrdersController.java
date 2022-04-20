package com.oracle.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.ssm.model.Goods;
import com.oracle.ssm.model.Orders;
import com.oracle.ssm.model.Receiver;
import com.oracle.ssm.service.GoodsService;
import com.oracle.ssm.service.OrdersService;
import com.oracle.ssm.service.ReceiverService;
import com.oracle.ssm.util.JedisUtil;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private RedisTemplate jedis;
	@Autowired
	private JedisUtil jedisUtil;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ReceiverService receiverService;
	/**
	 * ����ȷ��ҳ
	 * @return
	 */
	@RequestMapping("/ordersDetail")
	public String ordersDetail(int id,HttpServletRequest request) {
		//id����Ʒ��id 
		//1:��ȷ���û��Ƿ������ɹ�������Ʒ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		String usersSetKey="kill:users:"+date+":set:"+id;
		String userId=jedisUtil.getUserBySession(request).getId()+"";
		
		if(jedis.boundSetOps(usersSetKey).isMember(userId)) {
			 //���ɶ��� 
			 //���ɶ���  ��Ʒ id   �û�id 
			Orders orders=ordersService.insertOrdersByGoodsUser(id,userId);
			//orders goodslistû��ֵ
			Goods goods=goodsService.findGoodsById(id);
			List<Goods> goodsList=new ArrayList();
			goodsList.add(goods);
			orders.setGoodsList(goodsList);
			List<Receiver> receiverList=receiverService.findReveiversByUserId(Integer.parseInt(userId));
			request.setAttribute("orders", orders);
			request.setAttribute("receiverList", receiverList);
			//ɾ������¼�û�id��set���ϵ��еĸ��û�Id
			return "index/buyprod";
			
		}else {
			//�Ƿ�����
			return "exception/noAuth";
		}
		
	}

}
