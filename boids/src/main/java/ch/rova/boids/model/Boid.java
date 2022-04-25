package ch.rova.boids.model;

public class Boid {

    public static final double MAX_CANVAS_SIZE = 500;

    public Direction dir;
    public Position pos;
    public double angle;

    private double max_width;
    private double max_height;

    public Boid(){
        dir = Direction.random();
        pos = Position.random(MAX_CANVAS_SIZE,MAX_CANVAS_SIZE);
        max_width = MAX_CANVAS_SIZE;
        max_height = MAX_CANVAS_SIZE;
        updateAngle();
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
