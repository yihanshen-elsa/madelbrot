package mandelbrot.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mandelbrot.calc.ComplexNumber;

public class MandelbrotExplorer extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int MAX_ITER = 200;

    private double minX = -2.0;
    private double maxX = 1.0;
    private double minY = -1.5;
    private double maxY = 1.5;

    private double zoomFactor = 1.0;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        drawMandelbrot(canvas.getGraphicsContext2D());

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            double clickX = e.getX();
            double clickY = e.getY();

            double centerX = minX + (clickX / WIDTH) * (maxX - minX);
            double centerY = minY + (clickY / HEIGHT) * (maxY - minY);

            zoomFactor *= 0.5;
            double rangeX = (maxX - minX) * zoomFactor;
            double rangeY = (maxY - minY) * zoomFactor;

            minX = centerX - rangeX / 2;
            maxX = centerX + rangeX / 2;
            minY = centerY - rangeY / 2;
            maxY = centerY + rangeY / 2;

            drawMandelbrot(canvas.getGraphicsContext2D());
        });

        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.setTitle("Mandelbrot-Menge");
        primaryStage.show();
    }

    private void drawMandelbrot(GraphicsContext gc) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double c_re = minX + x * (maxX - minX) / WIDTH;
                double c_im = minY + y * (maxY - minY) / HEIGHT;
                ComplexNumber c = new ComplexNumber(c_re, c_im);

                ComplexNumber z = new ComplexNumber(0, 0);
                int iter = 0;
                while (z.modulusSquared() <= 4.0 && iter < MAX_ITER) {
                    z = z.multiply(z).add(c);
                    iter++;
                }

                double hue = 360.0 * iter / MAX_ITER;
                Color color = iter == MAX_ITER ? Color.BLACK : Color.hsb(hue, 1.0, 1.0);
                gc.getPixelWriter().setColor(x, y, color);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
