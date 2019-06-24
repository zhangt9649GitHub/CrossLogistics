package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/3/1 9:21
 */
@ApiModel(description = "张占伟")
public class WaringMessage {

//    private List<String> carMaintenances;

    private List<String> goodsWaringMessage;

    public List<String> getGoodsWaringMessage() {
        return goodsWaringMessage;
    }

    public WaringMessage() {
        super();
    }

    @Override
    public String toString() {
        return "WaringMessage{" +
                "goodsWaringMessage=" + goodsWaringMessage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaringMessage that = (WaringMessage) o;
        return Objects.equals(goodsWaringMessage, that.goodsWaringMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsWaringMessage);
    }

    public void setGoodsWaringMessage(List<String> goodsWaringMessage) {
        this.goodsWaringMessage = goodsWaringMessage;
    }
}
