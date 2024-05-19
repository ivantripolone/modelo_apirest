package com.Ivan.primerApiREST.controller;

import com.Ivan.primerApiREST.model.dto.ClienteDto;
import com.Ivan.primerApiREST.model.entities.Cliente;
import com.Ivan.primerApiREST.payload.MensajeResponse;
import com.Ivan.primerApiREST.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @PostMapping("cliente")
    public ResponseEntity create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);
            String message = "Guardado correctamente";
            return getMensajeResponseEntity(message, getClienteDto(clienteSave), HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return getMensajeResponseEntity(exDt.getMessage(), null, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }




    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
        Cliente clienteUpdate = null;
        try {
            if (clienteService.existsById(id)) {
                clienteDto.setIdCliente(id);
                clienteUpdate = clienteService.save(clienteDto);
                String message = "Guardado correctamente";
                return getMensajeResponseEntity(message, getClienteDto(clienteUpdate), HttpStatus.CREATED);
            } else {
                String message = "El registro que intenta actualizar no se encuentra en la base de datos.";
                return getMensajeResponseEntity(message, null, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return getMensajeResponseEntity(exDt.getMessage(), null, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    @DeleteMapping("cliente/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt) {
            return getMensajeResponseEntity(exDt.getMessage(), null, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("clientes")
    public ResponseEntity<?> showAll() {
        List<Cliente> getList = clienteService.listAlll();
        if (getList == null) {
            String message= "No hay registros";
            return getMensajeResponseEntity(message, null, HttpStatus.OK);
        }
        return getMensajeResponseEntity("", getList, HttpStatus.OK);
    }


    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);

        if (cliente == null) {
            String message = "El registro que intenta buscar, no existe!!";
            return getMensajeResponseEntity(message, null, HttpStatus.NOT_FOUND);
        }

        return getMensajeResponseEntity("", getClienteDto(cliente), HttpStatus.OK);
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

    private static ResponseEntity<MensajeResponse> getMensajeResponseEntity(String message, Object objet, HttpStatus status) {
        MensajeResponse mensajeResponse = MensajeResponse.builder().mensaje(message).object(objet).build();
        return new ResponseEntity<>(mensajeResponse, status);
    }

}
