package com.example.servicio_item.dao;

import com.example.servicio_item.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {
    @Autowired
    //Quiero mapear el nombre del eureka con restTemplate
    RestTemplate clienteRest;
    public List<Product> listar(){
        //RestTemplate restTemplate = new RestTemplate();
        Product[] productsArray =
                //obtengo la dirección -- forma normal
                //restTemplate.getForObject("http://localhost:8001/productos", Product[].class);
        clienteRest.getForObject("http://servicio-producto/productos", Product[].class);
        return Arrays.asList(productsArray); //lo convierte a lista.
    }
    public Product obtenPorID(int id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://servicio-producto/productos/"+id;
        Product product =
                //obtengo de la dirección
                restTemplate.getForObject(url, Product.class);
        return product; //lo convierte a lista.
    }
}
