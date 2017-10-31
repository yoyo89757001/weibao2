package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/31.
 */
@Entity
public class PlansBean {

    private String area;
    private int auditBy;
    private int createBy;
    private long createTime;
    private int dtoResult;
    @Id
    @NotNull
    private Long id;
    private int itemId;
    private long modifyTime;
    private int pageNum;
    private int pageSize;
    private int planMonth;
    private int status;
    @Generated(hash = 1839292584)
    public PlansBean(String area, int auditBy, int createBy, long createTime,
            int dtoResult, @NotNull Long id, int itemId, long modifyTime,
            int pageNum, int pageSize, int planMonth, int status) {
        this.area = area;
        this.auditBy = auditBy;
        this.createBy = createBy;
        this.createTime = createTime;
        this.dtoResult = dtoResult;
        this.id = id;
        this.itemId = itemId;
        this.modifyTime = modifyTime;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.planMonth = planMonth;
        this.status = status;
    }
    @Generated(hash = 1413172819)
    public PlansBean() {
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
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public int getPlanMonth() {
        return this.planMonth;
    }
    public void setPlanMonth(int planMonth) {
        this.planMonth = planMonth;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
