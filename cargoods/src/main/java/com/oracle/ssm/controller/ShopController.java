package com.oracle.ssm.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.oracle.ssm.model.Goods;
import com.oracle.ssm.model.User;
import com.oracle.ssm.service.GoodsService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Autowired 
	private RedisTemplate jedis;
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * �����Ʒ�����ﳵ
	 * @throws Exception 
	 */
	@RequestMapping("/addShop")
	public void addShap(int goodsId,HttpServletRequest request,HttpServletResponse response) throws Exception {
		/**
		 * ���ﳵ���߼�
		 * ���ڵ�¼�û���˵��
		 * ���ﳵ���ݣ�����ʹ�÷���������д洢��
		 * �����ο���˵���ͻ����Լ����洢.
		 * ��¼�û���ʹ��redis���洢   k: �û�id   v List<goods> ���л�
		 * �ο��û���ʹ��cookie    ֻ�ܴ洢�ַ���  key  value �ַ��������ܰ������Ŀո�
		 * JS ������һ��sessionStorage 
		 * 
		 * 
		 * 
		 */
		//
		boolean isLogin=jedis.hasKey("session:"+request.getRemoteAddr());
		if(isLogin==false) {
			//ʹ��cookie�����д洢
			//ȡ��������Я����cookie
			Cookie[] cookies=request.getCookies();
			
			Cookie shopCookie=null;
			for(Cookie cookie:cookies) {
				//�ж��û�֮ǰ��û�д洢�����ﳵ
				if(cookie.getName().equals("shop")) {
				
					shopCookie=cookie;
					break;
				}
			}
			if(shopCookie==null) {
				//�û���һ����Ҫ�湺�ﳵ
			    //key shop   value  �ַ���   json
				Goods goods=goodsService.findGoodsById(goodsId);
				List<Goods> goodsList=new ArrayList();
				goodsList.add(goods);
				//����Ʒ����ת��json
				String json=JSON.toJSONString(goodsList);
				//cookie�����ǲ�׼��������Ļ������ַ���
				String key="shop";
				String value=URLEncoder.encode(json, "utf-8");
				Cookie cookie=new Cookie(key,value);
				//����cookie����������
				cookie.setMaxAge(60*60*24);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
				response.getWriter().write("1");
				
			}else {
				//�û�֮ǰ���ﳵ���ж�����
				String json=shopCookie.getValue();
				json=URLDecoder.decode(json, "utf-8");
				List<Goods> goodsList=JSON.parseArray(json, Goods.class);
				boolean isExist=false;
				for(Goods goods:goodsList) {
					if(goods.getId()==goodsId) {
						isExist=true;
						break;
					}
				}
				if(isExist) {
					response.getWriter().write("2");
				}else {
					Goods goods=goodsService.findGoodsById(goodsId);
					goodsList.add(goods);
					//����Ʒ����ת��json
					String json1=JSON.toJSONString(goodsList);
					//cookie�����ǲ�׼��������Ļ������ַ���
					String key="shop";
					String value=URLEncoder.encode(json1, "utf-8");
					Cookie cookie=new Cookie(key,value);
					//����cookie����������
					cookie.setMaxAge(60*60*24);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					response.getWriter().write("1");
					
				}
			}
			
			 
		}else {
			/**�û��Ѿ���¼����
			 * redisʵ��
			 * key   shop:+�û�id
			 * value  ���л���byte[]
			 */
			//���ж��ǲ��ǵ�һ�δ湺�ﳵ
			byte[] bytes=(byte[]) jedis.boundValueOps("session:"+request.getRemoteAddr()).get();
			User user=(User) jedis.getHashValueSerializer().deserialize(bytes);
			boolean check=jedis.hasKey("shop:"+user.getId());
			if(check) {
				byte[] data=(byte[]) jedis.boundValueOps("shop:"+user.getId()).get();
				List<Goods> goodsList=(List<Goods>) jedis.getHashValueSerializer().deserialize(data);
				boolean isGoods=false;
				for(Goods goods:goodsList) {
					if(goods.getId()==goodsId) {
						isGoods=true;
						break;
					}
				}
				if(isGoods) {
					response.getWriter().write("2");
				}else {
					Goods goods=goodsService.findGoodsById(goodsId);
					goodsList.add(goods);
					byte[] data1=jedis.getHashValueSerializer().serialize(goodsList);
					jedis.boundValueOps("shop:"+user.getId()).set(data1);
					response.getWriter().write("1");
					
				}
				
				
				
				
			}else {
				//�û���һ����Ҫ�湺�ﳵ
				Goods goods=goodsService.findGoodsById(goodsId);
				List<Goods> goodsList=new ArrayList();
				goodsList.add(goods);
				byte[] data=jedis.getHashValueSerializer().serialize(goodsList);
				jedis.boundValueOps("shop:"+user.getId()).set(data);
				response.getWriter().write("1");
			}
			
			
			
		}
		
	
	}
	
	
	
	@RequestMapping("/shopList")
	public String shopList(HttpServletRequest request) throws Exception {
		/**
		 * ��¼û��½��
		 * û��¼ȥcookie����ȡ
		 * ��½��ȥredis��ȡ
		 */
		boolean isLogin=jedis.hasKey("session:"+request.getRemoteAddr());
		if(isLogin) {
			byte[] bytes=(byte[]) jedis.boundValueOps("session:"+request.getRemoteAddr()).get();
			User user=(User) jedis.getHashValueSerializer().deserialize(bytes);
			boolean check=jedis.hasKey("shop:"+user.getId());
			if(check) {
				
				byte[] data=(byte[]) jedis.boundValueOps("shop:"+user.getId()).get();
				List<Goods> goodsList=(List<Goods>) jedis.getHashValueSerializer().deserialize(data);
				
				request.setAttribute("list", goodsList);
			}
		}else {
			//û��¼��
			Cookie[] cookies=request.getCookies();
			Cookie shopCookie=null;
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("shop")) {
					shopCookie=cookie;
					break;
				}
			}
			if(shopCookie!=null) {
				String value=shopCookie.getValue();
				String json=URLDecoder.decode(value, "utf-8");
				List<Goods> list=JSON.parseArray(json, Goods.class);
				request.setAttribute("list", list);
			}
		}
		return "index/carShop";
	}
	
	
	

}
