package com.webservice.awara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Vadim
 */

@Entity
@Table(name = "operations")
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Operation {

    @Id
    @Column(name = "operation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id")
    private Material material;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_type")
    private OperationType operation_type;

    @Column(name = "q")
    private double quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "q_type")
    private QType quantity_type;

    @Column(name = "total_ave")
    private double average_price;

    @Column(name = "total")
    private double summ;

    @Column(name = "retailer")
    private Integer retailer_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "warehouse_id")
    private Integer warehouse_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public OperationType getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(OperationType operation_type) {
        this.operation_type = operation_type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public QType getQuantity_type() {
        return quantity_type;
    }

    public void setQuantity_type(QType quantity_type) {
        this.quantity_type = quantity_type;
    }

    public double getAverage_price() {
        return average_price;
    }

    public void setAverage_price(double average_price) {
        this.average_price = average_price;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public Integer getRetailer_id() {
        return retailer_id;
    }

    public void setRetailer_id(Integer retailer_id) {
        this.retailer_id = retailer_id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Integer getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(Integer warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date=" + date +
                ", material=" + material +
                ", operation_type=" + operation_type +
                ", quantity=" + quantity +
                ", quantity_type=" + quantity_type +
                ", average_price=" + average_price +
                ", summ=" + summ +
                ", retailer_id=" + retailer_id +
                ", shop=" + shop +
                ", warehouse_id=" + warehouse_id +
                '}';
    }

}
