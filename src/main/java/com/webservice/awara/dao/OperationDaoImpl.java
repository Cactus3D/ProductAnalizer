package com.webservice.awara.dao;

import com.webservice.awara.model.Material;
import com.webservice.awara.model.Operation;
import com.webservice.awara.model.OperationType;
import com.webservice.awara.model.Shop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Vadim
 */

@Repository
@SuppressWarnings("unchecked")
public class OperationDaoImpl implements OperationDao  {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MaterialDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Operation> selectByDateAbove(Timestamp _date){
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Operation>)session
                .createQuery("from Operation where date <: date_param")
                .setParameter("date_param", _date)
                .list();
    }

    public List<Operation> selectByDateBelow(Timestamp _date){
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Operation>)session
                .createQuery("from Operation where date >: date_param")
                .setParameter("date_param", _date)
                .list();
    }

    public List<Operation> selectByDate(Timestamp _date){
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Operation>)session
                .createQuery("from Operation where date=: date_param")
                .setParameter("date_param", _date)
                .list();
    }

    public List<Operation> selectByMaterialId(int _material_id){
        Session session = this.sessionFactory.getCurrentSession();
        Material selected_material = (Material)session.createQuery("from Material where id=:_id")
                .setParameter("_id", _material_id)
                .uniqueResult();
        return (List<Operation>) session.createQuery("from Operation where material=:_mt")
                .setParameter("_mt", selected_material)
                .list();

    }

    public Operation selectById(int _id){
        Session session = this.sessionFactory.getCurrentSession();
        return (Operation) session.createQuery("from Operation where id=:_id")
                .setParameter("_id", _id)
                .uniqueResult();
    }

    public List<Operation> selectByType(int _operation_type_id){
        Session session = this.sessionFactory.getCurrentSession();
        OperationType selected_operation_type = (OperationType) session.createQuery("from OperationType where id=:_operation_type_id")
                .setParameter("_operation_type_id", _operation_type_id)
                .uniqueResult();

        return (List<Operation>) session.createQuery("from Operation where operation_type=:op_type")
                .setParameter("op_type",selected_operation_type)
                .list();
    }

    public List<Operation> selectByShopId(int shop_id){
        Session session = this.sessionFactory.getCurrentSession();

        Shop selected_shop = (Shop) session.createQuery("from Shop where id=:shop_id")
                .setParameter("shop_id", shop_id)
                .uniqueResult();

        return (List<Operation>) session.createQuery("from Operation where shop=:shop")
                .setParameter("shop",selected_shop)
                .list();

    }

    public List<Operation> selectByMaterialAndShop(int material_id, int shop_id){

        Session session = this.sessionFactory.getCurrentSession();

        Material selected_material = (Material) session.createQuery("from Material where id=:m_id")
                .setParameter("m_id", material_id)
                .uniqueResult();


        Shop selected_shop = (Shop) session.createQuery("from Shop where id=:s_id")
                .setParameter("s_id", shop_id)
                .uniqueResult();

        return (List<Operation>) session.createQuery("from Operation where material=:m_id and shop=:shop_id")
                .setParameter("m_id", selected_material)
                .setParameter("shop_id", selected_shop)
                .list();
    }

    public List<Operation> selectByDateAndShop(Timestamp start_date, Timestamp end_date, List<Shop> shop_id){
        Session session = this.sessionFactory.getCurrentSession();


        return (List<Operation>) session.createQuery("from Operation where shop in (:_shop) and date between :s_date and :e_date")
                .setParameter("s_date", start_date)
                .setParameter("e_date", end_date)
                .setParameterList("_shop", shop_id)
                .list();
    }

    @Override
    public List<Operation> selectByDateAndShop(Timestamp start_date, Shop shop, double quantity) {
        return null;
    }


}
