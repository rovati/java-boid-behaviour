module ch.rova.boids {
    requires javafx.controls;
    requires javafx.fxml;

    opens ch.rova.boids to javafx.fxml;
    exports ch.rova.boids;
}
