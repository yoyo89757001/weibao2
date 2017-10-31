package com.examples.weibao.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class AllBenDiBean {

    private List<ItemsBean> items;
    private List<PlansBean> plans;
    private List<DevicesBean> devices;
    private List<MenusBean> menus;
    private List<DetectionsBean> detections;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<PlansBean> getPlans() {
        return plans;
    }

    public void setPlans(List<PlansBean> plans) {
        this.plans = plans;
    }

    public List<DevicesBean> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesBean> devices) {
        this.devices = devices;
    }

    public List<MenusBean> getMenus() {
        return menus;
    }

    public void setMenus(List<MenusBean> menus) {
        this.menus = menus;
    }

    public List<DetectionsBean> getDetections() {
        return detections;
    }

    public void setDetections(List<DetectionsBean> detections) {
        this.detections = detections;
    }

    public static class ItemsBean {
        /**
         * address : 龙口东
         * area : 1000
         * auditBy : 0
         * buildingHeight : 36
         * createBy : 0
         * createTime : 1507790000000
         * dtoResult : 3
         * failureDate : 1512057600000
         * floor : 12
         * id : 10001
         * itemAmount : 200
         * itemDuty : 2
         * itemId : 0
         * maxId : 0
         * modifyTime : 1509430960322
         * name : 维保计划项目测试333
         * nature : 1
         * pageNum : 0
         * pageSize : 0
         * safetyDuty : 2
         * safetyManager : 2
         * status : 10
         * taxInclusive : 0
         * validDate : 1504195200000
         * weibaoCompanyId : 10001
         * weibaoMan : 2
         * weibaoMenuId : 0
         * weibaoTel : 13316082976
         * weituoCompanyId : 10001
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
    }

    public static class PlansBean {
        /**
         * area : 天河
         * auditBy : 0
         * createBy : 0
         * createTime : 1509418157000
         * dtoResult : 1
         * id : 10001
         * itemId : 10001
         * modifyTime : 1509430960363
         * pageNum : 0
         * pageSize : 0
         * planMonth : 201709
         * status : 10
         */

        private String area;
        private int auditBy;
        private int createBy;
        private long createTime;
        private int dtoResult;
        private int id;
        private int itemId;
        private long modifyTime;
        private int pageNum;
        private int pageSize;
        private int planMonth;
        private int status;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
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

        public int getPlanMonth() {
            return planMonth;
        }

        public void setPlanMonth(int planMonth) {
            this.planMonth = planMonth;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class DevicesBean {
        /**
         * area : 19楼
         * auditBy : 0
         * checkPeriod : 0
         * city : 01
         * createBy : 0
         * deviceNum : 1234aB
         * dtoResult : 1
         * failureTime : 1507564800000
         * floor : 1栋
         * id : 1
         * isDetection : 1
         * itemId : 10001
         * modifyTime : 1509430960380
         * name : d1
         * pageNum : 0
         * pageSize : 0
         * province : 01
         * status : 10
         * subArea : 01
         * unit : 01
         * validTime : 1506787200000
         * weibaoSubSystemId : 2
         * weibaoSystemId : 1
         */

        private String area;
        private int auditBy;
        private int checkPeriod;
        private String city;
        private int createBy;
        private String deviceNum;
        private int dtoResult;
        private long failureTime;
        private String floor;
        private int id;
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

        public int getCheckPeriod() {
            return checkPeriod;
        }

        public void setCheckPeriod(int checkPeriod) {
            this.checkPeriod = checkPeriod;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public int getDtoResult() {
            return dtoResult;
        }

        public void setDtoResult(int dtoResult) {
            this.dtoResult = dtoResult;
        }

        public long getFailureTime() {
            return failureTime;
        }

        public void setFailureTime(long failureTime) {
            this.failureTime = failureTime;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDetection() {
            return isDetection;
        }

        public void setIsDetection(int isDetection) {
            this.isDetection = isDetection;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSubArea() {
            return subArea;
        }

        public void setSubArea(String subArea) {
            this.subArea = subArea;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public long getValidTime() {
            return validTime;
        }

        public void setValidTime(long validTime) {
            this.validTime = validTime;
        }

        public String getWeibaoSubSystemId() {
            return weibaoSubSystemId;
        }

        public void setWeibaoSubSystemId(String weibaoSubSystemId) {
            this.weibaoSubSystemId = weibaoSubSystemId;
        }

        public String getWeibaoSystemId() {
            return weibaoSystemId;
        }

        public void setWeibaoSystemId(String weibaoSystemId) {
            this.weibaoSystemId = weibaoSystemId;
        }
    }

    public static class MenusBean {
        /**
         * createBy : 1
         * createTime : 1508317914000
         * dtoResult : 3
         * id : 1
         * level : 1
         * modifyBy : 2
         * modifyTime : 1508227255000
         * name : 消防供配电设施
         * pageNum : 0
         * pageSize : 0
         * parentId : -1
         * serialNumber : 001
         * sort : 1
         * status : 10
         * type : 1
         */

        private int createBy;
        private long createTime;
        private int dtoResult;
        private int id;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(int modifyBy) {
            this.modifyBy = modifyBy;
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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class DetectionsBean {
        /**
         * createTime : 1509430960442
         * detection : 0
         * dtoResult : 1
         * id : 19
         * modifyTime : 1509430960442
         * pageNum : 0
         * pageSize : 0
         * remark : 测试
         * weibaoMenuId : 1
         */

        private long createTime;
        private int detection;
        private int dtoResult;
        private int id;
        private long modifyTime;
        private int pageNum;
        private int pageSize;
        private String remark;
        private int weibaoMenuId;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDetection() {
            return detection;
        }

        public void setDetection(int detection) {
            this.detection = detection;
        }

        public int getDtoResult() {
            return dtoResult;
        }

        public void setDtoResult(int dtoResult) {
            this.dtoResult = dtoResult;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getWeibaoMenuId() {
            return weibaoMenuId;
        }

        public void setWeibaoMenuId(int weibaoMenuId) {
            this.weibaoMenuId = weibaoMenuId;
        }
    }
}
