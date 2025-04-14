package mandelbrot.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mandelbrot.calc.ComplexNumbers;

import java.util.ArrayList;
import java.util.List;

public class JavaFXDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label realLabel = new Label("Realteil:");
        TextField realInput = new TextField();

        Label imagLabel = new Label("Imaginärteil:");
        TextField imagInput = new TextField();

        Button drawButton = new Button("Draw");

        VBox inputBox = new VBox(10, realLabel, realInput, imagLabel, imagInput, drawButton);
        inputBox.setStyle("-fx-padding: 20;");

        drawButton.setOnAction(e -> {
            try {
                double re = Double.parseDouble(realInput.getText());
                double im = Double.parseDouble(imagInput.getText());

                ComplexNumbers c = new ComplexNumbers(re, im);
                List<ComplexNumbers> result = new ArrayList<>();
                ComplexNumbers z = new ComplexNumbers(0, 0);

                for (int i = 0; i < 30; i++) {
                    z = z.multiply(z).add(c);
                    result.add(z);
                }

                DrawingDemo.points = result;
                DrawingDemo demo = new DrawingDemo();
                Stage drawStage = new Stage();
                demo.start(drawStage);

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte gültige Zahlen eingeben!");
                alert.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(inputBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Komplexe Zahlen eingeben");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
