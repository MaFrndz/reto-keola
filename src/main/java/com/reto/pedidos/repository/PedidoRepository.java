package com.reto.pedidos.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.reto.pedidos.model.Pedido;

public interface PedidoRepository extends ReactiveCrudRepository <Pedido, Long>{
    
}
