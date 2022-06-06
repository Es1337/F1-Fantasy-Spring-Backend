package zti.project.f1fantasy.api.availableprediction;

import zti.project.f1fantasy.api.season.Season;

import javax.persistence.*;

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
    private String typeCode;

    @ManyToOne
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    public AvailablePrediction() {
    }

    public AvailablePrediction(String typeCode, Season season) {
        this.typeCode = typeCode;
        this.season = season;
    }

    public AvailablePrediction(Long id, String typeCode, Season season) {
        this.id = id;
        this.typeCode = typeCode;
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
