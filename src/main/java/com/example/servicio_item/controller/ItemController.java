package com.example.servicio_item.controller;

import com.example.servicio_item.dao.ItemDao;
import com.example.servicio_item.dao.ProductDao;
import com.example.servicio_item.dao.ProductRestFeign;
import com.example.servicio_item.dao.ServicioHeroesFeign;
import com.example.servicio_item.entity.Heroe;
import com.example.servicio_item.entity.Item;
import com.example.servicio_item.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ProductDao productDao;

    @Autowired
    ProductRestFeign productRestFeign;
    @Autowired
    ServicioHeroesFeign servicioHeroesFeign;

    //circuit breaker
    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

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
    public Item flujoAlternativo(){
        Item item = new Item();
        Product product = new Product();
        product.setProductName("alternativo");
        item.setProduct(product);
        item.setCantidad(0);
        return item;
    }
    //Definicion por anotaciones del Circuit Breaker
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "items2",
            fallbackMethod = "flujoAlternativo")
    /*Definicion por código del Circuit Breaker
    @GetMapping("/items/{id}")
    public Item obtenerID(@PathVariable("id") int id){

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("items");
        return circuitBreaker.run(() -> {
            //Código original
            Product product = productDao.obtenPorID(id);
            Item item = new Item();
            item.setProduct(product);
            item.setCantidad(Math.round(Math.random() * 10));
            return item;
        }, throwable -> flujoAlternativo());
    }*/

    @GetMapping("/items/{id}")
    public Item obtenerID(@PathVariable("id") int id){
            //Código original
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
    @GetMapping("/sh/")
    public Heroe listarHeroe(){
        return servicioHeroesFeign.listarHeroe();
    }
}
