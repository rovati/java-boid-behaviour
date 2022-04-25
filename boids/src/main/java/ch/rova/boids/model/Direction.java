package ch.rova.boids.model;

import java.util.Random;

public class Direction {

    private static final double MAX_SPEED = 2;

    public double x;
    public double y;

    public Direction(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Direction(){
        this.x = 0;
        this.y = 0;
    }

    public static Direction random(){
        Random rng = new Random();
        double signX = rng.nextDouble() >= 0.5 ? 1 : -1;
        double signY = rng.nextDouble() >= 0.5 ? 1 : -1;
        double x = signX * rng.nextDouble() * MAX_SPEED;
        double y = signY * Math.sqrt((MAX_SPEED*MAX_SPEED - x*x));
        return new Direction(x, y);
    }

    public void reset(){
        this.x = 0;
        this.y = 0;
    }

    public void moveBy(Direction dir){
        this.x += dir.x;
        this.y += dir.y;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
    
}
