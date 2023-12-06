package com.example.servicio_item.dao;

import com.example.servicio_item.entity.Heroe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="servicio-heroes")
public interface ServicioHeroesFeign {
    @GetMapping("/hola")
    Heroe listarHeroe();
}
