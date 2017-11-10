package com.examples.weibao.ShouKuanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class ShouKuansBean {


    /**
     * createTime : 1510128941295
     * dtoResult : 0
     * modifyTime : 1510128941295
     * objects : [{"address":"22222222222222","area":"1000","auditBy":0,"buildingHeight":36,"createBy":0,"createTime":1510036410000,"dtoResult":0,"failureDate":1514736000000,"floor":12,"id":10067,"itemAmount":200,"itemAmountIncoming":2,"itemAmountReceived":7,"itemDuty":10000042,"itemDutyName":"黄春瑜","itemId":0,"maxId":0,"modifyTime":1510128941287,"name":"t2","nature":1,"pageNum":0,"pageSize":0,"payStatus":0,"safetyDuty":10000042,"safetyManager":10000042,"status":10,"taxInclusive":0,"validDate":1504195200000,"weibaoCompanyId":10001,"weibaoCompanyName":"广州市局","weibaoMan":10000042,"weibaoMenuId":0,"weibaoTel":"13456789650","weituoCompanyId":4,"weituoCompanyName":"223"},{"address":"广州市天河区岗顶天娱广场","area":"1000","auditBy":10000020,"auditTime":1509674088000,"buildingHeight":18,"createBy":0,"createTime":1509672598000,"dtoResult":0,"failureDate":1514736000000,"floor":6,"id":10066,"itemAmount":100,"itemAmountIncoming":0,"itemAmountReceived":0,"itemDuty":10000019,"itemDutyName":"维保主管","itemId":0,"maxId":0,"modifyTime":1510128941291,"name":"测试项目(勿删)","nature":3,"pageNum":0,"pageSize":0,"payStatus":0,"safetyDuty":10000025,"safetyManager":10000024,"status":10,"taxInclusive":0,"validDate":1504195200000,"weibaoCompanyId":10001,"weibaoCompanyName":"广州市局","weibaoMan":10000020,"weibaoMenuId":0,"weibaoTel":"13312345678","weituoCompanyId":5,"weituoCompanyName":"广东深华消防设备工程股份有限公司"}]
     * pageIndex : 0
     * pageNum : 1
     * pageSize : 1000
     * totalNum : 2
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
         * address : 22222222222222
         * area : 1000
         * auditBy : 0
         * buildingHeight : 36
         * createBy : 0
         * createTime : 1510036410000
         * dtoResult : 0
         * failureDate : 1514736000000
         * floor : 12
         * id : 10067
         * itemAmount : 200
         * itemAmountIncoming : 2
         * itemAmountReceived : 7
         * itemDuty : 10000042
         * itemDutyName : 黄春瑜
         * itemId : 0
         * maxId : 0
         * modifyTime : 1510128941287
         * name : t2
         * nature : 1
         * pageNum : 0
         * pageSize : 0
         * payStatus : 0
         * safetyDuty : 10000042
         * safetyManager : 10000042
         * status : 10
         * taxInclusive : 0
         * validDate : 1504195200000
         * weibaoCompanyId : 10001
         * weibaoCompanyName : 广州市局
         * weibaoMan : 10000042
         * weibaoMenuId : 0
         * weibaoTel : 13456789650
         * weituoCompanyId : 4
         * weituoCompanyName : 223
         * auditTime : 1509674088000
         */

        private String address;
        private String area;
        private int auditBy;
        private int buildingHeight;
        private int createBy;
        private long createTime;
        private int dtoResult;
        private long failureDate;
        private int floor;
        private int id;
        private double itemAmount;
        private double itemAmountIncoming;
        private double itemAmountReceived;
        private int itemDuty;
        private String itemDutyName;
        private int itemId;
        private int maxId;
        private long modifyTime;
        private String name;
        private int nature;
        private int pageNum;
        private int pageSize;
        private int payStatus;
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
        private long auditTime;

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

        public double getItemAmount() {
            return itemAmount;
        }

        public void setItemAmount(double itemAmount) {
            this.itemAmount = itemAmount;
        }

        public double getItemAmountIncoming() {
            return itemAmountIncoming;
        }

        public void setItemAmountIncoming(double itemAmountIncoming) {
            this.itemAmountIncoming = itemAmountIncoming;
        }

        public double getItemAmountReceived() {
            return itemAmountReceived;
        }

        public void setItemAmountReceived(double itemAmountReceived) {
            this.itemAmountReceived = itemAmountReceived;
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

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
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

        public long getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(long auditTime) {
            this.auditTime = auditTime;
        }
    }
}
