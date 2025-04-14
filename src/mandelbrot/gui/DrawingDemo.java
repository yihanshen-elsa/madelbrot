package mandelbrot.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mandelbrot.calc.ComplexNumbers;

import java.util.List;

public class DrawingDemo extends Application {

    public static List<ComplexNumbers> points; // wird extern gesetzt

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Berechne Min/Max zum Skalieren
        double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;

        for (ComplexNumbers z : points) {
            minX = Math.min(minX, z.getReal());
            maxX = Math.max(maxX, z.getReal());
            minY = Math.min(minY, z.getImag());
            maxY = Math.max(maxY, z.getImag());
        }

        double padding = 50;
        double width = 600;
        double height = 600;

        double scaleX = (width - 2 * padding) / (maxX - minX);
        double scaleY = (height - 2 * padding) / (maxY - minY);

        for (ComplexNumbers z : points) {
            double x = padding + (z.getReal() - minX) * scaleX;
            double y = padding + (maxY - z.getImag()) * scaleY; // y-Achse umdrehen

            Circle point = new Circle(x, y, 5, Color.RED);
            root.getChildren().add(point);
        }

        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Komplexe Ebene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}