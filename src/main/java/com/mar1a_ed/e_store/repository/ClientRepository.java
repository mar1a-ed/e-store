package com.mar1a_ed.e_store.repository;

import com.mar1a_ed.e_store.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCode(String code);

    Client findByName(String name);

    List<Client> findClientByNameContaining(String name);

    void deleteClientByCode(String code);
}
