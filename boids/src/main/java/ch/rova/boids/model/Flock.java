package ch.rova.boids.model;

import java.util.ArrayList;
import java.util.List;

public class Flock {

    private List<Boid> boids;

    public Flock(int nbBoids){
        boids = new ArrayList<>();
        for (int i = 0; i < nbBoids; i++){
            boids.add(new Boid());
        }
    }

    public void updateBoidsPositions(){ boids.forEach(Boid::updatePosition); }

    public List<Boid> getBoids(){ return boids; }
    
}
