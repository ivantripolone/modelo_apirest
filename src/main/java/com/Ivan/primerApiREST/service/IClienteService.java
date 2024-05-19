package com.Ivan.primerApiREST.service;

import com.Ivan.primerApiREST.model.dto.ClienteDto;
import com.Ivan.primerApiREST.model.entities.Cliente;

import java.util.List;


public interface IClienteService {
    List<Cliente> listAlll();

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);
}
