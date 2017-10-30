package com.webservice.awara.dao;

import com.webservice.awara.model.Operation;
import com.webservice.awara.model.Shop;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Vadim
 */
public interface OperationDao {

    List<Operation> selectByDateAbove(Timestamp _date);

    List<Operation> selectByDateBelow(Timestamp _date);

    List<Operation> selectByDate(Timestamp _date);

    List<Operation> selectByMaterialId(int _material_id);

    Operation selectById(int _id);

    List<Operation> selectByType(int _operation_type);

    List<Operation> selectByShopId(int shop_id);

    List<Operation> selectByMaterialAndShop(int material_id, int shop_id);

    List<Operation> selectByDateAndShop(Timestamp start_date, Timestamp end_date, List<Shop> shop_id);
    List<Operation> selectByDateAndShop(Timestamp start_date, Shop shop, double quantity);

}
