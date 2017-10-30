package com.webservice.awara.dao;

import com.webservice.awara.model.Shop;

import java.util.List;

/**
 * Created by Vadim
 */
public interface ShopDao {

    Shop selectById(int _id);
    List<Shop> selectMultipleById(List<Integer> list_id);
    List<Shop> selectAll();
}
