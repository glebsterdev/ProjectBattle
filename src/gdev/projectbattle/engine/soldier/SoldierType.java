package gdev.projectbattle.engine.soldier;

public enum SoldierType {
    INFANTRY ( 8,  3,  1),
    CAVALRY  (10, 12,  1),
    ARCHER   ( 5,  4, 40);

    public final double maxHealth;
    public final double maxSpeed;
    public final double maxRange;

    SoldierType(double maxHealth, double maxSpeed, double maxRange) {
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.maxRange = maxRange;
    }
}
