package com.examples.weibao.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class JianChaBean {


    /**
     * detection : 0
     * device : 0
     * items : [10001,10002]
     * menu : 0
     * plan : 0
     * total : 1
     */

    private int detection;
    private int device;
    private int menu;
    private int plan;
    private int total;
    private List<Integer> items;

    public int getDetection() {
        return detection;
    }

    public void setDetection(int detection) {
        this.detection = detection;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }
}
