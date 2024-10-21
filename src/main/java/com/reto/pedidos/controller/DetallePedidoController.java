package com.reto.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.pedidos.model.DetallePedido;
import com.reto.pedidos.repository.DetallePedidoRepository;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/detalles")
@CrossOrigin(origins = "http://localhost:4200")
public class DetallePedidoController {
    @Autowired
     DetallePedidoRepository dt;

   

    @PostMapping
    public Mono<DetallePedido> crearDetallePedido(@RequestBody DetallePedido detallePedido) {
        return dt.save(detallePedido);
    }
}
