package br.com.giovanederenevick.cars.services;

import br.com.giovanederenevick.cars.dtos.UserDTO;
import br.com.giovanederenevick.cars.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }
}
