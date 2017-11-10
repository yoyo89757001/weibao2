package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/10.
 */
@Entity
public class BenDiMenusBean {
    @Id
    @NotNull
    private Long id;
    private boolean isYiChang;
    private long mensuId;
    private long parentId;
    private String name;
    private boolean isQiTa;
    @Generated(hash = 238725694)
    public BenDiMenusBean(@NotNull Long id, boolean isYiChang, long mensuId,
            long parentId, String name, boolean isQiTa) {
        this.id = id;
        this.isYiChang = isYiChang;
        this.mensuId = mensuId;
        this.parentId = parentId;
        this.name = name;
        this.isQiTa = isQiTa;
    }
    @Generated(hash = 670714680)
    public BenDiMenusBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getIsYiChang() {
        return this.isYiChang;
    }
    public void setIsYiChang(boolean isYiChang) {
        this.isYiChang = isYiChang;
    }
    public long getMensuId() {
        return this.mensuId;
    }
    public void setMensuId(long mensuId) {
        this.mensuId = mensuId;
    }
    public long getParentId() {
        return this.parentId;
    }
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsQiTa() {
        return this.isQiTa;
    }
    public void setIsQiTa(boolean isQiTa) {
        this.isQiTa = isQiTa;
    }


}
