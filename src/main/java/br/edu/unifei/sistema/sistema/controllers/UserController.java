package br.edu.unifei.sistema.sistema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.sistema.sistema.domain.User;
import br.edu.unifei.sistema.sistema.dto.UserDTO;
import br.edu.unifei.sistema.sistema.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserDTO>findAll(){
		
		return userService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public UserDTO findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
	
	@PutMapping(value = "/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody User userUpdate) {
        return userService.updateUser(id, userUpdate);
        //return ResponseEntity.ok(new UserDTO(userUpdate));
    }
}
