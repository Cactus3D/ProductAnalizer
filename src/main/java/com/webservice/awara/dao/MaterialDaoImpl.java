package com.webservice.awara.dao;

import com.webservice.awara.model.Material;
import org.springframework.stereotype.Repository;

import org.hibernate.*;

import org.slf4j.Logger;

import java.util.List;

/**
 * Created by Vadim
 */

@Repository
public class MaterialDaoImpl implements MaterialDao {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MaterialDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Material selectById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Material selected_material =  (Material)session.createQuery("from Material where id=:_id")
                .setParameter("_id", id)
                .uniqueResult();
        logger.info("Selected material: " + selected_material);
        return selected_material;
    }

    public List<Material> listMaterial(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Material> materialList = (List<Material>) session.createQuery("from Material").list();
        logger.info("Material list: " + materialList);
        return materialList;
    }

}
