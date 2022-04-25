package ch.rova.boids.ui_elems;

import ch.rova.boids.model.Boid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class BoidUI extends Polygon {

    private Boid boid;
    private Color normalColor = Color.AQUAMARINE;
    private Color collisionColor = Color.RED;
    private Translate translation;
    private Rotate orientation;

    public BoidUI(Boid boid){
        super();
        this.boid = boid;
        getPoints().addAll(new Double[]{        
            0.0, 10.0, 
            5.0, -10.0, 
            -5.0, -10.0,  
            });
        setFill(normalColor);
        orientation = new Rotate(this.boid.angle);
        translation = new Translate();
        getTransforms().addAll(translation, orientation);
    }

    public void setBounds(double width, double height){
        boid.setBounds(width, height);
    }

    public void move(){
        boid.updatePosition();
        translation.setX(boid.pos.x);
        translation.setY(boid.pos.y);
        orientation.setAngle(boid.angle);
    }

    public void draw(){
        translation.setX(boid.pos.x);
        translation.setY(boid.pos.y);
        orientation.setAngle(boid.angle);
        if (boid.isColliding())
            setFill(collisionColor);
        else
            setFill(normalColor);
    }
    
}
