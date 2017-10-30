package com.webservice.awara.service;

import com.webservice.awara.dao.MaterialDao;
import com.webservice.awara.dao.OperationDao;
import com.webservice.awara.model.Operation;
import com.webservice.awara.model.Shop;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Vadim
 */
@Service
public class OperationServiceImpl implements OperationService {

    private OperationDao operationDao;
    private ShopService shopService;

    @Transactional
    public void setOperationDao(OperationDao operationDao) {this.operationDao = operationDao;}

    @Autowired(required = true)
    @Qualifier(value = "shopService")
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @Transactional
    public List<Operation> selectByDateAbove(Timestamp _date){
        return this.operationDao.selectByDateAbove(_date);
    }

    @Transactional
    public List<Operation> selectByDateBelow(Timestamp _date){
        return this.operationDao.selectByDateBelow(_date);
    }

    @Transactional
    public List<Operation> selectByDate(Timestamp _date){
        return this.operationDao.selectByDate(_date);
    }

    @Transactional
    public List<Operation> selectByMaterialId(int _material_id){
        return this.operationDao.selectByMaterialId(_material_id);
    }

    @Transactional
    public Operation selectById(int _id){
        return this.operationDao.selectById(_id);
    }

    @Transactional
    public List<Operation> selectByType(int _operation_type){
        return this.operationDao.selectByType(_operation_type);
    }

    @Transactional
    public List<Operation> selectByShopId(int shop_id)
    {
        return this.operationDao.selectByShopId(shop_id);
    }

    @Transactional
    public List<Operation> selectByMaterialAndShop(int material_id, int shop_id)
    {
        return this.operationDao.selectByMaterialAndShop(material_id,shop_id);
    }

    @Transactional
    public List<Operation> selectByDateAndShop(String start_date, String end_date, String shop_id){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date s_date = sdf.parse(start_date);
            Date e_date = sdf.parse(end_date);
            Timestamp start_period = new Timestamp(s_date.getTime());
            Timestamp end_period = new Timestamp(e_date.getTime());

            List<Shop> shop_list = shopService.selectMultipleById(shop_id);
            return this.operationDao.selectByDateAndShop(start_period, end_period, shop_list);
        }
        catch (ParseException pe){
            System.err.println(pe.getStackTrace());
            return null;
        }
    }

    @Transactional
    public Map<Integer,List<Operation>> selectByDateAndShopAndType(String start_date, String end_date, String shop_id, String type){
        List<Operation> all_oper = selectByDateAndShop(start_date,end_date,shop_id);
        List<Operation> type_oper = new ArrayList<>();
        Map<Integer,List<Operation> > shop_by_id = new HashMap<>();
        for(Operation op : all_oper){
            if(op.getOperation_type().getId().equals(type)){
                type_oper.add(op);
                int s_id = op.getShop().getId();
                if(shop_by_id.containsKey(s_id)){
                    shop_by_id.get(s_id).add(op);
                }
                else {
                    List<Operation> o_list = new ArrayList<>();
                    o_list.add(op);
                    shop_by_id.put(op.getShop().getId(),o_list);
                }
            }
        }
        //System.out.println(type_oper);
        return shop_by_id;
    }

    @Override
    public List<Operation> sortListByDateAsc(List<Operation> target) {
        target.sort(Comparator.comparing(Operation::getDate));
        return target;
    }


}
