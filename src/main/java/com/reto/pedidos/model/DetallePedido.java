package com.reto.pedidos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

import lombok.Data;


@Document(collection = "detalles_pedidos")

@Data
public class DetallePedido {

    @Id
    private String id;
    private Integer numero;
    private String direccion_envio;
    private Integer cantidad; 
    private LocalDateTime fecha_envio;
    private String descripcion; 
    private LocalDateTime fecha_pedido;
}
