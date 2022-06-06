package zti.project.f1fantasy.api.user;

import zti.project.f1fantasy.api.ranking.Ranking;
import zti.project.f1fantasy.api.userprediction.UserPrediction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String email;
    private String fname;
    private String lname;
    private String pass;
    private boolean adminPrivileges;

    @OneToMany(mappedBy = "user")
    private Set<Ranking> rankings;

    @OneToMany(mappedBy = "user")
    private Set<UserPrediction> userPredictions;

    public User() {
    }

    public User(String email, String pass, boolean adminPrivileges) {
        this.email = email;
        this.pass = pass;
        this.adminPrivileges = adminPrivileges;
    }

    public User(String email, String fname, String lname, String pass, boolean adminPrivileges) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.adminPrivileges = adminPrivileges;
    }

    public User(Long id, String email, String fname, String lname, String pass, boolean adminPrivileges) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.adminPrivileges = adminPrivileges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdminPrivileges() {
        return adminPrivileges;
    }

    public void setAdminPrivileges(boolean adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }
}
