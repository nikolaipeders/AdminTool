package UI.Navigation;

import UI.Misc.FontSlider;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class Menubar
{
    UIButton uiButton = new UIButton();
    FontSlider fontSlider = new FontSlider();

    public Menubar()
    {
    }

    /**
     * @return Custom MenuBar based on a VBox. This is the vertical blue menu on the left side of the application.
     */
    public VBox GetMenubarRoot()
    {
        VBox menubarRoot = new VBox(20);

        menubarRoot.setPadding(new Insets(10, 0, 10, 0));
        menubarRoot.setStyle("-fx-background-color: #EEF5FD; -fx-border-radius: 7px;");
        menubarRoot.setEffect(new DropShadow(5, Color.GREY));

        VBox topPart = new VBox(20);
        topPart.setAlignment(Pos.BASELINE_LEFT);
        VBox.setVgrow(topPart, Priority.ALWAYS);

        VBox bottomPart = new VBox(20);
        bottomPart.setAlignment(Pos.BOTTOM_LEFT);
        VBox.setVgrow(bottomPart, Priority.ALWAYS);

        topPart.getChildren().addAll(uiButton.consultantsButton(),
                uiButton.officesButton(), uiButton.projectsButton(), uiButton.tasksButton(), uiButton.bindingsButton());

        bottomPart.getChildren().add(fontSlider.getSliderFont());

        menubarRoot.getChildren().addAll(topPart, bottomPart);

        return menubarRoot;
    }
}
