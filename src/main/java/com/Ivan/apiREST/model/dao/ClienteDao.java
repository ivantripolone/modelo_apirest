package com.Ivan.apiREST.model.dao;

import com.Ivan.apiREST.model.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
