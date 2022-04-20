package com.oracle.ssm.service;

import java.util.List;

import com.oracle.ssm.model.Goods;
import com.oracle.ssm.vo.GoodsQueryVo;
import com.oracle.ssm.vo.GoodsShowVo;

public interface GoodsService {

	List<GoodsShowVo> findGoodsByQueryVo(GoodsQueryVo vo);

	Goods findGoodsById(int goodsId);

}
