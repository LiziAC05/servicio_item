package com.example.servicio_item.dao;

import com.example.servicio_item.entity.Item;
import com.example.servicio_item.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    final ProductDao productDao;


    public ItemDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Item> listar(){
        List<Item> items = new ArrayList<>();

        for(Product p: productDao.listar()){
            Item item = new Item();
            item.setProduct(p);
            item.setCantidad(Math.round(Math.random() * 10));
            items.add(item);
        }
        return items;
    }
}
