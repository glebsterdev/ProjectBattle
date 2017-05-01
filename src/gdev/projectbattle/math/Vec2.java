package gdev.projectbattle.math;

public class Vec2 {
    public double x;
    public double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 vec2) {
        this.x = vec2.x;
        this.y = vec2.y;
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public void add(Vec2 v) {
        x = x + v.x;
        y = y + v.y;
    }

    public void sub(Vec2 v) {
        x = x - v.x;
        y = y - v.y;
    }

    public void mult(double m) {
        x = x * m;
        y = y * m;
    }

    public void div(double d) {
        if(d != 0){
            x = x / d;
            y = y / d;
        }
    }

    public void normalize() {
        double mag = magnitude();
        if(mag != 0) {
            x = x / mag;
            y = y / mag;
        }
    }

    public static Vec2 zero() {
        return new Vec2(0, 0);
    }

    public static Vec2 diff(Vec2 v0, Vec2 v1) {
        return new Vec2(v0.x - v1.x, v0.y - v1.y);
    }

    public String toString() {
        return x + ", " + y;
    }
}
