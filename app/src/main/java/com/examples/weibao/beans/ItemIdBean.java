package com.examples.weibao.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class ItemIdBean {


    /**
     * createTime : 1509435875052
     * dtoResult : 0
     * modifyTime : 1509435875052
     * objects : [{"address":"龙威广场","area":"100000","auditBy":10000020,"auditTime":1509416194000,"buildingHeight":96,"createBy":0,"createTime":1508229361000,"dtoResult":0,"failureDate":1601481600000,"floor":32,"id":10044,"itemAmount":1000000,"itemDuty":10000019,"itemDutyName":"维保主管","itemId":0,"maxId":0,"modifyTime":1509435875036,"name":"消防项目1","nature":1,"pageNum":0,"pageSize":0,"safetyDuty":10000019,"safetyManager":10000021,"status":10,"taxInclusive":0,"validDate":1506787200000,"weibaoCompanyId":10001,"weibaoCompanyName":"广州市局","weibaoMan":10000020,"weibaoMenuId":0,"weibaoTel":"18855555555","weituoCompanyId":5,"weituoCompanyName":"深华"},{"address":"123456","area":"","auditBy":2,"auditTime":1509414354000,"buildingHeight":0,"createBy":0,"createTime":1508117041000,"dtoResult":0,"failureDate":1506787200000,"floor":0,"id":10042,"itemAmount":152,"itemDuty":10000019,"itemDutyName":"维保主管","itemId":0,"maxId":0,"modifyTime":1509435875052,"name":"测试2","nature":0,"pageNum":0,"pageSize":0,"safetyDuty":10000020,"safetyManager":10000020,"status":10,"taxInclusive":0,"validDate":1506787200000,"weibaoCompanyId":5,"weibaoCompanyName":"深华","weibaoMan":10000020,"weibaoMenuId":0,"weibaoTel":"12345678940","weituoCompanyId":10001,"weituoCompanyName":"广州市局"},{"address":"11","area":"1","auditBy":1,"auditTime":1507887832000,"buildingHeight":1,"createBy":0,"createTime":1507878070000,"dtoResult":0,"failureDate":1506787200000,"floor":1,"id":10027,"itemAmount":1,"itemDuty":10000020,"itemDutyName":"维保工程师","itemId":0,"maxId":0,"modifyTime":1509435875052,"name":"11","nature":2,"pageNum":0,"pageSize":0,"safetyDuty":10000020,"safetyManager":10000020,"status":10,"taxInclusive":0,"validDate":1506787200000,"weibaoCompanyId":4,"weibaoCompanyName":"223","weibaoMan":10000020,"weibaoMenuId":0,"weibaoTel":"11","weituoCompanyId":4,"weituoCompanyName":"223"}]
     * pageIndex : 0
     * pageNum : 1
     * pageSize : 15
     * totalNum : 3
     */

    private long createTime;
    private int dtoResult;
    private long modifyTime;
    private int pageIndex;
    private int pageNum;
    private int pageSize;
    private int totalNum;
    private List<ObjectsBean> objects;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDtoResult() {
        return dtoResult;
    }

    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<ObjectsBean> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsBean> objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        /**
         * address : 龙威广场
         * area : 100000
         * auditBy : 10000020
         * auditTime : 1509416194000
         * buildingHeight : 96
         * createBy : 0
         * createTime : 1508229361000
         * dtoResult : 0
         * failureDate : 1601481600000
         * floor : 32
         * id : 10044
         * itemAmount : 1000000
         * itemDuty : 10000019
         * itemDutyName : 维保主管
         * itemId : 0
         * maxId : 0
         * modifyTime : 1509435875036
         * name : 消防项目1
         * nature : 1
         * pageNum : 0
         * pageSize : 0
         * safetyDuty : 10000019
         * safetyManager : 10000021
         * status : 10
         * taxInclusive : 0
         * validDate : 1506787200000
         * weibaoCompanyId : 10001
         * weibaoCompanyName : 广州市局
         * weibaoMan : 10000020
         * weibaoMenuId : 0
         * weibaoTel : 18855555555
         * weituoCompanyId : 5
         * weituoCompanyName : 深华
         */

        private String address;
        private String area;
        private int auditBy;
        private long auditTime;
        private int buildingHeight;
        private int createBy;
        private long createTime;
        private int dtoResult;
        private long failureDate;
        private int floor;
        private int id;
        private int itemAmount;
        private int itemDuty;
        private String itemDutyName;
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
        private String weibaoCompanyName;
        private int weibaoMan;
        private int weibaoMenuId;
        private String weibaoTel;
        private int weituoCompanyId;
        private String weituoCompanyName;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getAuditBy() {
            return auditBy;
        }

        public void setAuditBy(int auditBy) {
            this.auditBy = auditBy;
        }

        public long getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(long auditTime) {
            this.auditTime = auditTime;
        }

        public int getBuildingHeight() {
            return buildingHeight;
        }

        public void setBuildingHeight(int buildingHeight) {
            this.buildingHeight = buildingHeight;
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

        public int getDtoResult() {
            return dtoResult;
        }

        public void setDtoResult(int dtoResult) {
            this.dtoResult = dtoResult;
        }

        public long getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(long failureDate) {
            this.failureDate = failureDate;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItemAmount() {
            return itemAmount;
        }

        public void setItemAmount(int itemAmount) {
            this.itemAmount = itemAmount;
        }

        public int getItemDuty() {
            return itemDuty;
        }

        public void setItemDuty(int itemDuty) {
            this.itemDuty = itemDuty;
        }

        public String getItemDutyName() {
            return itemDutyName;
        }

        public void setItemDutyName(String itemDutyName) {
            this.itemDutyName = itemDutyName;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getMaxId() {
            return maxId;
        }

        public void setMaxId(int maxId) {
            this.maxId = maxId;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNature() {
            return nature;
        }

        public void setNature(int nature) {
            this.nature = nature;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSafetyDuty() {
            return safetyDuty;
        }

        public void setSafetyDuty(int safetyDuty) {
            this.safetyDuty = safetyDuty;
        }

        public int getSafetyManager() {
            return safetyManager;
        }

        public void setSafetyManager(int safetyManager) {
            this.safetyManager = safetyManager;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTaxInclusive() {
            return taxInclusive;
        }

        public void setTaxInclusive(int taxInclusive) {
            this.taxInclusive = taxInclusive;
        }

        public long getValidDate() {
            return validDate;
        }

        public void setValidDate(long validDate) {
            this.validDate = validDate;
        }

        public int getWeibaoCompanyId() {
            return weibaoCompanyId;
        }

        public void setWeibaoCompanyId(int weibaoCompanyId) {
            this.weibaoCompanyId = weibaoCompanyId;
        }

        public String getWeibaoCompanyName() {
            return weibaoCompanyName;
        }

        public void setWeibaoCompanyName(String weibaoCompanyName) {
            this.weibaoCompanyName = weibaoCompanyName;
        }

        public int getWeibaoMan() {
            return weibaoMan;
        }

        public void setWeibaoMan(int weibaoMan) {
            this.weibaoMan = weibaoMan;
        }

        public int getWeibaoMenuId() {
            return weibaoMenuId;
        }

        public void setWeibaoMenuId(int weibaoMenuId) {
            this.weibaoMenuId = weibaoMenuId;
        }

        public String getWeibaoTel() {
            return weibaoTel;
        }

        public void setWeibaoTel(String weibaoTel) {
            this.weibaoTel = weibaoTel;
        }

        public int getWeituoCompanyId() {
            return weituoCompanyId;
        }

        public void setWeituoCompanyId(int weituoCompanyId) {
            this.weituoCompanyId = weituoCompanyId;
        }

        public String getWeituoCompanyName() {
            return weituoCompanyName;
        }

        public void setWeituoCompanyName(String weituoCompanyName) {
            this.weituoCompanyName = weituoCompanyName;
        }
    }
}
