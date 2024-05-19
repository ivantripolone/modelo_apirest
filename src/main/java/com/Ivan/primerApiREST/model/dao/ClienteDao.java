package com.Ivan.primerApiREST.model.dao;

import com.Ivan.primerApiREST.model.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
