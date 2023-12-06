package com.example.servicio_item.controller;

import com.example.servicio_item.dao.ItemDao;
import com.example.servicio_item.dao.ProductDao;
import com.example.servicio_item.dao.ProductRestFeign;
import com.example.servicio_item.entity.Item;
import com.example.servicio_item.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ProductDao productDao;
    ItemDao itemDao;
    ProductRestFeign productRestFeign;

    @GetMapping("/items")
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

    @GetMapping("/items/{id}")
    public Item obtenerID(@PathVariable("id") int id){
        Product product = productDao.obtenPorID(id);
        Item item = new Item();
        item.setProduct(product);

        item.setCantidad(Math.round(Math.random() * 10));
        return item;
    }

    @GetMapping("/itemsF")
    public List<Item> listarFeign(){
        List<Item> items = new ArrayList<>();

        for(Product p: productRestFeign.listar()){
            Item item = new Item();
            item.setProduct(p);
            item.setCantidad(Math.round(Math.random() * 10));
            items.add(item);
        }
        return items;
    }

    @GetMapping("/itemsF/{id}")
    public Item obtenerIdFeign(@PathVariable("id") int id){
        Product product = productRestFeign.buscarPorId(id);
        Item item = new Item();
        item.setProduct(product);
        item.setCantidad(Math.round(Math.random() * 10));
        return item;
    }
}
