package com.webservice.awara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim
 */
@Entity
@Table(name = "shops")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shop {

    @Id
    @Column(name = "shop_id")
    private int id;

    @OneToMany(mappedBy = "shop")
    @JsonIgnore private List<Operation> operations = new ArrayList<Operation>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                '}';
    }
}
