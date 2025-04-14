package mandelbrot.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawingDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Ein Punkt (als kleiner Kreis)
        Circle point = new Circle(100, 100, 5, Color.RED);

        // Eine Linie von Punkt A nach Punkt B
        Line line = new Line(100, 100, 200, 150);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);

        // Shapes zur Oberfläche hinzufügen
        root.getChildren().addAll(point, line);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Zeichnen mit JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
