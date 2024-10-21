package com.reto.pedidos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "DetallePedido")
@Getter
@Setter
@Data
public class DetallePedido {

    @Id
    private Integer id;
    private Integer numero;
    private String direccion_envio;
    private Integer cantidad; 
    private LocalDateTime fecha_envio;
    private String descripcion; 
    private LocalDateTime fecha_pedido;
}
