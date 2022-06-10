package zti.project.f1fantasy.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPass(passwordEncoder.encode(user.getPass()));
        return userRepository.save(user);
    }

    public User updateUserById(User newUser, Long oldUserId){
        return userRepository.findById(oldUserId).map(user -> {
            user.setEmail(newUser.getEmail());
            user.setFname(newUser.getFname());
            user.setLname(newUser.getLname());
            user.setPass(passwordEncoder.encode(newUser.getPass()));
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get(0);
        if(user == null) throw new UsernameNotFoundException("User with email: " + email + " not found.");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user.isAdminPrivileges()) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(), authorities);
    }
}
