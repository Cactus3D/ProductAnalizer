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
@Table(name = "operations_types")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OperationType{

        @Id
        @Column(name = "o_type")
        private String id;

        @Column(name = "description")
        private String description;

        @OneToMany(mappedBy = "operation_type")
        @JsonIgnore
        private List<Operation> operations = new ArrayList<Operation>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
