package zti.project.f1fantasy.api.driver;

import zti.project.f1fantasy.api.raceresult.RaceResult;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.team.Team;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "drivers")
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_sequence"
    )
    private Long id;
    private String fname;
    private String lname;
    private Integer points;

    @ManyToOne
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name="team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "driver")
    private Set<RaceResult> raceResults;

    public Driver() {
    }

    public Driver(String fname, String lname, Season season, Team team) {
        this.fname = fname;
        this.lname = lname;
        this.points = 0;
        this.season = season;
        this.team = team;
    }

    public Driver(String fname, String lname, Integer points, Season season, Team team) {
        this.fname = fname;
        this.lname = lname;
        this.points = points;
        this.season = season;
        this.team = team;
    }

    public Driver(Long id, String fname, String lname, Integer points, Season season, Team team) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.points = points;
        this.season = season;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
