package com.reto.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.pedidos.model.Pedido;
import com.reto.pedidos.repository.PedidoRepository;
import reactor.core.publisher.Flux;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pedidos")
public class pedido {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping()
    public Flux<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository
                .findAll()
                .onErrorResume(e -> {
                    // Manejo de errores
                    System.err.println("Error al obtener pedidos: " + e.getMessage());
                    return Flux.empty();
                });
    }

    @PostMapping
    public Flux<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return pedidoRepository
                .save(pedido)
                .thenMany(pedidoRepository.findAll())
                // Manejo de errores
                .onErrorResume(e -> Flux.empty());
    }

}
