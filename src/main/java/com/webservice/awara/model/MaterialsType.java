package com.webservice.awara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim
 */
@Entity
@Table(name = "materials_types")
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaterialsType {

    @Id
    @Column(name = "m_type_id")
    private int id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "m_type")
    @JsonIgnore
    private List<Material> materials = new ArrayList<Material>();

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

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "MaterialsType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
