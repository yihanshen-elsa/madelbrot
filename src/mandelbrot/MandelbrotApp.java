package mandelbrot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mandelbrot.gui.MandelbrotExplorer;
import mandelbrot.gui.IterationPathViewer;

public class MandelbrotApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button explorerButton = new Button("Mandelbrot-Menge erkunden");
        Button viewerButton = new Button("Iterationspfad anzeigen");

        explorerButton.setOnAction(e -> {
            try {
                new MandelbrotExplorer().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        viewerButton.setOnAction(e -> {
            try {
                new IterationPathViewer().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox root = new VBox(10, explorerButton, viewerButton);
        root.setStyle("-fx-padding: 20;");
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.setTitle("Mandelbrot Visualisierer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
