package com.examples.weibao.beans;

import org.parceler.Parcel;

/**
 * Created by Administrator on 2017/11/16.
 */
@Parcel
public class WeiBaoCeShiCSBean {


    /**
     * id : 0
     * planId : 0
     * menuLevel1Id : 0
     * menuId : 0
     * menuLevel3Id : 0
     * menuLevel4Id : 0
     * deviceId : 0
     * remark : 维保结果描述
     * testData : 测试数据
     * createBy : 0
     * createTime : 1509788606570
     */

    public int id;
    public int planId;
    public int menuLevel1Id;
    public int menuId;
    public int menuLevel3Id;
    public int menuLevel4Id;
    public int deviceId;
    public String remark;
    public String testData;
    public int createBy;
    public long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getMenuLevel1Id() {
        return menuLevel1Id;
    }

    public void setMenuLevel1Id(int menuLevel1Id) {
        this.menuLevel1Id = menuLevel1Id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMenuLevel3Id() {
        return menuLevel3Id;
    }

    public void setMenuLevel3Id(int menuLevel3Id) {
        this.menuLevel3Id = menuLevel3Id;
    }

    public int getMenuLevel4Id() {
        return menuLevel4Id;
    }

    public void setMenuLevel4Id(int menuLevel4Id) {
        this.menuLevel4Id = menuLevel4Id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
