package ch.rova.boids.model;

import java.util.Random;

public class Position {

    public double x;
    public double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public static Position random(double maxWidth, double maxHeight){
        Random rng = new Random();
        return new Position(rng.nextDouble() * maxWidth, rng.nextDouble() * maxHeight);
    }

    public void reset(){
        this.x = 0;
        this.y = 0;
    }

    public void moveBy(Direction d){
        this.x += d.x;
        this.y += d.y;
    }
    
}
