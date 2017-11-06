package com.examples.weibao.allbeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/6.
 */
@Entity
public class PhotosBean {
    @Id
    @NotNull
    private Long id;
    private long pid;
    private String path;
    @Generated(hash = 691892085)
    public PhotosBean(@NotNull Long id, long pid, String path) {
        this.id = id;
        this.pid = pid;
        this.path = path;
    }
    @Generated(hash = 1002121505)
    public PhotosBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getPid() {
        return this.pid;
    }
    public void setPid(long pid) {
        this.pid = pid;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
