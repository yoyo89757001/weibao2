package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/2.
 */

    @Entity
    public  class DetectionsBean {
        /**
         * createTime : 1509534598838
         * detection : 0
         * dtoResult : 1
         * id : 19
         * modifyTime : 1509534598838
         * pageNum : 0
         * pageSize : 0
         * remark : 测试
         * weibaoMenuId : 1
         */
        @Id
        @NotNull
        private Long id;
        private Long detid;
        private long createTime;
        private int detection;
        private int dtoResult;
        private long modifyTime;
        private String pageNum;
        private String pageSize;
        private String remark;
        private int weibaoMenuId;
        @Generated(hash = 512389265)
        public DetectionsBean(@NotNull Long id, Long detid, long createTime,
                int detection, int dtoResult, long modifyTime, String pageNum,
                String pageSize, String remark, int weibaoMenuId) {
            this.id = id;
            this.detid = detid;
            this.createTime = createTime;
            this.detection = detection;
            this.dtoResult = dtoResult;
            this.modifyTime = modifyTime;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.remark = remark;
            this.weibaoMenuId = weibaoMenuId;
        }
        @Generated(hash = 81043678)
        public DetectionsBean() {
        }
        public Long getId() {
            return this.id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public Long getDetid() {
            return this.detid;
        }
        public void setDetid(Long detid) {
            this.detid = detid;
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
        public long getModifyTime() {
            return this.modifyTime;
        }
        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }
        public String getPageNum() {
            return this.pageNum;
        }
        public void setPageNum(String pageNum) {
            this.pageNum = pageNum;
        }
        public String getPageSize() {
            return this.pageSize;
        }
        public void setPageSize(String pageSize) {
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