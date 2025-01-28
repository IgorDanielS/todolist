package igorsdev.todolist.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel create(UserModel user) throws Exception{
        Optional<UserModel> userToFind = userRepository.findByUsername(user.getUsername());
        if(userToFind.isPresent()){
            throw new Exception("User already existed!");
        }
        var passwordHashred =  BCrypt.withDefaults()
        .hashToString(12, user.getPassword().toCharArray());

        user.setPassword(passwordHashred);
        return userRepository.save(user);
    }
}
