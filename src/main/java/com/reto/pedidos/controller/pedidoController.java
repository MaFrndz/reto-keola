package com.reto.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.pedidos.model.DetallePedido;
import com.reto.pedidos.model.Pedido;
import com.reto.pedidos.model.PedidoList;
import com.reto.pedidos.repository.DetallePedidoRepository;
import com.reto.pedidos.service.PedidoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class pedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private DetallePedidoRepository dt; 

    // @GetMapping()
    // public Flux<PedidoList> obtenerTodosLosPedidos() {
    // return pedidoService.ListarPedido()
    // .doOnError(e -> System.err.println("Error al obtener pedidos: " +
    // e.getMessage()))
    // .onErrorResume(e -> Flux.empty()) ;
    // }

    @PostMapping
    public Mono<Pedido> crearPedido(@RequestBody DetallePedido pedido) {

        return pedidoService
                .guardarPedido(pedido)
                .flatMap(savedPedido -> {
                    // Aquí puedes manejar el resultado guardado si es necesario
                    return Mono.just(savedPedido);
                })
                .onErrorResume(e -> {
                    // Manejo de errores
                    System.err.println("Error al guardar el pedido: " + e.getMessage());
                    return Mono.error(e); // O puedes devolver un Mono.error(e) si quieres propagar el error
                });
    }

}
