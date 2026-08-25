package com.devluiz.dscommerce.services;

import com.devluiz.dscommerce.entities.User;
import com.devluiz.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Coloque um System.out aqui para testarmos se o Spring está chamando esse método!
        System.out.println("Buscando usuário pelo email: " + username);

        User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("Email nao encontrado.");
        }
        return (UserDetails) user;
    }




}
