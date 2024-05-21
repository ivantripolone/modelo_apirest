package com.Ivan.apiREST.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ClienteDto implements Serializable {

    private Integer idCliente;
    @Size(min = 2, max = 25)
    @NotEmpty(message = "Nombre requerido!")
    private String nombre;
    @Size(min = 2, max = 25)
    @NotEmpty(message = "Apellido requerido!")
    private String apellido;
    @NotEmpty(message = "Correo requerido!")
    @Email
    private String correo;
    private Date fechaRegistro;
}
