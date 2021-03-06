package zti.project.f1fantasy.api.userprediction;

import zti.project.f1fantasy.api.availableprediction.AvailablePrediction;
import zti.project.f1fantasy.api.race.Race;
import zti.project.f1fantasy.api.user.User;

import javax.persistence.*;

@Entity
@Table(name = "user_predictions")
public class UserPrediction {
    @Id
    @SequenceGenerator(
            name = "user_prediction_sequence",
            sequenceName = "user_prediction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_prediction_sequence"
    )
    private Long id;
    // Driver name or team name
    private String title;

    @ManyToOne
    @JoinColumn(name = "available_prediction_id", nullable = false)
    private AvailablePrediction availablePrediction;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AvailablePrediction getAvailablePrediction() {
        return availablePrediction;
    }

    public void setAvailablePrediction(AvailablePrediction availablePrediction) {
        this.availablePrediction = availablePrediction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
