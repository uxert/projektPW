package projekt_PW.runnable_GUI_actions;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import projekt_PW.HelloController;

/**
 * This implementation of Runnable adds to the shelf a circle with random color
 * This will be used for full animation in the future (probably...)
 */
public class MoveToShelfAnimation implements Runnable{

    private HelloController control;
    public MoveToShelfAnimation(HelloController control)
    {
        this.control = control;
    }
    public void run()
    {
//        double destX = control.getShelfGUI().getTranslateX();
//        double destY = control.getShelfGUI().getTranslateY();
//        TranslateTransition addItem = new TranslateTransition();
        Circle temp = new Circle(150, 200, 25, Color.color(Math.random(), Math.random(), Math.random()));
//        addItem.setToX(destX);
//        addItem.setToY(destY);
//        addItem.setDuration(Duration.millis(1000));
//        addItem.setNode(temp);
//        addItem.play();
        control.getShelfGUI().getChildren().add(temp);
        control.itemsOnShelf.add(temp);
    }
}
