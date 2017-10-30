package com.webservice.awara.service;

import com.webservice.awara.dao.MaterialDao;
import com.webservice.awara.model.Material;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vadim
 */

@Service
public class MaterialServiceImpl implements MaterialService {

    private MaterialDao materialDao;

    public void setMaterialDao(MaterialDao materialDao) {this.materialDao = materialDao;}

    @Transactional
    public Material selectById(int id){
        return this.materialDao.selectById(id);
    }

    @Transactional
    public List<Material> listMaterial(){
        return this.materialDao.listMaterial();
    }


}
