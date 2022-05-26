package UI.Navigation;

import Application.MainWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Menubar
{
    UIButton uiButton = new UIButton();

    public Menubar()
    {
    }

    public VBox GetMenubarRoot()
    {
        VBox menubarRoot = new VBox(20);

        menubarRoot.setPadding(new Insets(10, 0, 10, 0));
        menubarRoot.setAlignment(Pos.BASELINE_LEFT);
        menubarRoot.setStyle("-fx-background-color: #EEF5FD; -fx-border-radius: 7px;");

        menubarRoot.getChildren().addAll(uiButton.overviewButton(), uiButton.consultantsButton(),
                uiButton.officesButton(), uiButton.projectsButton(), uiButton.tasksButton(),
                uiButton.reportButton(), uiButton.bindingsButton());

        return menubarRoot;
    }
}
