package com.reto.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.pedidos.model.PedidoList;


import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class pedidoController {

    
    // @Autowired
    // private PedidoService pedidoService;

    // @GetMapping()
    // public Flux<PedidoList> obtenerTodosLosPedidos() {
    //     return pedidoService.ListarPedido() 
    //     .doOnError(e -> System.err.println("Error al obtener pedidos: " + e.getMessage()))
    //     .onErrorResume(e -> Flux.empty()) ;
    // }

    // @PostMapping
    // public Flux<Pedido> crearPedido(@RequestBody DetallePedido pedido) {


    //     return pedidoService
    //             .guardarPedido(pedido)
    //             .thenMany(pedidoRepository.findAll())
    //             // Manejo de errores
    //             .onErrorResume(e -> Flux.empty());
    // }

}
