package co.com.mueblessas.model.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stat {

    private LocalDateTime timestamp;
    private Integer totalContactoClientes;
    private Integer motivoReclamo;
    private Integer motivoGarantia;
    private Integer motivoDuda;
    private Integer motivoCompra;
    private Integer motivoFelicitaciones;
    private Integer motivoCambio;
    private String hash;
}
