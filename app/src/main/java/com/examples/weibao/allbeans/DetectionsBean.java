package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/31.
 */
@Entity
public class DetectionsBean {

    private long createTime;
    private int detection;
    private int dtoResult;
    @Id
    @NotNull
    private Long id;
    private long modifyTime;
    private int pageNum;
    private int pageSize;
    private String remark;
    private int weibaoMenuId;
    @Generated(hash = 479653032)
    public DetectionsBean(long createTime, int detection, int dtoResult,
            @NotNull Long id, long modifyTime, int pageNum, int pageSize,
            String remark, int weibaoMenuId) {
        this.createTime = createTime;
        this.detection = detection;
        this.dtoResult = dtoResult;
        this.id = id;
        this.modifyTime = modifyTime;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.remark = remark;
        this.weibaoMenuId = weibaoMenuId;
    }
    @Generated(hash = 81043678)
    public DetectionsBean() {
    }
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public int getDetection() {
        return this.detection;
    }
    public void setDetection(int detection) {
        this.detection = detection;
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
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getWeibaoMenuId() {
        return this.weibaoMenuId;
    }
    public void setWeibaoMenuId(int weibaoMenuId) {
        this.weibaoMenuId = weibaoMenuId;
    }

}
