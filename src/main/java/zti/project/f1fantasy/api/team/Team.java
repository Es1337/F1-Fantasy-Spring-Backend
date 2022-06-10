package zti.project.f1fantasy.api.team;

import zti.project.f1fantasy.api.driver.Driver;
import zti.project.f1fantasy.api.season.Season;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "teams")
public class Team {
    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_sequence"
    )
    private Long id;
    private String name;
    private Integer points;

    @ManyToOne
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Driver> drivers;

    public Team() {
    }

    public Team(String name, Season season) {
        this.name = name;
        this.season = season;
        this.points = 0;
    }

    public Team(String name, Integer points, Season season) {
        this.name = name;
        this.points = points;
        this.season = season;
    }

    public Team(Long id, String name, Integer points, Season season) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
