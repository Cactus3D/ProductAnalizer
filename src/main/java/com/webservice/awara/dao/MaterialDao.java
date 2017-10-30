package com.webservice.awara.dao;

import com.webservice.awara.model.Material;

import java.util.List;

/**
 * Created by Vadim
 */
public interface MaterialDao {

    Material selectById(int id);
    List<Material> listMaterial();
}
