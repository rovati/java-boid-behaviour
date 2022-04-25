package ch.rova.boids.model;

import java.util.List;

public class Boid {

    public static final double MAX_CANVAS_SIZE = 500;
    public static final double MAX_COLLISION_DIST = 10;

    public Direction dir;
    public Position pos;
    public double angle;

    private double max_width;
    private double max_height;

    private boolean colliding;

    public Boid(){
        dir = Direction.random();
        pos = Position.random(MAX_CANVAS_SIZE,MAX_CANVAS_SIZE);
        max_width = MAX_CANVAS_SIZE;
        max_height = MAX_CANVAS_SIZE;
        updateAngle();
        colliding = false;
    }

    public Boid(double canvasWidth, double canvasHeight){
        dir = new Direction(2,1);
        pos = Position.random(canvasWidth, canvasHeight);
        updateAngle();
    }

    public void setBounds(double width, double height){
        this.max_width = width;
        this.max_height = height;
    }

    public void updatePosition(){
        pos.moveBy(dir);
        if (pos.x > max_width)
            pos.x = 0;
        if (pos.x < 0)
            pos.x = max_width;
        if (pos.y > max_height)
            pos.y = 0;
        if (pos.y < 0)
            pos.y = max_height;
    }

    /**
     * Where the boids behaviour is defined.
     * Given a list of boids close to this one, it updates the
     * expeced direction of this boid based on the behaviour
     * rules defined.
     * 
     * @param closeBoids list of the boids in proximity of this
     */
    public void updateDirection(List<Boid> closeBoids){
        /* collision avoidance */
        colliding = false;
        // check whether collision happens in front of boids
        for (Boid otherB : closeBoids){
            if (isCollidingWith(otherB)){
                // calibrate direction
                dir.addAndNormalize(otherB.dir.scaleBy(0.001));
                updateAngle();
                colliding = true;
            }
        }
    }

    public boolean isColliding() { return colliding; }

    public void setDirection(Direction dir){
        this.dir = dir;
        updateAngle();
    }

    public void moveDirection(Direction dir){
        this.dir.moveBy(dir);
        updateAngle();
    }

    private void updateAngle(){
        angle = Math.atan2(dir.y, dir.x) / Math.PI * 180 - 90;
    }

    private boolean isCollidingWith(Boid otherB){
        double t1 = (otherB.pos.x - this.pos.x) / (dir.x - otherB.dir.x);
        double t2 = (otherB.pos.y - this.pos.y) / (dir.y - otherB.dir.y);
        return  t1 > 0 && Math.abs(t1-t2) < 100;
    }

}
