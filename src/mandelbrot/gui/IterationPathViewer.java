package mandelbrot.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import mandelbrot.calc.ComplexNumber;

import java.util.ArrayList;
import java.util.List;

public class IterationPathViewer extends Application {

    private Pane drawingPane = new Pane();
    private TextArea logArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        Label realLabel = new Label("Realteil:");
        TextField realInput = new TextField("0.3");
        Label imagLabel = new Label("Imaginärteil:");
        TextField imagInput = new TextField("0.5");
        Button startButton = new Button("Starte Iteration");

        VBox inputBox = new VBox(10, realLabel, realInput, imagLabel, imagInput, startButton);
        inputBox.setStyle("-fx-padding: 20;");

        logArea.setEditable(false);
        logArea.setPrefWidth(300);
        logArea.setWrapText(true);

        HBox layout = new HBox(10, drawingPane, logArea);
        VBox root = new VBox(10, inputBox, layout);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Iterationspfad in der komplexen Ebene");
        primaryStage.show();

        startButton.setOnAction(e -> {
            try {
                double re = Double.parseDouble(realInput.getText());
                double im = Double.parseDouble(imagInput.getText());
                ComplexNumber c = new ComplexNumber(re, im);

                startIteration(c);
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte gültige Zahlen eingeben!");
                alert.show();
            }
        });
    }

    private void startIteration(ComplexNumber c) {
        drawingPane.getChildren().clear();
        logArea.clear();

        List<ComplexNumber> points = new ArrayList<>();
        ComplexNumber z = new ComplexNumber(0, 0);
        points.add(z);

        for (int i = 0; i < 100 && z.modulusSquared() <= 4.0; i++) {
            z = z.multiply(z).add(c);
            points.add(z);
        }

        drawAnimated(points);
    }

    private void drawAnimated(List<ComplexNumber> points) {
        double minX = points.stream().mapToDouble(ComplexNumber::getReal).min().orElse(-2);
        double maxX = points.stream().mapToDouble(ComplexNumber::getReal).max().orElse(2);
        double minY = points.stream().mapToDouble(ComplexNumber::getImag).min().orElse(-2);
        double maxY = points.stream().mapToDouble(ComplexNumber::getImag).max().orElse(2);

        double width = 700, height = 700, padding = 50;
        double scaleX = (width - 2 * padding) / (maxX - minX);
        double scaleY = (height - 2 * padding) / (maxY - minY);

        Timeline timeline = new Timeline();
        for (int i = 0; i < points.size(); i++) {
            int index = i;
            KeyFrame frame = new KeyFrame(Duration.millis(300 * i), event -> {
                if (index > 0) {
                    ComplexNumber prev = points.get(index - 1);
                    ComplexNumber curr = points.get(index);

                    double x1 = padding + (prev.getReal() - minX) * scaleX;
                    double y1 = padding + (maxY - prev.getImag()) * scaleY;
                    double x2 = padding + (curr.getReal() - minX) * scaleX;
                    double y2 = padding + (maxY - curr.getImag()) * scaleY;

                    Line line = new Line(x1, y1, x2, y2);
                    line.setStroke(Color.GRAY);
                    drawingPane.getChildren().add(line);
                }

                ComplexNumber z = points.get(index);
                double x = padding + (z.getReal() - minX) * scaleX;
                double y = padding + (maxY - z.getImag()) * scaleY;
                Circle point = new Circle(x, y, 3, Color.RED);
                drawingPane.getChildren().add(point);

                logArea.appendText("z" + index + " = " + z.toString() + "\n");
            });
            timeline.getKeyFrames().add(frame);
        }
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
