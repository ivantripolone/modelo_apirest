package com.Ivan.apiREST.controller;
import com.Ivan.apiREST.exception.BadRequestException;
import com.Ivan.apiREST.exception.ResourceNotFoundException;
import com.Ivan.apiREST.model.dto.ClienteDto;
import com.Ivan.apiREST.model.entities.Cliente;
import com.Ivan.apiREST.payload.ApiResponse;

import com.Ivan.apiREST.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(ClienteController.STRING)
public class ClienteController {
    public static final String STRING = "/api/v1/";
    private static final String URL_CLIENTE = "cliente/";
    private final IClienteService clienteService;
    @Autowired
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("cliente")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);
            String message = "Guardado correctamente";
            return getMensajeResponseEntity(message, getClienteDto(clienteSave),STRING+ URL_CLIENTE +clienteSave.getIdCliente(), HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }




    @PutMapping("cliente/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid ClienteDto clienteDto, @PathVariable Integer id) {
        Cliente clienteUpdate = null;
        try {
            if (clienteService.existsById(id)) {
                clienteDto.setIdCliente(id);
                clienteUpdate = clienteService.save(clienteDto);
                String message = "Guardado correctamente";
                return getMensajeResponseEntity(message, getClienteDto(clienteUpdate),STRING+ URL_CLIENTE + id, HttpStatus.CREATED);
            } else {
                throw new ResourceNotFoundException("cliente", "id", id);
            }
        } catch (DataAccessException exDt) {
            throw  new BadRequestException(exDt.getMessage());
        }
    }


    @DeleteMapping("cliente/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            String message= "Se elimino correctamente el cliente del id: " + id;
            return getMensajeResponseEntity(message,clienteDelete,STRING+ URL_CLIENTE +id, HttpStatus.NO_CONTENT);
        }
        catch (DataAccessException exDt) {
        throw  new BadRequestException(exDt.getMessage());
    }
    }

    @GetMapping("clientes")
    public ResponseEntity<ApiResponse> showAll() {
        List<Cliente> getList = clienteService.listAlll();
        if (getList == null) {
            throw new ResourceNotFoundException("clientes");

        }
        return getMensajeResponseEntity("", getList,STRING+"clientes", HttpStatus.OK);
    }


    @GetMapping("cliente/{id}")
    public ResponseEntity<ApiResponse> showById(@PathVariable Integer id) {
        Cliente clienteActual = clienteService.findById(id);

        if (clienteActual == null) {
            throw  new ResourceNotFoundException("cliente", "id", id);
        }

        return getMensajeResponseEntity("", getClienteDto(clienteActual),STRING+ URL_CLIENTE + id , HttpStatus.OK);
    }


    private static ClienteDto getClienteDto(Cliente cliente) {
        return ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .correo(cliente.getCorreo())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

    private static ResponseEntity<ApiResponse> getMensajeResponseEntity(String message, Object objet, String url, HttpStatus status) {
        ApiResponse mensajeResponse = new ApiResponse(message, url,objet);
        return new ResponseEntity<>(mensajeResponse, status);
    }

}
