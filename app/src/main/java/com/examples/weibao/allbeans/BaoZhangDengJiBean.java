package com.examples.weibao.allbeans;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/6.
 */

@Entity
public class BaoZhangDengJiBean {
    @Id
    @NotNull
    private Long id;


    /**
     * status : 0
     * id : 1
     * accountId : 0
     * address : 111
     * companyId : 10001
     * deviceId : 1
     * deviceNumber : 123
     * faultTime : 1509787543000
     * remark : 111
     * contactTel : 133123456789
     * faultImage : 1234567890.jpg
     * replyBy : 0
     * replyContent : =========
     * replyUsername : 用户名称
     * replyTime : 1509788606570
     * planCheckTime : 1509788606570
     * processBy : 0
     * processContent : ==========
     * processUser : 用户名称
     * processTime : 1509788606570
     * modifyTime : 1509788606570
     * dtoResult : 1
     * pageNum : 0
     * pageSize : 0
     */

    private int status;
    private int accountId;
    private String address;
    private int companyId;
    private int deviceId;
    private String deviceNumber;
    private long faultTime;
    private String remark;
    private String contactTel;
    private String faultImage;
    private int replyBy;
    private String replyContent;
    private String replyUsername;
    private long replyTime;
    private long planCheckTime;
    private int processBy;
    private String processContent;
    private String processUser;
    private long processTime;
    private long modifyTime;
    private int dtoResult;
    private int pageNum;
    private int pageSize;
    private boolean isTijiao;
    @Generated(hash = 871956218)
    public BaoZhangDengJiBean(@NotNull Long id, int status, int accountId,
            String address, int companyId, int deviceId, String deviceNumber,
            long faultTime, String remark, String contactTel, String faultImage,
            int replyBy, String replyContent, String replyUsername, long replyTime,
            long planCheckTime, int processBy, String processContent,
            String processUser, long processTime, long modifyTime, int dtoResult,
            int pageNum, int pageSize, boolean isTijiao) {
        this.id = id;
        this.status = status;
        this.accountId = accountId;
        this.address = address;
        this.companyId = companyId;
        this.deviceId = deviceId;
        this.deviceNumber = deviceNumber;
        this.faultTime = faultTime;
        this.remark = remark;
        this.contactTel = contactTel;
        this.faultImage = faultImage;
        this.replyBy = replyBy;
        this.replyContent = replyContent;
        this.replyUsername = replyUsername;
        this.replyTime = replyTime;
        this.planCheckTime = planCheckTime;
        this.processBy = processBy;
        this.processContent = processContent;
        this.processUser = processUser;
        this.processTime = processTime;
        this.modifyTime = modifyTime;
        this.dtoResult = dtoResult;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.isTijiao = isTijiao;
    }
    @Generated(hash = 334163897)
    public BaoZhangDengJiBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getAccountId() {
        return this.accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getCompanyId() {
        return this.companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public int getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceNumber() {
        return this.deviceNumber;
    }
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }
    public long getFaultTime() {
        return this.faultTime;
    }
    public void setFaultTime(long faultTime) {
        this.faultTime = faultTime;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getContactTel() {
        return this.contactTel;
    }
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }
    public String getFaultImage() {
        return this.faultImage;
    }
    public void setFaultImage(String faultImage) {
        this.faultImage = faultImage;
    }
    public int getReplyBy() {
        return this.replyBy;
    }
    public void setReplyBy(int replyBy) {
        this.replyBy = replyBy;
    }
    public String getReplyContent() {
        return this.replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    public String getReplyUsername() {
        return this.replyUsername;
    }
    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }
    public long getReplyTime() {
        return this.replyTime;
    }
    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }
    public long getPlanCheckTime() {
        return this.planCheckTime;
    }
    public void setPlanCheckTime(long planCheckTime) {
        this.planCheckTime = planCheckTime;
    }
    public int getProcessBy() {
        return this.processBy;
    }
    public void setProcessBy(int processBy) {
        this.processBy = processBy;
    }
    public String getProcessContent() {
        return this.processContent;
    }
    public void setProcessContent(String processContent) {
        this.processContent = processContent;
    }
    public String getProcessUser() {
        return this.processUser;
    }
    public void setProcessUser(String processUser) {
        this.processUser = processUser;
    }
    public long getProcessTime() {
        return this.processTime;
    }
    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }
    public long getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }
    public int getDtoResult() {
        return this.dtoResult;
    }
    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
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
    public boolean getIsTijiao() {
        return this.isTijiao;
    }
    public void setIsTijiao(boolean isTijiao) {
        this.isTijiao = isTijiao;
    }



  
}
