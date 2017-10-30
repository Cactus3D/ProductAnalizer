package com.webservice.awara.service;

import com.webservice.awara.model.Operation;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Vadim
 */
public interface OperationService {

    List<Operation> selectByDateAbove(Timestamp _date);

    List<Operation> selectByDateBelow(Timestamp _date);

    List<Operation> selectByDate(Timestamp _date);

    List<Operation> selectByMaterialId(int _material_id);

    Operation selectById(int _id);

    List<Operation> selectByType(int _operation_type);

    List<Operation> selectByShopId(int shop_id);

    List<Operation> selectByMaterialAndShop(int material_id, int shop_id);

    List<Operation> selectByDateAndShop(String start_date, String end_date, String shop_id);

    List<Operation> sortListByDateAsc(List<Operation> target);

    Map<Integer,List<Operation> > selectByDateAndShopAndType(String start_date, String end_date, String shop_id, String type);

}
