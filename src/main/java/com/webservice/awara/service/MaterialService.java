package com.webservice.awara.service;

import com.webservice.awara.model.Material;

import java.util.List;

/**
 * Created by Vadim
 */
public interface MaterialService {

    Material selectById(int id);
    List<Material> listMaterial();
}
