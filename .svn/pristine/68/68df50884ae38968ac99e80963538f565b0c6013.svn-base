package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author 张占伟
 * @date 2019/3/14 13:44
 * 传参使用
 */
public class CarLockDto {

    @ApiModelProperty(value="锁编号", example = "lockNumber")
    private String lockNumber;

    @ApiModelProperty(value="锁的位置 1.小车车厢锁  2.小车锁", example = "1")
    private Integer lockPosition;

    @ApiModelProperty(value="锁的位置 1.小车车厢锁  2.小车锁", example = "1")
    private Integer lockId;

    public Integer getLockId() {
        return lockId;
    }

    public void setLockId(Integer lockId) {
        this.lockId = lockId;
    }

    public CarLockDto() {
        super();
    }

    @Override
    public String toString() {
        return "CarLockDto{" +
                "lockNumber='" + lockNumber + '\'' +
                ", lockPosition=" + lockPosition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarLockDto that = (CarLockDto) o;
        return Objects.equals(lockNumber, that.lockNumber) &&
                Objects.equals(lockPosition, that.lockPosition);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lockNumber, lockPosition);
    }

    public String getLockNumber() {
        return lockNumber;
    }

    public void setLockNumber(String lockNumber) {
        this.lockNumber = lockNumber;
    }

    public Integer getLockPosition() {
        return lockPosition;
    }

    public void setLockPosition(Integer lockPosition) {
        this.lockPosition = lockPosition;
    }

    public CarLockDto(String lockNumber, Integer lockPosition) {
        this.lockNumber = lockNumber;
        this.lockPosition = lockPosition;
    }
}
