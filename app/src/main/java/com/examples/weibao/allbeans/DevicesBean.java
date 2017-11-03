package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/2.
 */

    @Entity
    public  class DevicesBean {

    /**
     * allocationTime : 1506873600000
     * area : 03
     * auditBy : 10000020
     * auditTime : 1509695192000
     * buildingNum : 01
     * checkCase : 检查情况
     * checkPeriod : 3
     * checkTime : 1506960000000
     * city : 01
     * createBy : 10000020
     * createTime : 1509695171000
     * deviceNum : a123456
     * dtoResult : 1
     * failureTime : 1509465600000
     * floor : 01
     * groupDuty : 责任部门
     * id : 494
     * isDetection : 0
     * itemId : 10066
     * manageMemberDuty : 管理责任人
     * manageMemberDutyTel : 13398765432
     * memberDuty : 维保工程师
     * memberDutyTel : 13312345678
     * model : 型号
     * modifyTime : 1509696646966
     * name : 测试Ａ
     * other : 相关方
     * otherMember : 相关方直接责任人
     * pageNum : 0
     * pageSize : 0
     * producedTime : 1506787200000
     * provider : 供应商
     * province : 44
     * qrCodePath : 13570327.jpg
     * sign : 品牌
     * status : 10
     * submitter : 提交人
     * unit : 01
     * validTime : 1506787200000
     * weibaoSubSystemId : 003001
     * weibaoSystemId : 003
     */
    @Id
    @NotNull
    private Long id;
    private Long devid;
    private long allocationTime;
    private String area;
    private int auditBy;
    private long auditTime;
    private String buildingNum;
    private String checkCase;
    private int checkPeriod;
    private long checkTime;
    private String city;
    private int createBy;
    private long createTime;
    private String deviceNum;
    private int dtoResult;
    private long failureTime;
    private String floor;
    private String groupDuty;
    private int isDetection;
    private int itemId;
    private String manageMemberDuty;
    private String manageMemberDutyTel;
    private String memberDuty;
    private String memberDutyTel;
    private String model;
    private long modifyTime;
    private String name;
    private String other;
    private String otherMember;
    private int pageNum;
    private int pageSize;
    private long producedTime;
    private String provider;
    private String province;
    private String qrCodePath;
    private String sign;
    private int status;
    private String submitter;
    private String unit;
    private long validTime;
    private String weibaoSubSystemId;
    private String weibaoSystemId;
    @Generated(hash = 1697208712)
    public DevicesBean(@NotNull Long id, Long devid, long allocationTime,
            String area, int auditBy, long auditTime, String buildingNum,
            String checkCase, int checkPeriod, long checkTime, String city,
            int createBy, long createTime, String deviceNum, int dtoResult,
            long failureTime, String floor, String groupDuty, int isDetection,
            int itemId, String manageMemberDuty, String manageMemberDutyTel,
            String memberDuty, String memberDutyTel, String model, long modifyTime,
            String name, String other, String otherMember, int pageNum,
            int pageSize, long producedTime, String provider, String province,
            String qrCodePath, String sign, int status, String submitter,
            String unit, long validTime, String weibaoSubSystemId,
            String weibaoSystemId) {
        this.id = id;
        this.devid = devid;
        this.allocationTime = allocationTime;
        this.area = area;
        this.auditBy = auditBy;
        this.auditTime = auditTime;
        this.buildingNum = buildingNum;
        this.checkCase = checkCase;
        this.checkPeriod = checkPeriod;
        this.checkTime = checkTime;
        this.city = city;
        this.createBy = createBy;
        this.createTime = createTime;
        this.deviceNum = deviceNum;
        this.dtoResult = dtoResult;
        this.failureTime = failureTime;
        this.floor = floor;
        this.groupDuty = groupDuty;
        this.isDetection = isDetection;
        this.itemId = itemId;
        this.manageMemberDuty = manageMemberDuty;
        this.manageMemberDutyTel = manageMemberDutyTel;
        this.memberDuty = memberDuty;
        this.memberDutyTel = memberDutyTel;
        this.model = model;
        this.modifyTime = modifyTime;
        this.name = name;
        this.other = other;
        this.otherMember = otherMember;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.producedTime = producedTime;
        this.provider = provider;
        this.province = province;
        this.qrCodePath = qrCodePath;
        this.sign = sign;
        this.status = status;
        this.submitter = submitter;
        this.unit = unit;
        this.validTime = validTime;
        this.weibaoSubSystemId = weibaoSubSystemId;
        this.weibaoSystemId = weibaoSystemId;
    }
    @Generated(hash = 1295633009)
    public DevicesBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDevid() {
        return this.devid;
    }
    public void setDevid(Long devid) {
        this.devid = devid;
    }
    public long getAllocationTime() {
        return this.allocationTime;
    }
    public void setAllocationTime(long allocationTime) {
        this.allocationTime = allocationTime;
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
    public long getAuditTime() {
        return this.auditTime;
    }
    public void setAuditTime(long auditTime) {
        this.auditTime = auditTime;
    }
    public String getBuildingNum() {
        return this.buildingNum;
    }
    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }
    public String getCheckCase() {
        return this.checkCase;
    }
    public void setCheckCase(String checkCase) {
        this.checkCase = checkCase;
    }
    public int getCheckPeriod() {
        return this.checkPeriod;
    }
    public void setCheckPeriod(int checkPeriod) {
        this.checkPeriod = checkPeriod;
    }
    public long getCheckTime() {
        return this.checkTime;
    }
    public void setCheckTime(long checkTime) {
        this.checkTime = checkTime;
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
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
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
    public String getFloor() {
        return this.floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getGroupDuty() {
        return this.groupDuty;
    }
    public void setGroupDuty(String groupDuty) {
        this.groupDuty = groupDuty;
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
    public String getManageMemberDuty() {
        return this.manageMemberDuty;
    }
    public void setManageMemberDuty(String manageMemberDuty) {
        this.manageMemberDuty = manageMemberDuty;
    }
    public String getManageMemberDutyTel() {
        return this.manageMemberDutyTel;
    }
    public void setManageMemberDutyTel(String manageMemberDutyTel) {
        this.manageMemberDutyTel = manageMemberDutyTel;
    }
    public String getMemberDuty() {
        return this.memberDuty;
    }
    public void setMemberDuty(String memberDuty) {
        this.memberDuty = memberDuty;
    }
    public String getMemberDutyTel() {
        return this.memberDutyTel;
    }
    public void setMemberDutyTel(String memberDutyTel) {
        this.memberDutyTel = memberDutyTel;
    }
    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
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
    public String getOther() {
        return this.other;
    }
    public void setOther(String other) {
        this.other = other;
    }
    public String getOtherMember() {
        return this.otherMember;
    }
    public void setOtherMember(String otherMember) {
        this.otherMember = otherMember;
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
    public long getProducedTime() {
        return this.producedTime;
    }
    public void setProducedTime(long producedTime) {
        this.producedTime = producedTime;
    }
    public String getProvider() {
        return this.provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getQrCodePath() {
        return this.qrCodePath;
    }
    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }
    public String getSign() {
        return this.sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getSubmitter() {
        return this.submitter;
    }
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
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
