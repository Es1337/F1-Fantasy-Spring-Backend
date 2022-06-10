package zti.project.f1fantasy.api.ranking;

import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ranking")
@IdClass(UserRankingId.class)
public class Ranking {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "season_id", referencedColumnName = "id")
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
