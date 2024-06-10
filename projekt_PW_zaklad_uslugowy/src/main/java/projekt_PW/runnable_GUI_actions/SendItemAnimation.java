package projekt_PW.runnable_GUI_actions;

import javafx.animation.ScaleTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import projekt_PW.HelloController;

public class SendItemAnimation implements Runnable{
    private HelloController control;
    int timeMilis;
    public SendItemAnimation(HelloController control, int timeMilis)
    {
        this.control = control;
        this.timeMilis = timeMilis;
    }
    public void run()
    {
        Circle temp = control.itemsOnShelf.getFirst();
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(temp);
        scale.setByX(-0.75);
        scale.setByY(-0.75);
        scale.setDuration(Duration.millis(timeMilis));
        scale.play();
    }
}

