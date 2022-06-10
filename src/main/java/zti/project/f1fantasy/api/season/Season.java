package zti.project.f1fantasy.api.season;

import zti.project.f1fantasy.api.availableprediction.AvailablePrediction;
import zti.project.f1fantasy.api.driver.Driver;
import zti.project.f1fantasy.api.race.Race;
import zti.project.f1fantasy.api.ranking.Ranking;
import zti.project.f1fantasy.api.team.Team;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @SequenceGenerator(
            name = "season_sequence",
            sequenceName = "season_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "season_sequence"
    )
    private Long id;
    private Integer year;
    private String wdc;
    private String wcc;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Team> teams;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<AvailablePrediction> availablePredictions;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Driver> drivers;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Race> races;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ranking> rankings;

    public Season() {
    }

    public Season(Integer year) {
        this.year = year;
        this.wdc = "";
        this.wcc = "";
    }

    public Season(Integer year, String wdc, String wcc) {
        this.year = year;
        this.wdc = wdc;
        this.wcc = wcc;
    }

    public Season(Long id, Integer year, String wdc, String wcc) {
        this.id = id;
        this.year = year;
        this.wdc = wdc;
        this.wcc = wcc;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setWdc(String wdc) {
        this.wdc = wdc;
    }

    public void setWcc(String wcc) {
        this.wcc = wcc;
    }

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public String getWdc() {
        return wdc;
    }

    public String getWcc() {
        return wcc;
    }


}
