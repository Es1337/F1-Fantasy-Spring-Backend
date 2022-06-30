package zti.project.f1fantasy.api.reward;

public enum PointsHelper {
    P1(25),
    P2(18),
    P3(15),
    P4(12),
    P5(10),
    P6(8),
    P7(6),
    P8(4),
    P9(2),
    P10(1),
    OTHER(0);

    private final Integer points;

    PointsHelper(Integer points) {
        this.points = points;
    }
}
