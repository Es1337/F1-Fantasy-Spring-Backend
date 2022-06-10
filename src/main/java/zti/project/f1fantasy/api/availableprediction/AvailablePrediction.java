package zti.project.f1fantasy.api.availableprediction;

import com.sun.istack.NotNull;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.userprediction.UserPrediction;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table( name = "available_predictions")
public class AvailablePrediction {
    @Id
    @SequenceGenerator(
            name = "available_prediction_sequence",
            sequenceName = "available_prediction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "available_prediction_sequence"
    )
    private Long id;
    // Defines if it's team(T) or driver(D) related
    @Pattern(regexp = "\\b[TD]\\b")
    private String target;
    private String typeCode;

    @ManyToOne
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "availablePrediction", cascade = CascadeType.ALL)
    private Set<UserPrediction> userPredictions;

    public AvailablePrediction() {
    }

    public AvailablePrediction(String typeCode, String target, Season season) {
        this.typeCode = typeCode;
        this.target = target;
        this.season = season;
    }

    public AvailablePrediction(Long id, String typeCode, String target, Season season) {
        this.id = id;
        this.typeCode = typeCode;
        this.target = target;
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
