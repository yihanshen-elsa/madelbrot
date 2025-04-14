package mandelbrot.gui;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFXDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Hole die Bildschirmgröße
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Erstelle eine einfache Oberfläche
        StackPane root = new StackPane(new Label("Hallo JavaFX 🙂"));
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        // Setze Größe und Position
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());

        // Stage konfigurieren
        primaryStage.setTitle("Vollbild JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
