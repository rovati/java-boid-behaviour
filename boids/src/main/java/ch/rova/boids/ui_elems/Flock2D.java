package ch.rova.boids.ui_elems;

import java.util.ArrayList;
import java.util.List;

import ch.rova.boids.model.Boid;
import ch.rova.boids.model.Flock;
import javafx.scene.layout.Pane;

public class Flock2D extends Pane {

    private Flock flock;
    List<BoidUI> boidsShapes;

    public Flock2D(int nbBoids){
        super();
        this.flock = new Flock(nbBoids);
        boidsShapes = new ArrayList<>();
        for (Boid b : flock.getBoids()){
            boidsShapes.add(new BoidUI(b));
        }
        this.getChildren().addAll(boidsShapes);
    }

    public void setCanvasSize(double width, double height){
        for (BoidUI bui : boidsShapes){
            bui.setBounds(width, height);
        }
    }

    public void update(){
        flock.updateBoidsDirections();
        flock.updateBoidsPositions();
        boidsShapes.forEach(BoidUI::draw);
    }
    
}
