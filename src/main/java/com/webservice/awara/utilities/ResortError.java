package com.webservice.awara.utilities;

import java.sql.Timestamp;

/**
 * Created by Vadim
 */
public class ResortError {
    private int o_id;
    private int e_type;
    private String e_desc;
    private int shop_id;

    private Timestamp date;
    private String desc;
    private double loss;

    public ResortError(int o_id, int e_type, String e_desc, Timestamp date, String desc, int shop_id, double loss) {
        this.o_id = o_id;
        this.e_type = e_type;
        this.e_desc = e_desc;
        this.date = date;
        this.desc = desc;
        this.shop_id = shop_id;
        this.loss = loss;
    }

    public ResortError(int o_id, Timestamp date, String desc) {
        this.o_id = o_id;
        this.date = date;
        this.desc = desc;
        this.e_type = -1;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getE_type() {
        return e_type;
    }

    public void setE_type(int e_type) {
        this.e_type = e_type;
    }

    public String getE_desc() {
        return e_desc;
    }

    public void setE_desc(String e_desc) {
        this.e_desc = e_desc;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }
}
