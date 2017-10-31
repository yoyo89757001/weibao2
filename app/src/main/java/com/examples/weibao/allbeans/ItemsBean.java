package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/31.
 */
@Entity
public class ItemsBean {

    private String address;
    private String area;
    private int auditBy;
    private int buildingHeight;
    private int createBy;
    private long createTime;
    private int dtoResult;
    private long failureDate;
    private int floor;
    @Id
    @NotNull
    private Long id;
    private int itemAmount;
    private int itemDuty;
    private int itemId;
    private int maxId;
    private long modifyTime;
    private String name;
    private int nature;
    private int pageNum;
    private int pageSize;
    private int safetyDuty;
    private int safetyManager;
    private int status;
    private int taxInclusive;
    private long validDate;
    private int weibaoCompanyId;
    private int weibaoMan;
    private int weibaoMenuId;
    private String weibaoTel;
    private int weituoCompanyId;
    @Generated(hash = 121700767)
    public ItemsBean(String address, String area, int auditBy, int buildingHeight,
            int createBy, long createTime, int dtoResult, long failureDate,
            int floor, @NotNull Long id, int itemAmount, int itemDuty, int itemId,
            int maxId, long modifyTime, String name, int nature, int pageNum,
            int pageSize, int safetyDuty, int safetyManager, int status,
            int taxInclusive, long validDate, int weibaoCompanyId, int weibaoMan,
            int weibaoMenuId, String weibaoTel, int weituoCompanyId) {
        this.address = address;
        this.area = area;
        this.auditBy = auditBy;
        this.buildingHeight = buildingHeight;
        this.createBy = createBy;
        this.createTime = createTime;
        this.dtoResult = dtoResult;
        this.failureDate = failureDate;
        this.floor = floor;
        this.id = id;
        this.itemAmount = itemAmount;
        this.itemDuty = itemDuty;
        this.itemId = itemId;
        this.maxId = maxId;
        this.modifyTime = modifyTime;
        this.name = name;
        this.nature = nature;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.safetyDuty = safetyDuty;
        this.safetyManager = safetyManager;
        this.status = status;
        this.taxInclusive = taxInclusive;
        this.validDate = validDate;
        this.weibaoCompanyId = weibaoCompanyId;
        this.weibaoMan = weibaoMan;
        this.weibaoMenuId = weibaoMenuId;
        this.weibaoTel = weibaoTel;
        this.weituoCompanyId = weituoCompanyId;
    }
    @Generated(hash = 1126760319)
    public ItemsBean() {
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getArea() {
        return this.area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public int getAuditBy() {
        return this.auditBy;
    }
    public void setAuditBy(int auditBy) {
        this.auditBy = auditBy;
    }
    public int getBuildingHeight() {
        return this.buildingHeight;
    }
    public void setBuildingHeight(int buildingHeight) {
        this.buildingHeight = buildingHeight;
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
    public int getDtoResult() {
        return this.dtoResult;
    }
    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }
    public long getFailureDate() {
        return this.failureDate;
    }
    public void setFailureDate(long failureDate) {
        this.failureDate = failureDate;
    }
    public int getFloor() {
        return this.floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getItemAmount() {
        return this.itemAmount;
    }
    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }
    public int getItemDuty() {
        return this.itemDuty;
    }
    public void setItemDuty(int itemDuty) {
        this.itemDuty = itemDuty;
    }
    public int getItemId() {
        return this.itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getMaxId() {
        return this.maxId;
    }
    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }
    public long getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNature() {
        return this.nature;
    }
    public void setNature(int nature) {
        this.nature = nature;
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
    public int getSafetyDuty() {
        return this.safetyDuty;
    }
    public void setSafetyDuty(int safetyDuty) {
        this.safetyDuty = safetyDuty;
    }
    public int getSafetyManager() {
        return this.safetyManager;
    }
    public void setSafetyManager(int safetyManager) {
        this.safetyManager = safetyManager;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getTaxInclusive() {
        return this.taxInclusive;
    }
    public void setTaxInclusive(int taxInclusive) {
        this.taxInclusive = taxInclusive;
    }
    public long getValidDate() {
        return this.validDate;
    }
    public void setValidDate(long validDate) {
        this.validDate = validDate;
    }
    public int getWeibaoCompanyId() {
        return this.weibaoCompanyId;
    }
    public void setWeibaoCompanyId(int weibaoCompanyId) {
        this.weibaoCompanyId = weibaoCompanyId;
    }
    public int getWeibaoMan() {
        return this.weibaoMan;
    }
    public void setWeibaoMan(int weibaoMan) {
        this.weibaoMan = weibaoMan;
    }
    public int getWeibaoMenuId() {
        return this.weibaoMenuId;
    }
    public void setWeibaoMenuId(int weibaoMenuId) {
        this.weibaoMenuId = weibaoMenuId;
    }
    public String getWeibaoTel() {
        return this.weibaoTel;
    }
    public void setWeibaoTel(String weibaoTel) {
        this.weibaoTel = weibaoTel;
    }
    public int getWeituoCompanyId() {
        return this.weituoCompanyId;
    }
    public void setWeituoCompanyId(int weituoCompanyId) {
        this.weituoCompanyId = weituoCompanyId;
    }
}
