package zti.project.f1fantasy.api.userprediction;

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
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

}
