package com.siruiman.crosslogistics.model;

/**
 * @author 张占伟
 * @date 2019/3/27 16:41
 */
public class GoodsPrintFormMid {
    private int goodsId;

    private int formId;

    public GoodsPrintFormMid() {
        super();
    }

    @Override
    public String toString() {
        return "GoodsPrintFormMid{" +
                "goodsId=" + goodsId +
                ", formId=" + formId +
                '}';
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }
}
