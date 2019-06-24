package com.siruiman.crosslogistics.enums;


public enum ZwCode {
    SUCCESS_GET(30001, "获取成功"),
    FAIL_GET(30002, "获取失败"),
    SUCCESS_EDIT(30003,"修改成功"),
    FAIL_EDIT(30004,"修改失败"),
    SUCCESS_INSERT(30005,"添加成功"),
    FAIL_INSERT(30006,"添加失败"),
    FAIL_DELETE(30008,"删除失败"),
    SUCCESS_DELETE(30007,"删除成功"),
    SUCCESS_PACKAGING(30009,"打包成功"),
    FAIL_PACKAGING(30010,"打包失败"),
    SUCCESS_CREATBAG(30011,"获取货袋成功"),
    FAIL_CREATBAG(30012,"获取货袋失败"),
    SUCCESS_OUTBOUND(30013,"货袋出库成功"),
    FAIL_OUTBOUND(30014,"货袋出库失败"),
    SUCCESS_RECEIVE(30015,"货袋接收成功"),
    FAIL_RECEIVE(30016,"货袋接收失败"),
    CAN_USERNAME(30017,"恭喜您,该区域名字可以使用"),
    UNABLE_CHECKNAME(30018,"该区域名字已被使用"),
    FAIL_CHECKNAME(30020,"验重失败"),
    SUCCESS_STORAGE(30021,"入库成功"),
    FAIL_STORAGE(30022,"入库失败"),
    ERROR_PAY(30023,"出现未知错误,请稍后重试"),
    ERROR_GOODS_PACKGING(30024,"Error,货物已经打包"),
    ERROR_BAGOUTBOUNDSG(30025,"Error,货袋已经装车出库"),
    SUCCESS_SEND_OUT_BAG(30026,"货袋送出成功"),
    FAIL_SEND_OUT_BAG(30027,"货袋送出失败"),
    ERROR_SEND_OUT_BAG(30028,"Error,货袋已送出"),
    ERROR_LOGIN_NAME_PASSWORD_NULL(30029,"Error,用户名或者密码不能为空"),
    ERROR_LOGIN_NAME_EXCIST(30030,"Error,用户名不存在"),
    ERROR_NAME_PASSWORD_EXCIST(30031,"Error,用户名或者密码错误"),
    SUCCESS_GET_WX_PAY(30032,"微信支付下单成功"),
    FAIL_GET_WX_PAY(30033,"微信支付下单失败"),
    ERROR_BAG_PUT_IN_STORAGE(30034,"Error,货袋已经入库"),
    WARNING_USER_NOT_ORDER(30035,"WARNING,当前用户没有货车订单"),
    ERROR_BAG_NOT_EXIT(30036,"当前货袋不存在"),
    SUCCESS_PAYMENT(30037,"支付成功"),
    NOT_PAYMENT(30038,"未支付"),
    ERROR_BAG_OPERATION(30039,"请进行正确的货袋操作"),
    ERROR_BAG_ID(30040,"请扫描正确的货袋"),
    NO_BAGS_IN_ORDER(30041,"当前用户所抢订单无货袋"),
    NO_ABNORMAL_INFORMATION(30042,"没有异常信息"),
    ABNORMAL_INFORMATION(30043,"有异常需要处理的信息"),
    WARNING_LOCK(30044,"车锁只有在空闲状态才可编辑"),
    WARNIng_LOCK_ID(30045,"车锁不存在"),
    WARNING_CAR_LOCK(30046,"小车配送中,不可报废小车"),
    CAN_USE_NUM(30047,"该编号可以使用"),
    UNABLE_CHECK_NUM(30048,"该编号锁已经录入,请不要重复录入"),
    CAN_USE_CODE(30049,"该邮编可以使用"),
    UNABLE_CHECK_CODE(30050,"该邮编,已添加"),
    ERROR_DELIVERY_NUM(30051,"请填写编号"),
    WARNING_DELIVERY_NUM(30052,"暂无该订单物流信息"),
    WARNING_USER_TYPE(30053,"当前用户类型使用中,无法删除"),
    WARNING_TRIPARTITE_USER(30062,"当前第三方公司已经存在账号,只能同时存在一个账号"),
    WARNING_ADMIN_USER_TYPE(30054,"系统管理员类型无法删除"),
    CAN_USE_USER_TYPE(30055,"当前用户类型名字未重复,可以使用"),
    UNABLE_USE_USER_TYPE(30056,"当前用户类型名称已存在"),
    SUCCESS_BAG_DIRECT(30057,"货袋出库直接配送成功"),
    FAIL_BAG_DIRECT(30058,"货袋出库直接配送失败"),
    SUCCESS_GOODS_DIRECT(30059,"货物出库直接配送成功"),
    FAIL_GOODS_DIRECT(30060,"货物出库直接配送失败"),
    ERROR_QR_CODE(30061,"请扫描正确的二维码"),
    WARNING_GOODS_BAG(30067,"警告!此货袋里有异常件,或货物为异常件"),
    WARNING_NOT_USER(30068,"当前司机不是平台签约司机");
    private int code;
    private String info;

    ZwCode(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
