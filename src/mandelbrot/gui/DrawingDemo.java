package mandelbrot.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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

        // Zeichne das Koordinatensystem (Achsen)
        Line xAxis = new Line(padding, height / 2, width - padding, height / 2);
        Line yAxis = new Line(width / 2, padding, width / 2, height - padding);
        xAxis.setStroke(Color.BLACK);
        yAxis.setStroke(Color.BLACK);
        root.getChildren().addAll(xAxis, yAxis);

        // Zeichne Punkte und Linien
        for (int i = 0; i < points.size(); i++) {
            ComplexNumbers z = points.get(i);
            double x = padding + (z.getReal() - minX) * scaleX;
            double y = padding + (maxY - z.getImag()) * scaleY; // y-Achse umdrehen

            Circle point = new Circle(x, y, 2, Color.RED); // kleinere Punkte
            root.getChildren().add(point);

            // Linie zum nächsten Punkt (falls vorhanden)
            if (i < points.size() - 1) {
                ComplexNumbers nextZ = points.get(i + 1);
                double nextX = padding + (nextZ.getReal() - minX) * scaleX;
                double nextY = padding + (maxY - nextZ.getImag()) * scaleY;
                
                Line line = new Line(x, y, nextX, nextY); // Linie zwischen den Punkten
                line.setStroke(Color.BLACK);
                line.setStrokeWidth(1); // dünne Linie
                root.getChildren().add(line);
            }
        }

        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Komplexe Ebene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
