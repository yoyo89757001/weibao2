package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/10.
 */
@Entity
public class BenDiMenusBean {
    @Id
    @NotNull
    private Long id;
    private boolean isYiChang;
    private long mensuId;
    private long parentId;
    private String name;
    private boolean isQiTa;
    private int planId;
    private int menuLevel1Id;
    private int menuId2;
    private int menuLevel3Id;
    private int menuLevel4Id;
    private int deviceId;
    private String remark;
    private String testData;
    private int createBy;
    private long createTime;
    private boolean isTijiao;
    @Generated(hash = 251480049)
    public BenDiMenusBean(@NotNull Long id, boolean isYiChang, long mensuId,
            long parentId, String name, boolean isQiTa, int planId,
            int menuLevel1Id, int menuId2, int menuLevel3Id, int menuLevel4Id,
            int deviceId, String remark, String testData, int createBy,
            long createTime, boolean isTijiao) {
        this.id = id;
        this.isYiChang = isYiChang;
        this.mensuId = mensuId;
        this.parentId = parentId;
        this.name = name;
        this.isQiTa = isQiTa;
        this.planId = planId;
        this.menuLevel1Id = menuLevel1Id;
        this.menuId2 = menuId2;
        this.menuLevel3Id = menuLevel3Id;
        this.menuLevel4Id = menuLevel4Id;
        this.deviceId = deviceId;
        this.remark = remark;
        this.testData = testData;
        this.createBy = createBy;
        this.createTime = createTime;
        this.isTijiao = isTijiao;
    }
    @Generated(hash = 670714680)
    public BenDiMenusBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getIsYiChang() {
        return this.isYiChang;
    }
    public void setIsYiChang(boolean isYiChang) {
        this.isYiChang = isYiChang;
    }
    public long getMensuId() {
        return this.mensuId;
    }
    public void setMensuId(long mensuId) {
        this.mensuId = mensuId;
    }
    public long getParentId() {
        return this.parentId;
    }
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsQiTa() {
        return this.isQiTa;
    }
    public void setIsQiTa(boolean isQiTa) {
        this.isQiTa = isQiTa;
    }
    public int getPlanId() {
        return this.planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public int getMenuLevel1Id() {
        return this.menuLevel1Id;
    }
    public void setMenuLevel1Id(int menuLevel1Id) {
        this.menuLevel1Id = menuLevel1Id;
    }
    public int getMenuId2() {
        return this.menuId2;
    }
    public void setMenuId2(int menuId2) {
        this.menuId2 = menuId2;
    }
    public int getMenuLevel3Id() {
        return this.menuLevel3Id;
    }
    public void setMenuLevel3Id(int menuLevel3Id) {
        this.menuLevel3Id = menuLevel3Id;
    }
    public int getMenuLevel4Id() {
        return this.menuLevel4Id;
    }
    public void setMenuLevel4Id(int menuLevel4Id) {
        this.menuLevel4Id = menuLevel4Id;
    }
    public int getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getTestData() {
        return this.testData;
    }
    public void setTestData(String testData) {
        this.testData = testData;
    }
    public int getCreateBy() {
        return this.createBy;
    }
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public boolean getIsTijiao() {
        return this.isTijiao;
    }
    public void setIsTijiao(boolean isTijiao) {
        this.isTijiao = isTijiao;
    }


   


}
