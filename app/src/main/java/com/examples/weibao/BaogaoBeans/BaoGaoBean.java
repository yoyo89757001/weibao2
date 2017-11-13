package com.examples.weibao.BaogaoBeans;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class BaoGaoBean {


    /**
     * createTime : 1510542860076
     * dtoResult : 0
     * modifyTime : 1510542860076
     * objects : [{"createBy":1,"createTime":1510305350000,"dtoResult":0,"itemDutyName":"维保主管","itemName":"测试项目(勿删)","item_id":10066,"modifyTime":1510305350000,"pageNum":0,"pageSize":0,"planArea":"广东省广州市天河区区区区2","planId":10026,"planMonth":201710,"reportFile":"201710/测试项目(勿删)-广东省广州市天河区区区区2-201710月度报告（10026）.doc","safetyDutyName":"唐颖涛","status":10}]
     * pageIndex : 0
     * pageNum : 1
     * pageSize : 10
     * totalNum : 1
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
         * createBy : 1
         * createTime : 1510305350000
         * dtoResult : 0
         * itemDutyName : 维保主管
         * itemName : 测试项目(勿删)
         * item_id : 10066
         * modifyTime : 1510305350000
         * pageNum : 0
         * pageSize : 0
         * planArea : 广东省广州市天河区区区区2
         * planId : 10026
         * planMonth : 201710
         * reportFile : 201710/测试项目(勿删)-广东省广州市天河区区区区2-201710月度报告（10026）.doc
         * safetyDutyName : 唐颖涛
         * status : 10
         */

        private int createBy;
        private long createTime;
        private int dtoResult;
        private String itemDutyName;
        private String itemName;
        private int item_id;
        private long modifyTime;
        private int pageNum;
        private int pageSize;
        private String planArea;
        private int planId;
        private int planMonth;
        private String reportFile;
        private String safetyDutyName;
        private int status;

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

        public String getItemDutyName() {
            return itemDutyName;
        }

        public void setItemDutyName(String itemDutyName) {
            this.itemDutyName = itemDutyName;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
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

        public String getPlanArea() {
            return planArea;
        }

        public void setPlanArea(String planArea) {
            this.planArea = planArea;
        }

        public int getPlanId() {
            return planId;
        }

        public void setPlanId(int planId) {
            this.planId = planId;
        }

        public int getPlanMonth() {
            return planMonth;
        }

        public void setPlanMonth(int planMonth) {
            this.planMonth = planMonth;
        }

        public String getReportFile() {
            return reportFile;
        }

        public void setReportFile(String reportFile) {
            this.reportFile = reportFile;
        }

        public String getSafetyDutyName() {
            return safetyDutyName;
        }

        public void setSafetyDutyName(String safetyDutyName) {
            this.safetyDutyName = safetyDutyName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
