package com.examples.weibao.allbeans;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Administrator on 2017/11/2.
 */
@Entity
public class LiXianBeans {

  //  @ToMany(referencedJoinProperty = "iid") //指定与之关联的其他类的id
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
     * modifyTime : 1509586056843
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
    @Id
    @NotNull
    private Long id;
    private String address;
    private String area;
    private int auditBy;
    private int buildingHeight;
    private int createBy;
    private long createTime;
    private int dtoResult;
    private long failureDate;
    private int floor;
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

    @ToMany(referencedJoinProperty = "devid") //指定与之关联的其他类的id
    private List<DevicesBean> devicesBeans;
    @ToMany(referencedJoinProperty = "detid") //指定与之关联的其他类的id
    private List<DetectionsBean> detectionsBeans;
    @ToMany(referencedJoinProperty = "mid") //指定与之关联的其他类的id
    private List<MenusBean> menusBeans;
    @ToMany(referencedJoinProperty = "pid") //指定与之关联的其他类的id
    private List<PlansBean> plansBeans;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2113768792)
    private transient LiXianBeansDao myDao;
    @Generated(hash = 1071041105)
    public LiXianBeans(@NotNull Long id, String address, String area, int auditBy,
            int buildingHeight, int createBy, long createTime, int dtoResult,
            long failureDate, int floor, int itemAmount, int itemDuty, int itemId,
            int maxId, long modifyTime, String name, int nature, int pageNum,
            int pageSize, int safetyDuty, int safetyManager, int status,
            int taxInclusive, long validDate, int weibaoCompanyId, int weibaoMan,
            int weibaoMenuId, String weibaoTel, int weituoCompanyId) {
        this.id = id;
        this.address = address;
        this.area = area;
        this.auditBy = auditBy;
        this.buildingHeight = buildingHeight;
        this.createBy = createBy;
        this.createTime = createTime;
        this.dtoResult = dtoResult;
        this.failureDate = failureDate;
        this.floor = floor;
        this.itemAmount = itemAmount;
        this.itemDuty = itemDuty;
        this.itemId = itemId;
        this.maxId = maxId;
        this.modifyTime = modifyTime;
        this.name = name;
        this.nature = nature;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.safetyDuty = safetyDuty;
        this.safetyManager = safetyManager;
        this.status = status;
        this.taxInclusive = taxInclusive;
        this.validDate = validDate;
        this.weibaoCompanyId = weibaoCompanyId;
        this.weibaoMan = weibaoMan;
        this.weibaoMenuId = weibaoMenuId;
        this.weibaoTel = weibaoTel;
        this.weituoCompanyId = weituoCompanyId;
    }
    @Generated(hash = 1812805547)
    public LiXianBeans() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public int getBuildingHeight() {
        return this.buildingHeight;
    }
    public void setBuildingHeight(int buildingHeight) {
        this.buildingHeight = buildingHeight;
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
    public int getDtoResult() {
        return this.dtoResult;
    }
    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }
    public long getFailureDate() {
        return this.failureDate;
    }
    public void setFailureDate(long failureDate) {
        this.failureDate = failureDate;
    }
    public int getFloor() {
        return this.floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public int getItemAmount() {
        return this.itemAmount;
    }
    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }
    public int getItemDuty() {
        return this.itemDuty;
    }
    public void setItemDuty(int itemDuty) {
        this.itemDuty = itemDuty;
    }
    public int getItemId() {
        return this.itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getMaxId() {
        return this.maxId;
    }
    public void setMaxId(int maxId) {
        this.maxId = maxId;
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
    public int getNature() {
        return this.nature;
    }
    public void setNature(int nature) {
        this.nature = nature;
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
    public int getSafetyDuty() {
        return this.safetyDuty;
    }
    public void setSafetyDuty(int safetyDuty) {
        this.safetyDuty = safetyDuty;
    }
    public int getSafetyManager() {
        return this.safetyManager;
    }
    public void setSafetyManager(int safetyManager) {
        this.safetyManager = safetyManager;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getTaxInclusive() {
        return this.taxInclusive;
    }
    public void setTaxInclusive(int taxInclusive) {
        this.taxInclusive = taxInclusive;
    }
    public long getValidDate() {
        return this.validDate;
    }
    public void setValidDate(long validDate) {
        this.validDate = validDate;
    }
    public int getWeibaoCompanyId() {
        return this.weibaoCompanyId;
    }
    public void setWeibaoCompanyId(int weibaoCompanyId) {
        this.weibaoCompanyId = weibaoCompanyId;
    }
    public int getWeibaoMan() {
        return this.weibaoMan;
    }
    public void setWeibaoMan(int weibaoMan) {
        this.weibaoMan = weibaoMan;
    }
    public int getWeibaoMenuId() {
        return this.weibaoMenuId;
    }
    public void setWeibaoMenuId(int weibaoMenuId) {
        this.weibaoMenuId = weibaoMenuId;
    }
    public String getWeibaoTel() {
        return this.weibaoTel;
    }
    public void setWeibaoTel(String weibaoTel) {
        this.weibaoTel = weibaoTel;
    }
    public int getWeituoCompanyId() {
        return this.weituoCompanyId;
    }
    public void setWeituoCompanyId(int weituoCompanyId) {
        this.weituoCompanyId = weituoCompanyId;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 330609670)
    public List<DevicesBean> getDevicesBeans() {
        if (devicesBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DevicesBeanDao targetDao = daoSession.getDevicesBeanDao();
            List<DevicesBean> devicesBeansNew = targetDao
                    ._queryLiXianBeans_DevicesBeans(id);
            synchronized (this) {
                if (devicesBeans == null) {
                    devicesBeans = devicesBeansNew;
                }
            }
        }
        return devicesBeans;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 706405639)
    public synchronized void resetDevicesBeans() {
        devicesBeans = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2059155262)
    public List<DetectionsBean> getDetectionsBeans() {
        if (detectionsBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DetectionsBeanDao targetDao = daoSession.getDetectionsBeanDao();
            List<DetectionsBean> detectionsBeansNew = targetDao
                    ._queryLiXianBeans_DetectionsBeans(id);
            synchronized (this) {
                if (detectionsBeans == null) {
                    detectionsBeans = detectionsBeansNew;
                }
            }
        }
        return detectionsBeans;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1709472430)
    public synchronized void resetDetectionsBeans() {
        detectionsBeans = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1924707519)
    public List<MenusBean> getMenusBeans() {
        if (menusBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MenusBeanDao targetDao = daoSession.getMenusBeanDao();
            List<MenusBean> menusBeansNew = targetDao
                    ._queryLiXianBeans_MenusBeans(id);
            synchronized (this) {
                if (menusBeans == null) {
                    menusBeans = menusBeansNew;
                }
            }
        }
        return menusBeans;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1742758093)
    public synchronized void resetMenusBeans() {
        menusBeans = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1399796166)
    public List<PlansBean> getPlansBeans() {
        if (plansBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlansBeanDao targetDao = daoSession.getPlansBeanDao();
            List<PlansBean> plansBeansNew = targetDao
                    ._queryLiXianBeans_PlansBeans(id);
            synchronized (this) {
                if (plansBeans == null) {
                    plansBeans = plansBeansNew;
                }
            }
        }
        return plansBeans;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 680209820)
    public synchronized void resetPlansBeans() {
        plansBeans = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1369155335)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLiXianBeansDao() : null;
    }
    

}
