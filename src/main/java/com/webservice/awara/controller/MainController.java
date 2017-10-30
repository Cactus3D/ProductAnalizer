package com.webservice.awara.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.webservice.awara.model.Material;
import com.webservice.awara.model.Operation;
import com.webservice.awara.model.Shop;
import com.webservice.awara.service.MaterialService;
import com.webservice.awara.service.OperationService;
import com.webservice.awara.service.ShopService;
import com.webservice.awara.utilities.Losses;
import com.webservice.awara.utilities.ResortError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLOutput;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Vadim
 */

@RestController
public class MainController {

    private MaterialService materialService;
    private OperationService operationService;
    private ShopService shopService;

    @Autowired(required = true)
    @Qualifier(value = "materialService")
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Autowired(required = true)
    @Qualifier(value = "operationService")
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    @Autowired(required = true)
    @Qualifier(value = "shopService")
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public List<Material> enter() {
        List<Material> mt = materialService.listMaterial();

        String str = "";
        for (Material m : mt) {
            str += m.toString();
        }
        return mt;
    }

    @RequestMapping(value = "/getStats", method = RequestMethod.GET)
    public List<Losses> getStats(HttpServletRequest request, HttpServletResponse response) {

        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        String shop = request.getParameter("shop_id");

        if(shop.isEmpty()){
            shop = setAllShops();
        }

        List<Operation> operations_list = this.operationService.selectByDateAndShop(start_date,end_date,shop);


        //int material_id = 10000832;
        //int shop_id = Integer.parseInt(shop);'

        List<Losses> losses_list = new ArrayList<>();

        for(Operation operation : operations_list){


            String oper_id = operation.getOperation_type().getId();

            double loss_value = 0;
            int loss_type;

            switch (oper_id) {

                //Излишек по инвентаризации
                case "701":
                    System.out.println(operation);
                    loss_value = operation.getAverage_price() * 0.2;
                    loss_type = 1;
                    break;

                //Недостача по интвентаризации
                case "702":
                    System.out.println(operation);
                    loss_value = operation.getAverage_price() * 0.1;
                    loss_type = 1;
                    break;

                //Списание порчи
                case "950":
                    loss_value = operation.getAverage_price();
                    loss_type = 2;
                    break;

                //Списание порчи готовой продукции
                case "951":
                    loss_value = operation.getAverage_price();
                    loss_type = 3;
                    break;

                //Списание на собственные нужды
                case "952":
                    loss_value = operation.getAverage_price();
                    loss_type = 4;
                    break;

                case "309":
                default:
                    continue;
            }

            losses_list.add(new Losses(operation.getId(),
                    loss_type,
                    loss_value,
                    operation.getDate(),
                    operation.getMaterial().
                            getDescription(),
                    operation.getShop().getId()));


        }
        //}

        response.setHeader("Access-Control-Allow-Origin", "*");
        return losses_list;

    }

    @RequestMapping(value = "/getShops", method = RequestMethod.GET)
    public ArrayList<Integer> getShops(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Shop> shop_list = shopService.selectAll();
        ArrayList<Integer> int_id_shop = new ArrayList<>();
        for(Shop s : shop_list){
            int_id_shop.add(s.getId());
        }
        return int_id_shop;

    }

    @RequestMapping(value = "/getResort", method = RequestMethod.GET)
    public List<ResortError> getResort(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");

        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        String shop = request.getParameter("shop_id");

        if(shop.isEmpty()){
            shop = setAllShops();
        }

        List<ResortError> result = new ArrayList<>();

        Map<Integer,List<Operation> > shop_by_id = this.operationService.selectByDateAndShopAndType(start_date,end_date,shop,"309");
        for(Object key : shop_by_id.keySet()) {
            List<Operation> pool = shop_by_id.get(key);

            pool = this.operationService.sortListByDateAsc(pool);

            while (pool.size() > 1) {
                Operation target = pool.get(0);
                pool.remove(0);

                ResortError re = new ResortError(target.getId(), target.getDate(), target.getMaterial().getDescription());

                boolean ch_flag = false;
                Operation buff = null;

                for (Operation op :
                        pool) {
                    re.setShop_id(op.getShop().getId());
                    if (!op.getDate().equals(target.getDate())) {
                        break;
                    }
                    if (!op.getShop().equals(target.getShop())) {
                        continue;
                    }
                    if (Math.round(op.getQuantity() * 100 / 100) == Math.round(target.getQuantity() * 100 / 100)) {
                        if (op.getMaterial().equals(target.getMaterial())) {
                            ch_flag = true;
                            re.setLoss(target.getAverage_price() - op.getAverage_price());
                            re.setE_type(99);
                            re.setE_desc("Probably uncorrect using");
                            buff = op;
                            if (Math.round(op.getAverage_price() * 100 / 100) == 0 &&
                                    Math.round(target.getAverage_price() * 100 / 100) == 0) {
                                ch_flag = true;
                                re.setLoss(0);
                                re.setE_type(1);
                                re.setE_desc("Uncorrect using");
                                pool.remove(op);
                                break;
                            }
                        } else {
                            if (Math.round(op.getAverage_price() * 100 / 100) == Math.round(target.getAverage_price() * 100 / 100)) {
                                re.setLoss(0);
                                re.setE_type(-1);
                                re.setE_desc("Correct");
                            } else {
                                re.setLoss(target.getAverage_price() - op.getAverage_price());
                                re.setE_type(2);
                                re.setE_desc("Loss");
                            }
                            ch_flag = true;
                            pool.remove(op);
                            break;
                        }
                    }
                }
                if (!ch_flag) {
                    re.setLoss(target.getAverage_price());
                    re.setE_type(0);
                    re.setE_desc("Without pair");
                }
                if (re.getE_type() != -1) {
                    if (re.getE_type() == 99) {
                        re.setE_type(1);
                        re.setE_desc("Uncorrect using");
                        pool.remove(buff);
                    }
                    result.add(re);
                }
            }
            if (!pool.isEmpty()) {
                Operation op = pool.get(0);
                result.add(new ResortError(op.getId(),
                        3,
                        "Cannot find at this period",
                        op.getDate(),
                        op.getMaterial().getDescription(),
                        op.getShop().getId(),
                        op.getAverage_price()));
            }
        }
        return result;

    }

    private String setAllShops(){

        String str = "";
            List<Shop> shop_list =  shopService.selectAll();

            for(Shop s : shop_list){
                str += s.getId() + ",";
            }
            str = str.substring(0, str.length() - 1);
        return str;
    }
}

