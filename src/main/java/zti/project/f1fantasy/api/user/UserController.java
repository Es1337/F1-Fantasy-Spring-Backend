package zti.project.f1fantasy.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping(path = "email")
    public List<User> getUserWithEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping(path = "admin")
    public List<User> getAdmins(){
        return userService.getAdmins();
    }

    @PostMapping
    public User postUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping(path = "{oldUserId}")
    public User updateUserById(@RequestBody User newUser, @PathVariable Long oldUserId){
        return userService.updateUserById(newUser, oldUserId);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }

}
