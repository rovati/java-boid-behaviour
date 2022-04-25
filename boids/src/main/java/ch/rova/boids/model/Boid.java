package ch.rova.boids.model;

import java.util.List;

import ch.rova.boids.Constants;

public class Boid {

    public Direction dir;
    public Position pos;
    public double angle;

    private double max_width;
    private double max_height;

    private boolean colliding;

    public Boid(){
        dir = Direction.random();
        pos = Position.random(Constants.SCENE_WIDTH,Constants.SCENE_WIDTH);
        max_width = Constants.SCENE_WIDTH;
        max_height = Constants.SCENE_WIDTH;
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
        colliding = closeBoids.size() != 0;
        Direction correction = new Direction(0,0);
        Direction flockDirection = new Direction(0,0);
        Direction flockCenter = new Direction(dir.x, dir.y);

        for (Boid otherB : closeBoids){
            /* collision avoidance */
            Direction distanceVector = Direction.fromPositions(otherB.pos, pos);
            double distance = distanceVector.norm();
            double scaleFactor = (Constants.MAX_DISTANCE_DETECTION - distance)
                / Constants.MAX_DISTANCE_DETECTION;
            correction.add(distanceVector.scaleBy(-scaleFactor));

            /* flock direcion */
            flockDirection.add(otherB.dir);

            /* flock center */
            flockCenter.add(new Direction(otherB.pos.x, otherB.pos.y));
        }
        Direction dirToCenter = flockCenter.scaleBy(closeBoids.size());
        dirToCenter.sub(new Direction(dir.x, dir.y));

        dir.add(dirToCenter.scaleBy(0.01));
        //dir.add(correction.scaleBy(0.0001));
        //dir.add(flockDirection.scaleBy(0.001));
        dir.normalize();
        updateAngle();
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

}