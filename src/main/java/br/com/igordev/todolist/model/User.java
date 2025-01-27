package br.com.igordev.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name = "tb_users")
@Data
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String password;
}
