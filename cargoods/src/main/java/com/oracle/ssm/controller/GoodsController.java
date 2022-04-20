package com.oracle.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.ssm.model.Goods;
import com.oracle.ssm.model.User;
import com.oracle.ssm.service.GoodsService;
import com.oracle.ssm.vo.GoodsQueryVo;
import com.oracle.ssm.vo.GoodsShowVo;
import com.oracle.ssm.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private RedisTemplate jedis;
	
	
	@RequestMapping("/goodsList")
	public String goodsList(GoodsQueryVo vo,Integer pageNo,Integer pageSize,HttpServletRequest request) {
		//��ʼ����ҳ����
		pageNo=pageNo==null?1:pageNo;
		pageSize=pageSize==null?10:pageSize;
		//��ô��ҳ? select * from goods where ..... limit index,pagesize 
		//select count(*) from goods where....
		//mybatis�ṩ�˷�ҳ������ײ�ԭ����ͨ����̬�����aop��ʵ�ֵ�
		PageHelper.startPage(pageNo, pageSize);
		//ֻ��Ҫдһ����ѯȫ����sql�Ϳ�����  select * from goods where ...
		List<GoodsShowVo> goodsList=goodsService.findGoodsByQueryVo(vo);
		for (GoodsShowVo good : goodsList) {
			System.out.println(good);
		}
		//ʹ��pageInfo���󣬷�װһ����������sql
		PageInfo<GoodsShowVo> page=new PageInfo<GoodsShowVo>(goodsList);
		//page����ײ����ж�̬����ȥ�������β�ѯ
		request.setAttribute("page", page);
		byte[] bytes=(byte[]) jedis.boundValueOps("session:"+request.getRemoteAddr()).get();
		User user=(User)jedis.getHashValueSerializer().deserialize(bytes);
		//�����ѯ����
		request.setAttribute("vo", vo);
		request.setAttribute("user", user);
		
		return "index/index";
	}

}
