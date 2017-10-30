package com.webservice.awara.utilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;

/**
 * Created by Vadim
 */
public class Losses {

    private int o_id;
    private int l_type;
    private double loss;
    private Timestamp date;
    private String desc;
    private int shop_id;


    public Losses() {
    }

    public Losses(int o_id, int l_type, double loss, Timestamp date, String desc, int shop_id) {
        this.o_id = o_id;
        this.l_type = l_type;
        this.loss = loss;
        this.date = date;
        this.desc = desc;
        this.shop_id = shop_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getL_type() {
        return l_type;
    }

    public void setL_type(int l_type) {
        this.l_type = l_type;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
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

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}
