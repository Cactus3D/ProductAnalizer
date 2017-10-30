package com.webservice.awara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim
 */
@Entity
@Table(name = "materials")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Material {

    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ean")
    private String ean;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "m_group")
    private MaterialsGroup m_group;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name = "m_type")
    private MaterialsType m_type;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "material")
    private List<Operation> operations = new ArrayList<Operation>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public MaterialsGroup getM_group() {
        return m_group;
    }

    public void setM_group(MaterialsGroup m_group) {
        this.m_group = m_group;
    }

    public MaterialsType getM_type() {
        return m_type;
    }

    public void setM_type(MaterialsType m_type) {
        this.m_type = m_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", ean='" + ean + '\'' +
                ", m_group=" + m_group +
                ", m_type=" + m_type +
                ", description='" + description + '\'' +
                '}';
    }
}
