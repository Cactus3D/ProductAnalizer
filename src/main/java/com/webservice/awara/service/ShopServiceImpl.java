package com.webservice.awara.service;

import com.webservice.awara.dao.OperationDao;
import com.webservice.awara.dao.ShopDao;
import com.webservice.awara.model.Shop;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim
 */

@Repository
public class ShopServiceImpl implements ShopService {

    private ShopDao shopDao;

    @Transactional
    public void setShopDao(ShopDao shopDao) {this.shopDao = shopDao;}

   public Shop selectById(int _id){
       return this.shopDao.selectById(_id);
   }
   public List<Shop> selectMultipleById(String _ids){

       String[] ids_array = _ids.split(",");
       List<Integer> shops_id_list = new ArrayList<>();

       for(String s : ids_array){
           shops_id_list.add(Integer.parseInt(s));
       }
       return this.shopDao.selectMultipleById(shops_id_list);
   }

   @Transactional
   public List<Shop> selectAll(){

       return this.shopDao.selectAll();
   }
}
