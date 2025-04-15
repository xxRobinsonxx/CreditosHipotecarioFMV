package com.mivivienda.creditos.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "creditos")
@Data
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de desembolso es obligatoria")
    private String fechaDesembolso;

    @NotNull(message = "El producto es obligatorio")
    private String producto;

    @NotNull(message = "El departamento es obligatorio")
    private String departamento;

    @NotNull(message = "La provincia es obligatoria")
    private String provincia;

    @NotNull(message = "El distrito es obligatorio")
    private String distrito;
//    @Id
    @NotNull(message = "El ubigeo es obligatorio")
    private String ubigeo;

    @NotNull(message = "El IFI es obligatorio")
    private String ifi;

    @NotNull(message = "El tipo de IFI es obligatorio")
    private String tipoIfi;

    @NotNull(message = "El monto de crédito es obligatorio")
    private Double montoCredito;

    @NotNull(message = "El monto de cuota inicial es obligatorio")
    private Double montoCuotaInicial;

    @NotNull(message = "El número de plazos es obligatorio")
    private Integer plazos;

    @NotNull(message = "La tasa es obligatoria")
    private Double tasa;

    @NotNull(message = "El monto del valor de la vivienda es obligatorio")
    private Double montoValorVivienda;

    @NotNull(message = "La fecha de corte es obligatoria")
    private String fechaCorte;
}
