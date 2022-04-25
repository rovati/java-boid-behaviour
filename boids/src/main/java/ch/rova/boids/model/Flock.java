package ch.rova.boids.model;

import java.util.ArrayList;
import java.util.List;

import ch.rova.boids.Constants;

public class Flock {

    private List<Boid> boids;

    public Flock(int nbBoids){
        boids = new ArrayList<>();
        for (int i = 0; i < nbBoids; i++){
            boids.add(new Boid());
        }
    }

    public void updateBoidsDirections(){
        for (Boid b : boids){
            //Boid b = boids.get(0);
            List<Boid> closeBoids = new ArrayList<>();
            for (Boid b1 : boids){
                if (!b1.equals(b)){
                    Direction distanceVector = Direction.fromPositions(
                        b1.pos, b.pos);
                    if (distanceVector.norm() < Constants.MAX_DISTANCE_DETECTION &&
                        distanceVector.dot(b.dir) > -0.5)
                        closeBoids.add(b1);
                }
            }
            b.updateDirection(closeBoids);
        }
    }

    public void updateBoidsPositions(){ boids.forEach(Boid::updatePosition); }

    public List<Boid> getBoids(){ return boids; }
    
}
