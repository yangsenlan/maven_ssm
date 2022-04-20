package com.oracle.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.ssm.mapper.GoodsMapper;
import com.oracle.ssm.model.Goods;
import com.oracle.ssm.service.GoodsService;
import com.oracle.ssm.vo.GoodsQueryVo;
import com.oracle.ssm.vo.GoodsShowVo;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<GoodsShowVo> findGoodsByQueryVo(GoodsQueryVo vo) {
		// TODO Auto-generated method stub
		return goodsMapper.findGoodsByQueryVo( vo);
	}

	@Override
	public Goods findGoodsById(int goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.selectByPrimaryKey(goodsId);
	}
	
	

}
