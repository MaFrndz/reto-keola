package com.reto.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.pedidos.model.DetallePedido;
import com.reto.pedidos.model.Pedido;
import com.reto.pedidos.model.PedidoList;
import com.reto.pedidos.repository.DetallePedidoRepository;
import com.reto.pedidos.repository.PedidoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    public Mono<PedidoList> guardarPedido(DetallePedido detallePedido) {
        // Fecha de guardado
        LocalDateTime fechaLocal = LocalDateTime.now();

        Pedido pedido = new Pedido();
        pedido.setDescripcion(detallePedido.getDescripcion());
        pedido.setFecha(fechaLocal);

        return pedidoRepository.save(pedido)
                .flatMap(savedPedido -> guardarDetalle(savedPedido, detallePedido)
                        .then(Mono.just(savedPedido)))
                .map( savedPedido -> {

                    detallePedido.setId(savedPedido.getIdpedido().toString());
                    detallePedido.setNumero(savedPedido.getIdpedido());

                    PedidoList pedidoList = new PedidoList();
                    pedidoList.setIdpedido(savedPedido.getIdpedido());
                    pedidoList.setDetalle(detallePedido);
                    return pedidoList; 
                })
                .onErrorResume(e -> {
                    // Manejo de errores
                    System.err.println("Error al guardar el pedido: " + e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<DetallePedido> guardarDetalle(Pedido pedido, DetallePedido detallePedido) {
        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(detallePedido.getCantidad());
        detalle.setDescripcion(detallePedido.getDescripcion());
        detalle.setDireccion_envio(detallePedido.getDireccion_envio());
        detalle.setFecha_envio(detallePedido.getFecha_envio());
        detalle.setFecha_pedido(pedido.getFecha());
        detalle.setId(pedido.getIdpedido().toString());
        detalle.setNumero(pedido.getIdpedido());

        return detallePedidoRepository.save(detalle)
                .doOnSuccess(savedDetalle -> {
                    // Registro exitoso, si es necesario
                    System.out.println("Detalle guardado: " + savedDetalle);
                })
                .onErrorResume(e -> {
                    // Manejo de errores
                    System.err.println("Error al guardar el detalle: " + e.getMessage());
                    return Mono.empty();
                });
    }

    public Flux<PedidoList> listarPedido() {
        return pedidoRepository.findAll()
                .flatMap(pedido -> detallePedidoRepository.findById(pedido.getIdpedido().toString())
                        .map(detalle -> {
                            PedidoList pedidoList = new PedidoList();
                            pedidoList.setIdpedido(pedido.getIdpedido());
                            pedidoList.setDetalle(detalle);
                            return pedidoList;
                        }));
    }
}
