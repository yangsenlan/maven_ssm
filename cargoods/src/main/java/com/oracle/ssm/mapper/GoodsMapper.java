package com.oracle.ssm.mapper;

import java.util.List;

import com.oracle.ssm.model.Goods;
import com.oracle.ssm.vo.GoodsQueryVo;
import com.oracle.ssm.vo.GoodsShowVo;
import com.oracle.ssm.vo.GoodsVo;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

	List<GoodsShowVo> findGoodsByQueryVo(GoodsQueryVo vo);
}