package com.siruiman.crosslogistics.model;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/3/1 18:08
 */
public class NetsReqModel {
    private String apiKey;

    private String mac;

    private String txnReq;


    public NetsReqModel() {
        super();
    }

    @Override
    public String toString() {
        return "NetsReqModel{" +
                "apiKey='" + apiKey + '\'' +
                ", mac='" + mac + '\'' +
                ", txnReq='" + txnReq + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetsReqModel that = (NetsReqModel) o;
        return Objects.equals(apiKey, that.apiKey) &&
                Objects.equals(mac, that.mac) &&
                Objects.equals(txnReq, that.txnReq);
    }

    @Override
    public int hashCode() {

        return Objects.hash(apiKey, mac, txnReq);
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getTxnReq() {
        return txnReq;
    }

    public void setTxnReq(String txnReq) {
        this.txnReq = txnReq;
    }
}

