package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/31.
 */
@Entity
public class MenusBean {

    private int createBy;
    private long createTime;
    private int dtoResult;
    @Id
    @NotNull
    private Long id;
    private int level;
    private int modifyBy;
    private long modifyTime;
    private String name;
    private int pageNum;
    private int pageSize;
    private int parentId;
    private String serialNumber;
    private int sort;
    private int status;
    private int type;
    @Generated(hash = 6960457)
    public MenusBean(int createBy, long createTime, int dtoResult, @NotNull Long id,
            int level, int modifyBy, long modifyTime, String name, int pageNum,
            int pageSize, int parentId, String serialNumber, int sort, int status,
            int type) {
        this.createBy = createBy;
        this.createTime = createTime;
        this.dtoResult = dtoResult;
        this.id = id;
        this.level = level;
        this.modifyBy = modifyBy;
        this.modifyTime = modifyTime;
        this.name = name;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.parentId = parentId;
        this.serialNumber = serialNumber;
        this.sort = sort;
        this.status = status;
        this.type = type;
    }
    @Generated(hash = 265781856)
    public MenusBean() {
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
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getModifyBy() {
        return this.modifyBy;
    }
    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
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
    public int getParentId() {
        return this.parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    public String getSerialNumber() {
        return this.serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }


}
