package zti.project.f1fantasy.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByEmail(String email);
    public List<User> findByFname(String fname);
    public List<User> findByLname(String lname);
    public List<User> findByAdminPrivileges(boolean adminPrivileges);
}
