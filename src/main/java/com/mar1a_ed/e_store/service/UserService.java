package com.mar1a_ed.e_store.service;

import com.mar1a_ed.e_store.exception.EmailUniqueViolationException;
import com.mar1a_ed.e_store.exception.NoUsersRegisterException;
import com.mar1a_ed.e_store.exception.UserNotFoundException;
import com.mar1a_ed.e_store.model.entity.User;
import com.mar1a_ed.e_store.model.enums.Role;
import com.mar1a_ed.e_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User create(User user){
        try{
            if(user.getRole() == null){
                user.setRole(Role.ROLE_CLIENT);
            }
            return userRepository.save(user);
        }catch (Exception e){
            throw new EmailUniqueViolationException(String.format("Email {%s} already register.", user.getEmail()));
        }
    }

    public User findById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User {id=%s} not found", id))
        );

        return user;
    }

    public User findByEmail(String email){
        try{
            User user = userRepository.findByEmail(email);
            return user;
        }catch (Exception e){
            throw new UserNotFoundException(String.format("User {email=%s} not found"));
        }
    }

    public List<User> findAll(){
        try{
            List<User> users = userRepository.findAll();
            return users;
        }catch (Exception e){
            throw new NoUsersRegisterException("No Users Register");
        }
    }

    public void updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User {id=%s} not found", id))
        );

        if(!newPassword.equals(confirmPassword)){
            throw new RuntimeException("The 'newPassword' does not match with 'confirmPassword'");
        }

        if(user.getPassword() != currentPassword){
            throw new RuntimeException("The 'currentPassword' does not match with the Current Password");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
