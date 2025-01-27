package igorsdev.todolist.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name = "tb_users")
@Data
public class UserModel {

    @Id
    @GeneratedValue
    private UUID id;

    private  String username;
    private  String password;
}
