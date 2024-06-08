package projekt_PW;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ShapeEventsDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane for layout
        Pane pane = new Pane();

        // Create a Circle shape
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.BLUE); // Set the fill color of the circle

        // Create a Rectangle shape
        Rectangle rectangle = new Rectangle(200, 200, 100, 50);
        rectangle.setFill(Color.RED); // Set the fill color of the rectangle

        // Create a Line shape
        Line line = new Line(50, 50, 300, 300);
        line.setStroke(Color.GREEN); // Set the color of the line
        line.setStrokeWidth(5); // Set the width of the line

        // Add shapes to the pane
        pane.getChildren().addAll(circle, rectangle, line);

        // Add a mouse click event handler for the circle
        circle.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Circle clicked at: " + event.getSceneX() + ", " + event.getSceneY());
            // Change the color of the circle to a random color on click
            circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        });

        // Add a mouse click event handler for the rectangle
        rectangle.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Rectangle clicked at: " + event.getSceneX() + ", " + event.getSceneY());
            // Change the color of the rectangle to a random color on click
            rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        });

        // Add a mouse click event handler for the line
        line.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Line clicked at: " + event.getSceneX() + ", " + event.getSceneY());
            // Change the color of the line to a random color on click
            line.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        });

        // Scene and Stage setup
        Scene scene = new Scene(pane, 400, 400); // Create a scene with the pane and set its size
        primaryStage.setTitle("JavaFX Shapes, Events and Layout Panes"); // Set the title of the stage
        primaryStage.setScene(scene); // Set the scene to the stage
        primaryStage.show(); // Display the stage

        // Add a key event handler to the scene
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    System.out.println("Up arrow pressed");
                    // Move the circle up by decreasing its Y translation
                    circle.setTranslateY(circle.getTranslateY() - 10);
                    break;
                case DOWN:
                    System.out.println("Down arrow pressed");
                    // Move the circle down by increasing its Y translation
                    circle.setTranslateY(circle.getTranslateY() + 10);
                    break;
                case LEFT:
                    System.out.println("Left arrow pressed");
                    // Move the circle left by decreasing its X translation
                    circle.setTranslateX(circle.getTranslateX() - 10);
                    break;
                case RIGHT:
                    System.out.println("Right arrow pressed");
                    // Move the circle right by increasing its X translation
                    circle.setTranslateX(circle.getTranslateX() + 10);
                    break;
                default:
                    break;
            }
        });
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
