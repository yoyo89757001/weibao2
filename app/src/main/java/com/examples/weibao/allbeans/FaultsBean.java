package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/13.
 */
@Entity
public class FaultsBean {

    /**
     * accountId : 10000020
     * address : 111
     * companyId : 10001
     * contactTel : 13314567896
     * deviceId : 491
     * deviceNumber : 123
     * dtoResult : 1
     * faultTime : 1509787543000
     * id : 1
     * modifyTime : 1510558182439
     * pageNum : 0
     * pageSize : 0
     * planCheckTime : 1510156800000
     * processBy : 0
     * remark : 111
     * replyBy : 10000020
     * replyContent : 情况属实
     * replyTime : 1510156800000
     * replyUsername : 施工工程师
     * status : 2
     */

    private int accountId;
    private String address;
    private int companyId;
    private String contactTel;
    private int deviceId;
    private String deviceNumber;
    private int dtoResult;
    private long faultTime;
    @Id
    @NotNull
    private Long id;
    private long modifyTime;
    private int pageNum;
    private int pageSize;
    private long planCheckTime;
    private int processBy;
    private String remark;
    private int replyBy;
    private String replyContent;
    private long replyTime;
    private String replyUsername;
    private String processUsername;
    private String processContent;
    private int status;
    private String faultImage;
    private boolean isXiazai;
    @Generated(hash = 490351224)
    public FaultsBean(int accountId, String address, int companyId,
            String contactTel, int deviceId, String deviceNumber, int dtoResult,
            long faultTime, @NotNull Long id, long modifyTime, int pageNum,
            int pageSize, long planCheckTime, int processBy, String remark,
            int replyBy, String replyContent, long replyTime, String replyUsername,
            String processUsername, String processContent, int status,
            String faultImage, boolean isXiazai) {
        this.accountId = accountId;
        this.address = address;
        this.companyId = companyId;
        this.contactTel = contactTel;
        this.deviceId = deviceId;
        this.deviceNumber = deviceNumber;
        this.dtoResult = dtoResult;
        this.faultTime = faultTime;
        this.id = id;
        this.modifyTime = modifyTime;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.planCheckTime = planCheckTime;
        this.processBy = processBy;
        this.remark = remark;
        this.replyBy = replyBy;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
        this.replyUsername = replyUsername;
        this.processUsername = processUsername;
        this.processContent = processContent;
        this.status = status;
        this.faultImage = faultImage;
        this.isXiazai = isXiazai;
    }
    @Generated(hash = 313645264)
    public FaultsBean() {
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
    public String getContactTel() {
        return this.contactTel;
    }
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
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
    public int getDtoResult() {
        return this.dtoResult;
    }
    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }
    public long getFaultTime() {
        return this.faultTime;
    }
    public void setFaultTime(long faultTime) {
        this.faultTime = faultTime;
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
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
    public long getReplyTime() {
        return this.replyTime;
    }
    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }
    public String getReplyUsername() {
        return this.replyUsername;
    }
    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }
    public String getProcessUsername() {
        return this.processUsername;
    }
    public void setProcessUsername(String processUsername) {
        this.processUsername = processUsername;
    }
    public String getProcessContent() {
        return this.processContent;
    }
    public void setProcessContent(String processContent) {
        this.processContent = processContent;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getFaultImage() {
        return this.faultImage;
    }
    public void setFaultImage(String faultImage) {
        this.faultImage = faultImage;
    }
    public boolean getIsXiazai() {
        return this.isXiazai;
    }
    public void setIsXiazai(boolean isXiazai) {
        this.isXiazai = isXiazai;
    }

}
