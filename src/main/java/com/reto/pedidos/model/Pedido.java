package com.reto.pedidos.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Table("pedido")
@Data
public class Pedido {
    
    @Id
    private  Integer idpedido;
    private LocalDateTime fecha;
    private String descripcion;
}
