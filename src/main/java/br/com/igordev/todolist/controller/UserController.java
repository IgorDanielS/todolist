package br.com.igordev.todolist.controller;

import br.com.igordev.todolist.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/create")
    public void create(@RequestBody User user) {
        System.out.println(user);
    }
}
