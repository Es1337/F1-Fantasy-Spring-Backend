package zti.project.f1fantasy.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.ranking.Ranking;
import zti.project.f1fantasy.api.ranking.RankingRepository;
//import zti.project.f1fantasy.api.ranking.RankingService;
import zti.project.f1fantasy.api.season.SeasonService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
//    private final RankingService rankingService;
    private final RankingRepository rankingRepository;
    private final SeasonService seasonService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RankingRepository rankingRepository, SeasonService seasonService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rankingRepository = rankingRepository;
//        this.rankingService = rankingService;
        this.seasonService = seasonService;
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
        if(userRepository.findByEmail( user.getEmail() ).size() > 0)
            return new User();

        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setAdminPrivileges(false);
        User result = userRepository.save(user);

        Ranking newRanking = new Ranking();
        newRanking.setUser(result);
        newRanking.setSeason(seasonService.getSeasonById(seasonService.getCurrentSeasonId()));
        newRanking.setPoints(0);
        rankingRepository.save(newRanking);

        return result;
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
        User userToDelete = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
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
