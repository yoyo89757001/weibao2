package com.examples.weibao.xiaoxibeans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class XiaoXiBean {


    /**
     * createTime : 1509438315239
     * dtoResult : 0
     * modifyTime : 1509438315239
     * objects : [{"content":"发送","createTime":1509418541000,"dtoResult":0,"id":32,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"发送","createTime":1509418540000,"dtoResult":0,"id":31,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"发送","createTime":1509418532000,"dtoResult":0,"id":30,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"发送","createTime":1509418342000,"dtoResult":0,"id":29,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"发送","createTime":1509418122000,"dtoResult":0,"id":28,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"发送","createTime":1509418121000,"dtoResult":0,"id":27,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"发送","createTime":1509418120000,"dtoResult":0,"id":26,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"hhh","createTime":1509418116000,"dtoResult":0,"id":25,"itemId":10044,"modifyTime":1509438315224,"operId":10000020,"operName":"wbgcs","pageNum":0,"pageSize":0,"status":0},{"content":"hhh","createTime":1509412640000,"dtoResult":0,"id":19,"itemId":10044,"modifyTime":1509438315224,"operId":2,"operName":"admin","pageNum":0,"pageSize":0,"status":0}]
     * pageIndex : 0
     * pageNum : 1
     * pageSize : 15
     * totalNum : 9
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
         * content : 发送
         * createTime : 1509418541000
         * dtoResult : 0
         * id : 32
         * itemId : 10044
         * modifyTime : 1509438315224
         * operId : 10000020
         * operName : wbgcs
         * pageNum : 0
         * pageSize : 0
         * status : 0
         */

        private String content;
        private long createTime;
        private int dtoResult;
        private int id;
        private int itemId;
        private long modifyTime;
        private int operId;
        private String operName;
        private int pageNum;
        private int pageSize;
        private int status;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getOperId() {
            return operId;
        }

        public void setOperId(int operId) {
            this.operId = operId;
        }

        public String getOperName() {
            return operName;
        }

        public void setOperName(String operName) {
            this.operName = operName;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
