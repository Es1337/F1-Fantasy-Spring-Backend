package zti.project.f1fantasy.api.ranking;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRankingKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "season_id")
    private Long seasonId;

    public UserRankingKey() {
    }

    public UserRankingKey(Long userId, Long seasonId) {
        this.userId = userId;
        this.seasonId = seasonId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRankingKey that = (UserRankingKey) o;
        return getUserId().equals(that.getUserId()) && getSeasonId().equals(that.getSeasonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getSeasonId());
    }
}
