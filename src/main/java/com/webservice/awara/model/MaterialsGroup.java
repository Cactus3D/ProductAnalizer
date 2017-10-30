package com.webservice.awara.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Vadim
 */

@Entity
@Table(name = "materials_groups")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaterialsGroup {

    @Id
    @Column(name = "m_group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "description")
    private String description;

    @OneToMany (mappedBy = "m_group")
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
        return "MaterialsGroup{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
