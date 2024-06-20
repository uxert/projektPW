package projekt_PW.runnable_GUI_actions;

import javafx.scene.shape.Circle;
import projekt_PW.HelloController;

/**
 * This implementation of Runnable adds to the shelf a circle with random color
 * This will be used for full animation in the future (probably...)
 */
public class MoveToShelfAnimation implements Runnable{

    private final HelloController control;
    public MoveToShelfAnimation(HelloController control)
    {
        this.control = control;
    }
    public void run()
    {
        control.incShelfCount();
        Circle itemGUI =(Circle) control.receptionistGUI.getChildren().removeFirst();
        control.getShelfGUI().getChildren().add(itemGUI);
        control.itemsOnShelf.add(itemGUI);
    }
}
