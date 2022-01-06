package br.com.giovanederenevick.cars.api;

import br.com.giovanederenevick.cars.domains.User;
import br.com.giovanederenevick.cars.dtos.UserDTO;
import br.com.giovanederenevick.cars.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> get() {

        List<UserDTO> userDTOList = userService.getUsers();

        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/info")
    public UserDTO userInfo(@AuthenticationPrincipal User user) {
        return UserDTO.create(user);
    }
}
