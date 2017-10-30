package com.webservice.awara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by Vadim
 */

@Entity
@Table(name = "q_types")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QType {

    @Id
    @Column(name = "q_type_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "description")
    private String description;

    //OneToMany(mappedBy = "quantity_type")

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
