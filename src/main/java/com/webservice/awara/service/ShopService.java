package com.webservice.awara.service;

import com.webservice.awara.model.Shop;

import java.util.List;

/**
 * Created by Vadim
 */
public interface ShopService {

    Shop selectById(int _id);
    List<Shop> selectMultipleById(String _ids);
    List<Shop> selectAll();
}
