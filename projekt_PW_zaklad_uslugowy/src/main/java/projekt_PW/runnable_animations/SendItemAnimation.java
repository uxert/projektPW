package projekt_PW.runnable_animations;

import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import projekt_PW.HelloController;

public class SendItemAnimation implements Runnable{
    private HelloController control;
    public SendItemAnimation(HelloController control)
    {
        this.control = control;
    }
    public void run()
    {
        Circle temp = control.itemsOnShelf.getFirst();
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(temp);
        scale.setByX(-0.75);
        scale.setByY(-0.75);
        scale.setDuration(Duration.millis(500));
        scale.play();
        control.itemsOnShelf.removeFirst();
        control.getShelfGUI().getChildren().removeFirst();

    }
}

