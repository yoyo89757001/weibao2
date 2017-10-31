package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/31.
 */
@Entity
public class DevicesBean {

    private String area;
    private int auditBy;
    private int checkPeriod;
    private String city;
    private int createBy;
    private String deviceNum;
    private int dtoResult;
    private long failureTime;

    @Id
    @NotNull
   private Long id;
    private int isDetection;
    private int itemId;
    private long modifyTime;
    private String name;
    private int pageNum;
    private int pageSize;
    private String province;
    private int status;
    private String subArea;
    private String unit;
    private long validTime;
    private String weibaoSubSystemId;
    private String weibaoSystemId;
    @Generated(hash = 2008661259)
    public DevicesBean(String area, int auditBy, int checkPeriod, String city,
            int createBy, String deviceNum, int dtoResult, long failureTime,
            @NotNull Long id, int isDetection, int itemId, long modifyTime,
            String name, int pageNum, int pageSize, String province, int status,
            String subArea, String unit, long validTime, String weibaoSubSystemId,
            String weibaoSystemId) {
        this.area = area;
        this.auditBy = auditBy;
        this.checkPeriod = checkPeriod;
        this.city = city;
        this.createBy = createBy;
        this.deviceNum = deviceNum;
        this.dtoResult = dtoResult;
        this.failureTime = failureTime;
        this.id = id;
        this.isDetection = isDetection;
        this.itemId = itemId;
        this.modifyTime = modifyTime;
        this.name = name;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.province = province;
        this.status = status;
        this.subArea = subArea;
        this.unit = unit;
        this.validTime = validTime;
        this.weibaoSubSystemId = weibaoSubSystemId;
        this.weibaoSystemId = weibaoSystemId;
    }
    @Generated(hash = 1295633009)
    public DevicesBean() {
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
    public int getCheckPeriod() {
        return this.checkPeriod;
    }
    public void setCheckPeriod(int checkPeriod) {
        this.checkPeriod = checkPeriod;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getCreateBy() {
        return this.createBy;
    }
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    public String getDeviceNum() {
        return this.deviceNum;
    }
    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }
    public int getDtoResult() {
        return this.dtoResult;
    }
    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }
    public long getFailureTime() {
        return this.failureTime;
    }
    public void setFailureTime(long failureTime) {
        this.failureTime = failureTime;
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
    public int getItemId() {
        return this.itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
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
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getSubArea() {
        return this.subArea;
    }
    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public long getValidTime() {
        return this.validTime;
    }
    public void setValidTime(long validTime) {
        this.validTime = validTime;
    }
    public String getWeibaoSubSystemId() {
        return this.weibaoSubSystemId;
    }
    public void setWeibaoSubSystemId(String weibaoSubSystemId) {
        this.weibaoSubSystemId = weibaoSubSystemId;
    }
    public String getWeibaoSystemId() {
        return this.weibaoSystemId;
    }
    public void setWeibaoSystemId(String weibaoSystemId) {
        this.weibaoSystemId = weibaoSystemId;
    }
}
