package com.reto.pedidos.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.reto.pedidos.model.DetallePedido;

public interface DetallePedidoRepository extends ReactiveMongoRepository <DetallePedido, String>{
    
}
