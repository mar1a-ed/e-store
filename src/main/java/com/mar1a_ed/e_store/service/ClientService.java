package com.mar1a_ed.e_store.service;

import com.mar1a_ed.e_store.exception.ClientNotFoundException;
import com.mar1a_ed.e_store.exception.NoClientsRegisterException;
import com.mar1a_ed.e_store.exception.UserNotFoundException;
import com.mar1a_ed.e_store.model.entity.Client;
import com.mar1a_ed.e_store.model.entity.User;
import com.mar1a_ed.e_store.repository.ClientRepository;
import com.mar1a_ed.e_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public Client create(Long userId, Client client){
        try{
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new UserNotFoundException(String.format("User {id=%s} not found",userId))
            );

            client.setUser(user);
            clientRepository.save(client);
            return client;
        }catch (Exception e){
            throw new RuntimeException("Error. Unable to register the client. Please check the fields");
        }
    }

    public Client findByCode(String code){
        try{
            Client client = clientRepository.findByCode(code);
            return client;
        }catch (Exception e){
            throw new ClientNotFoundException(String.format("Client {code=%s} not found", code));
        }
    }

    public Client findByFullName(String fullName){
        try{
            Client client = clientRepository.findByName(fullName);
            return client;
        }catch (Exception e){
            throw new ClientNotFoundException(String.format("Client {name=%s} not found", fullName));
        }
    }

    public List<Client> findByNameContains(String name){
        try{
            List<Client> clients = clientRepository.findClientByNameContaining(name);
            return clients;
        }catch (Exception e){
            throw new NoClientsRegisterException(String.format("No clients register with name=%s",name));
        }
    }

    public List<Client> findAll(){
        try{
            List<Client> clients = clientRepository.findAll();
            return clients;
        }catch (Exception e){
            throw new NoClientsRegisterException("No clients register exception");
        }
    }

    public void updateAddress(String name, String currentAddress, String newAddress){
        Client client = clientRepository.findByName(name);

        if(!client.getAddress().equals(currentAddress)){
            throw new RuntimeException("The field 'current address' does not match with the Current Address of the client.");
        }

        client.setAddress(newAddress);
        clientRepository.save(client);
    }

    public void deleteClient(String name){
        Client client = clientRepository.findByName(name);
        clientRepository.deleteClientByCode(client.getCode());
    }
}
