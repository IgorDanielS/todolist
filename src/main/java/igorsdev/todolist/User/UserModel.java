package igorsdev.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "tb_users")
@Data
public class UserModel {

    @Id
    @GeneratedValue
    private UUID id;

    private  String username;
    private  String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
