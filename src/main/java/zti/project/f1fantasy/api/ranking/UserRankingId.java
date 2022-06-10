package zti.project.f1fantasy.api.ranking;

import java.io.Serializable;
import java.util.Objects;

public class UserRankingId implements Serializable {
    private Long user;
    private Long season;

    public Long getUserId() {
        return user;
    }

    public void setUserId(Long userId) {
        this.user = userId;
    }

    public Long getSeasonId() {
        return season;
    }

    public void setSeasonId(Long seasonId) {
        this.season = seasonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRankingId that = (UserRankingId) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getSeasonId(), that.getSeasonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getSeasonId());
    }
}
