package UI.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class Menubar
{
    UIButton uiButton = new UIButton();

    public Menubar()
    {
    }

    /**
     * @return Custom MenuBar based on a VBox. This is the vertical blue menu in the left side of the application.
     */
    public VBox GetMenubarRoot()
    {
        VBox menubarRoot = new VBox(20);
        menubarRoot.setPadding(new Insets(10, 0, 10, 0));
        menubarRoot.setAlignment(Pos.BASELINE_LEFT);
        menubarRoot.setStyle("-fx-background-color: #EEF5FD; -fx-border-radius: 7px;");

        menubarRoot.getChildren().addAll(uiButton.consultantsButton(),
                uiButton.officesButton(), uiButton.projectsButton(), uiButton.tasksButton(), uiButton.bindingsButton());

        return menubarRoot;
    }
}
