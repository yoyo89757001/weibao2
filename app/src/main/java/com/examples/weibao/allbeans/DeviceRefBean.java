package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/3/21.
 */
@Entity
public class DeviceRefBean {

    /**
     * createTime : 1521617996148
     * deviceId : 584
     * dtoResult : 1
     * id : 18
     * isDetection : 0
     * modifyTime : 1521617996148
     * pageNum : 0
     * pageSize : 0
     * planId : 10053
     * weibaoMenuId : 435
     */

    private long createTime;
    private int deviceId;
    private int dtoResult;
    @Id
    @NotNull
    private Long id;
    private int isDetection;
    private long modifyTime;
    private int pageNum;
    private int pageSize;
    private int planId;
    private int weibaoMenuId;
    @Generated(hash = 1659871896)
    public DeviceRefBean(long createTime, int deviceId, int dtoResult,
            @NotNull Long id, int isDetection, long modifyTime, int pageNum,
            int pageSize, int planId, int weibaoMenuId) {
        this.createTime = createTime;
        this.deviceId = deviceId;
        this.dtoResult = dtoResult;
        this.id = id;
        this.isDetection = isDetection;
        this.modifyTime = modifyTime;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.planId = planId;
        this.weibaoMenuId = weibaoMenuId;
    }
    @Generated(hash = 1910796816)
    public DeviceRefBean() {
    }
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public int getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    public int getDtoResult() {
        return this.dtoResult;
    }
    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getIsDetection() {
        return this.isDetection;
    }
    public void setIsDetection(int isDetection) {
        this.isDetection = isDetection;
    }
    public long getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }
    public int getPageNum() {
        return this.pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return this.pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPlanId() {
        return this.planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public int getWeibaoMenuId() {
        return this.weibaoMenuId;
    }
    public void setWeibaoMenuId(int weibaoMenuId) {
        this.weibaoMenuId = weibaoMenuId;
    }

    @Override
    public String toString() {
        return "DeviceRefBean{" +
                "isDetection=" + isDetection +
                '}';
    }
}
