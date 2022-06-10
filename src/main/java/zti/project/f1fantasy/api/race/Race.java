package zti.project.f1fantasy.api.race;

import com.fasterxml.jackson.annotation.JsonFormat;
import zti.project.f1fantasy.api.raceresult.RaceResult;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.userprediction.UserPrediction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table( name = "races")
public class Race {
    @Id
    @SequenceGenerator(
            name = "race_sequence",
            sequenceName = "race_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "race_sequence"
    )
    private Long id;
    private String name;
    private String country;
    private String city;
    private String track;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fp1;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fp2;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fp3;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime quali;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sprint;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime race;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private Set<RaceResult> raceResults;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private Set<UserPrediction> userPredictions;

    public Race() {
    }

    public Race(String name, String country, String city, Season season) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.season = season;
    }

    public Race(String name, String country, String city, String track, Season season) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.track = track;
        this.season = season;
    }

    public Race(String name, String country, String city, String track,
                LocalDateTime fp1, LocalDateTime fp2, LocalDateTime fp3,
                LocalDateTime quali, LocalDateTime sprint, LocalDateTime race, Season season) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.track = track;
        this.fp1 = fp1;
        this.fp2 = fp2;
        this.fp3 = fp3;
        this.quali = quali;
        this.sprint = sprint;
        this.race = race;
        this.season = season;
    }

    public Race(Long id, String name, String country, String city, String track,
                LocalDateTime fp1, LocalDateTime fp2, LocalDateTime fp3,
                LocalDateTime quali, LocalDateTime sprint, LocalDateTime race, Season season) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.track = track;
        this.fp1 = fp1;
        this.fp2 = fp2;
        this.fp3 = fp3;
        this.quali = quali;
        this.sprint = sprint;
        this.race = race;
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

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public LocalDateTime getFp1() {
        return fp1;
    }

    public void setFp1(LocalDateTime fp1) {
        this.fp1 = fp1;
    }

    public LocalDateTime getFp2() {
        return fp2;
    }

    public void setFp2(LocalDateTime fp2) {
        this.fp2 = fp2;
    }

    public LocalDateTime getFp3() {
        return fp3;
    }

    public void setFp3(LocalDateTime fp3) {
        this.fp3 = fp3;
    }

    public LocalDateTime getQuali() {
        return quali;
    }

    public void setQuali(LocalDateTime quali) {
        this.quali = quali;
    }

    public LocalDateTime getSprint() {
        return sprint;
    }

    public void setSprint(LocalDateTime sprint) {
        this.sprint = sprint;
    }

    public LocalDateTime getRace() {
        return race;
    }

    public void setRace(LocalDateTime race) {
        this.race = race;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
