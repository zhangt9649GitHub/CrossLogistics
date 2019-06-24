package com.siruiman.crosslogistics.model;

/**
 * @author 张占伟
 * @date 2019/3/27 16:03
 */
public class GoodsPrintForm {

    private int formId;

    private String formNumber;

    private String driverName;

    private int printState;

    private String addTime;

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public GoodsPrintForm() {
        super();
    }

    @Override
    public String toString() {
        return "PrintGoods{" +
                "formId=" + formId +
                ", formNumber='" + formNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                ", printState=" + printState +
                '}';
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getPrintState() {
        return printState;
    }

    public void setPrintState(int printState) {
        this.printState = printState;
    }
}
