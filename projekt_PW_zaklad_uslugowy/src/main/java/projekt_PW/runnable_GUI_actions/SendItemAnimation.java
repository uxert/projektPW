package projekt_PW.runnable_GUI_actions;

import javafx.animation.ScaleTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import projekt_PW.HelloController;

public class SendItemAnimation implements Runnable{
    private HelloController control;
    int timeMilis;
    Circle itemGUI;
    public SendItemAnimation(HelloController control, int timeMilis, Circle itemGUI)
    {
        this.control = control;
        this.timeMilis = timeMilis;
        this.itemGUI = itemGUI;
    }
    public void run()
    {

        ScaleTransition scale = new ScaleTransition();
        scale.setNode(itemGUI);
        scale.setByX(-0.85);
        scale.setByY(-0.85);
        scale.setDuration(Duration.millis(timeMilis));
        scale.play();
    }
}

