package zti.project.f1fantasy.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public List<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAdmins(){
        return userRepository.findByAdminPrivileges(true);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUserById(User newUser, Long oldUserId){
        return userRepository.findById(oldUserId).map(user -> {
            user.setEmail(newUser.getEmail());
            user.setFname(newUser.getFname());
            user.setLname(newUser.getLname());
            user.setPass(newUser.getPass());
            user.setAdminPrivileges(newUser.isAdminPrivileges());

            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setId(oldUserId);
            return userRepository.save(newUser);
        });
    }

    public void deleteUserById(Long userId){
        User userToDelete = userRepository.findById(userId).get();
        userRepository.delete(userToDelete);
    }
}
