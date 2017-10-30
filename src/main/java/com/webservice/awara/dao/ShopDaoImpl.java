package com.webservice.awara.dao;

import com.webservice.awara.model.Operation;
import com.webservice.awara.model.Shop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vadim
 */

@Repository
@SuppressWarnings("unchecked")
public class ShopDaoImpl implements ShopDao{

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MaterialDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Shop selectById(int _id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Shop) session.createQuery("from Shop where id=:_id")
                .setParameter("_id", _id)
                .uniqueResult();
    }

    @Override
    public List<Shop> selectMultipleById(List<Integer> id_list) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println(id_list);
        List<Shop> shops_list =(List<Shop>) session.createQuery("from Shop where id in (:_ids)")
                .setParameterList("_ids", id_list).list();
        return shops_list;
    }

    @Override
    public List<Shop> selectAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Shop>) session.createQuery("from Shop").list();
    }
}
