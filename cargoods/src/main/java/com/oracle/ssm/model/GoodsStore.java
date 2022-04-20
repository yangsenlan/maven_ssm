package com.oracle.ssm.model;

public class GoodsStore {
    private Integer goodsId;

    private Integer totalCount;

    private Integer avaliableCount;

    private Integer freezeCount;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAvaliableCount() {
        return avaliableCount;
    }

    public void setAvaliableCount(Integer avaliableCount) {
        this.avaliableCount = avaliableCount;
    }

    public Integer getFreezeCount() {
        return freezeCount;
    }

    public void setFreezeCount(Integer freezeCount) {
        this.freezeCount = freezeCount;
    }
}