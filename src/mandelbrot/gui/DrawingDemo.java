package mandelbrot.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import mandelbrot.calc.ComplexNumbers;

import java.util.List;

public class DrawingDemo extends Application {

    public static List<ComplexNumbers> points; // wird extern gesetzt

    private double minX, maxX, minY, maxY;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        VBox container = new VBox();

        // TextArea zum Anzeigen der Koordinaten der Punkte
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefSize(300, 600);

        // Berechne Min/Max zum Skalieren
        calculateBounds();

        double padding = 50;
        double width = 600;
        double height = 600;

        // Zeichne Koordinatensystem
        drawCoordinatesSystem(root, width, height, padding);

        // Zeichne Punkte und Linien
        drawPointsAndLines(root, width, height, padding);

        // Update der Koordinaten im TextArea
        updateTextArea(textArea);

        // Horizontal Layout für Zeichnung und TextArea
        HBox layout = new HBox(10, root, textArea);
        Scene scene = new Scene(layout, width + 300, height);
        primaryStage.setTitle("Komplexe Ebene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateBounds() {
        minX = Double.POSITIVE_INFINITY;
        maxX = Double.NEGATIVE_INFINITY;
        minY = Double.POSITIVE_INFINITY;
        maxY = Double.NEGATIVE_INFINITY;

        for (ComplexNumbers z : points) {
            minX = Math.min(minX, z.getReal());
            maxX = Math.max(maxX, z.getReal());
            minY = Math.min(minY, z.getImag());
            maxY = Math.max(maxY, z.getImag());
        }
    }

    private void drawCoordinatesSystem(Pane pane, double width, double height, double padding) {
        // Koordinatensystem (Achsen)
        Line xAxis = new Line(padding, height / 2, width - padding, height / 2);
        Line yAxis = new Line(width / 2, padding, width / 2, height - padding);
        xAxis.setStroke(Color.BLACK);
        yAxis.setStroke(Color.BLACK);
        pane.getChildren().addAll(xAxis, yAxis);
    }

    private void drawPointsAndLines(Pane pane, double width, double height, double padding) {
        for (int i = 0; i < points.size(); i++) {
            ComplexNumbers z = points.get(i);
            double x = padding + (z.getReal() - minX) * (width - 2 * padding) / (maxX - minX);
            double y = padding + (maxY - z.getImag()) * (height - 2 * padding) / (maxY - minY); // y-Achse umdrehen

            Circle point = new Circle(x, y, 2, Color.RED); // kleinere Punkte
            pane.getChildren().add(point);

            // Linie zum nächsten Punkt (falls vorhanden)
            if (i < points.size() - 1) {
                ComplexNumbers nextZ = points.get(i + 1);
                double nextX = padding + (nextZ.getReal() - minX) * (width - 2 * padding) / (maxX - minX);
                double nextY = padding + (maxY - nextZ.getImag()) * (height - 2 * padding) / (maxY - minY);

                Line line = new Line(x, y, nextX, nextY); // Linie zwischen den Punkten
                line.setStroke(Color.BLACK);
                line.setStrokeWidth(1); // dünne Linie
                pane.getChildren().add(line);
            }
        }
    }

    private void updateTextArea(TextArea textArea) {
        StringBuilder sb = new StringBuilder();
        for (ComplexNumbers z : points) {
            sb.append(String.format("z = %.4f + %.4fi\n", z.getReal(), z.getImag()));
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
