package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Administrator on 2017/11/6.
 */

@Entity
public class BaoZhangDengJiBean {
    @Id
    @NotNull
    private Long id;
    private String danwei;
    private String dizhi;
    private String shebei;
    private String guzhangmiaoshu;
    private String guzhangshijian;
    private String baozhangren;
    private String dianhua;
    private boolean isTiJiao;

    @ToMany(referencedJoinProperty = "pid")//指定与之关联的其他类的id
    private List<PhotosBean> photosBeanList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1747493719)
    private transient BaoZhangDengJiBeanDao myDao;

    @Generated(hash = 331066935)
    public BaoZhangDengJiBean(@NotNull Long id, String danwei, String dizhi,
            String shebei, String guzhangmiaoshu, String guzhangshijian,
            String baozhangren, String dianhua, boolean isTiJiao) {
        this.id = id;
        this.danwei = danwei;
        this.dizhi = dizhi;
        this.shebei = shebei;
        this.guzhangmiaoshu = guzhangmiaoshu;
        this.guzhangshijian = guzhangshijian;
        this.baozhangren = baozhangren;
        this.dianhua = dianhua;
        this.isTiJiao = isTiJiao;
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

    public String getDanwei() {
        return this.danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getDizhi() {
        return this.dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getShebei() {
        return this.shebei;
    }

    public void setShebei(String shebei) {
        this.shebei = shebei;
    }

    public String getGuzhangmiaoshu() {
        return this.guzhangmiaoshu;
    }

    public void setGuzhangmiaoshu(String guzhangmiaoshu) {
        this.guzhangmiaoshu = guzhangmiaoshu;
    }

    public String getGuzhangshijian() {
        return this.guzhangshijian;
    }

    public void setGuzhangshijian(String guzhangshijian) {
        this.guzhangshijian = guzhangshijian;
    }

    public String getBaozhangren() {
        return this.baozhangren;
    }

    public void setBaozhangren(String baozhangren) {
        this.baozhangren = baozhangren;
    }

    public String getDianhua() {
        return this.dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public boolean getIsTiJiao() {
        return this.isTiJiao;
    }

    public void setIsTiJiao(boolean isTiJiao) {
        this.isTiJiao = isTiJiao;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1521561152)
    public List<PhotosBean> getPhotosBeanList() {
        if (photosBeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotosBeanDao targetDao = daoSession.getPhotosBeanDao();
            List<PhotosBean> photosBeanListNew = targetDao
                    ._queryBaoZhangDengJiBean_PhotosBeanList(id);
            synchronized (this) {
                if (photosBeanList == null) {
                    photosBeanList = photosBeanListNew;
                }
            }
        }
        return photosBeanList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1444559707)
    public synchronized void resetPhotosBeanList() {
        photosBeanList = null;
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
    @Generated(hash = 379854186)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBaoZhangDengJiBeanDao() : null;
    }

}
