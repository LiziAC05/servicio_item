package com.example.servicio_item.dao;

import com.example.servicio_item.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name="servicio-producto")
public interface ProductRestFeign {
    @GetMapping("/productos")
    List<Product> listar();

    @GetMapping("/productos/{id}")
    Product buscarPorId(@PathVariable int id);
}
