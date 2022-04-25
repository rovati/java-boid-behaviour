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

    private Scene scene;
    private AnimationTimer loop;
    private Flock2D flock;

    @Override
    public void start(Stage stage) throws IOException {
        flock = new Flock2D(Constants.NB_BOIDS);

        scene = new Scene(flock,
            Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);

        stage.setTitle("Boids test");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        runSimulation();
    }

    private void runSimulation(){
        flock.setCanvasSize(
            Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
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