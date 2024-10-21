package com.reto.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.pedidos.model.DetallePedido;
import com.reto.pedidos.model.Pedido;
import com.reto.pedidos.model.PedidoList;
import com.reto.pedidos.repository.PedidoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PeidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    // @Autowired
    // private DetallePedidoRepository detallePedidoRepository;

    // public Mono<Pedido> guardarPedido(DetallePedido detallePedido) {
    //     //fecha guardado
    //     LocalDateTime fechaLocal = LocalDateTime.now();

    //     Pedido  pedido = new Pedido();
    //     pedido.setDescripcion(detallePedido.getDescripcion());
    //     pedido.setFecha( fechaLocal);



    //     return pedidoRepository
    //             .save(pedido)
    //             .flatMap(savedPedido -> guardarDetalle(savedPedido, detallePedido)
    //                 .flatMap(result -> {
    //                     if (result) {
    //                         return Mono.just(savedPedido);
    //                     } else {
    //                         return Mono.error(new RuntimeException("Error al guardar el detalle del pedido"));
    //                     }
    //                 }))
    //             .onErrorResume(e -> Mono.empty());
    // }

    // public Flux<PedidoList>ListarPedido(){
    //     return pedidoRepository.findAll() // Obtienes todos los pedidos
    //     .flatMap(pedido -> detallePedidoRepository.findByPedidoId(pedido.getIdpedido()) // Busca el detalle por pedido ID
    //         .map(detalle -> {
    //             // Combinas el pedido y su detalle en un objeto PedidoList
    //             PedidoList pedidoList = new PedidoList();
    //             pedidoList.setIdpedido(pedido.getIdpedido());
    //             pedidoList.setDetalle(detalle); // Solo un detalle en una lista
    //             return pedidoList;
    //         })
    //     );
    // }

    // mongo
    private Mono<Boolean> guardarDetalle(Pedido pedido, DetallePedido detallePedido) {
    return Mono.fromCallable(() -> {
        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(detallePedido.getCantidad());
        detalle.setDescripcion(detallePedido.getDescripcion());
        detalle.setDireccion_envio(detallePedido.getDireccion_envio());
        detalle.setFecha_envio(detallePedido.getFecha_envio());
        detalle.setFecha_pedido(pedido.getFecha());
        detalle.setId(pedido.getIdpedido());  
        detalle.setNumero(pedido.getIdpedido());
        //detallePedidoRepository.save(detalle); 
        return true; 
    }).onErrorResume(e -> Mono.just(false));  // En caso de error, devuelve false
}

}
