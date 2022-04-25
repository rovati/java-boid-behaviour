package ch.rova.boids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import ch.rova.boids.ui_elems.Flock2D;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final double SCENE_WIDTH = 1200;
    private static final double SCENE_HEIGHT = 800;

    private Scene scene;
    private AnimationTimer loop;
    private Flock2D flock;

    @Override
    public void start(Stage stage) throws IOException {
        flock = new Flock2D(5);

        scene = new Scene(flock, SCENE_WIDTH, SCENE_HEIGHT);

        stage.setTitle("Boids test");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        runSimulation();
    }

    private void runSimulation(){
        flock.setCanvasSize(SCENE_WIDTH, SCENE_HEIGHT);
        loop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                flock.update();
            }
        };

        loop.start();
    }


    public static void main(String[] args) {
        launch();
    }

}