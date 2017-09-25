package com.examples.weibao.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/9/25.
 */
@Entity
public class PreferencesBean {
    @Id
    @NotNull
    private Long id;
    private String account;
    private String password;
    @Generated(hash = 35222024)
    public PreferencesBean(@NotNull Long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }
    @Generated(hash = 1826828975)
    public PreferencesBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccount() {
        return this.account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
