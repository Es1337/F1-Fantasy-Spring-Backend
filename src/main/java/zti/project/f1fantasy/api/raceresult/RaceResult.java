package zti.project.f1fantasy.api.raceresult;

import zti.project.f1fantasy.api.driver.Driver;
import zti.project.f1fantasy.api.race.Race;

import javax.persistence.*;

@Entity
@Table(name = "race_results")
public class RaceResult {
    @Id
    @SequenceGenerator(
            name = "race_result_sequence",
            sequenceName = "race_result_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "race_result_sequence"
    )
    private Long id;
    private Integer position;
    private Integer points;
    private boolean dnf;
    private Integer fastestLap;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    public RaceResult() {
    }

    public RaceResult(Integer position, Integer points, boolean dnf, Integer fastestLap, Race race, Driver driver) {
        this.position = position;
        this.points = points;
        this.dnf = dnf;
        this.fastestLap = fastestLap;
        this.race = race;
        this.driver = driver;
    }

    public RaceResult(Long id, Integer position, Integer points, boolean dnf, Integer fastestLap, Race race, Driver driver) {
        this.id = id;
        this.position = position;
        this.points = points;
        this.dnf = dnf;
        this.fastestLap = fastestLap;
        this.race = race;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public boolean isDnf() {
        return dnf;
    }

    public void setDnf(boolean dnf) {
        this.dnf = dnf;
    }

    public Integer getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(Integer fastestLap) {
        this.fastestLap = fastestLap;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
