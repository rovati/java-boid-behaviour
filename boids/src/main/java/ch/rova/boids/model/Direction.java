package ch.rova.boids.model;

import java.util.Random;

public class Direction {

    public double x;
    public double y;

    public Direction(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Direction random(){
        Random rng = new Random();
        double signX = rng.nextDouble() >= 0.5 ? 1 : -1;
        double signY = rng.nextDouble() >= 0.5 ? 1 : -1;
        double x = signX * rng.nextDouble();
        double y = signY * Math.sqrt(1 - x*x);
        Direction dir = new Direction(x, y);
        dir.normalize();
        return dir;
    }

    public static Direction fromPositions(Position p1, Position p2){
        return new Direction(p1.x - p2.x, p1.y - p2.y);
    }

    public void normalize(){
        if (x == 0 || y == 0){
            if (!(x == 0 && y == 0))
                if (x == 0)
                    y = 1;
                else
                    x = 1;
        } else {   
            double norm = Math.sqrt(x*x + y*y);
            x = x / norm;
            y = y / norm;
        }
    }

    public void reset(){
        this.x = 0;
        this.y = 0;
    }

    public void moveBy(Direction dir){
        this.x += dir.x;
        this.y += dir.y;
    }

    public void add(Direction other){
        x = x + other.x;
        y = y + other.y;
    }

    public void sub(Direction other){
        x = x - other.x;
        y = y - other.y;
    }

    public void addAndNormalize(Direction other){
        add(other);
        normalize();
    }

    public Direction scaleBy(double s){
        return new Direction(x*s, y*s);
    }

    public Direction mirrorOver(Direction other){
        double t = -2 * (x*other.x + y*other.y) / (x *x + y*y);
        return new Direction(t * x + other.x, t * y + other.y);
    }

    public double dot(Direction other){
        return this.x * other.x + this.y * other.y;
    }

    public double norm(){
        return Math.sqrt(x*x + y*y);
    }

    public Direction projectOn(Direction other){
        return other.scaleBy(this.dot(other) / other.norm());
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
    
}
