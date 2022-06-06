package zti.project.f1fantasy.api.ranking;

import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ranking")
public class Ranking {
    @EmbeddedId
    private UserRankingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("seasonId")
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    private Integer points;

    public Ranking() {
    }

    public Ranking(User user, Season season) {
        this.user = user;
        this.season = season;
        this.points = 0;
    }

    public Ranking(User user, Season season, Integer points) {
        this.user = user;
        this.season = season;
        this.points = points;
    }

    public Ranking(UserRankingKey id, User user, Season season, Integer points) {
        this.id = id;
        this.user = user;
        this.season = season;
        this.points = points;
    }

    public UserRankingKey getId() {
        return id;
    }

    public void setId(UserRankingKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
