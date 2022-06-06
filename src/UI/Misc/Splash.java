package UI.Misc;

import Application.MainWindow;
import Foundation.Loading.LoadConsultants;
import Foundation.Loading.LoadOffices;
import Foundation.Loading.LoadProjects;
import Foundation.Loading.LoadTasks;
import UI.Navigation.Menubar;
import UI.Navigation.UIButton;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class Splash
{
    public boolean isDone = false;

    /**
     * Loads a splashscreen which initializes threads all loading data from DB. This gives a startup time of a couple of seconds.
     */
    public void loadSplashScreen()
    {
        MainWindow.root.setCenter(loadingScreen());

        // Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2),loadingScreen());
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        // Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), loadingScreen());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        // After fade in, start threads
        fadeIn.setOnFinished((e) ->
        {
            LoadConsultants loadConsultants = new LoadConsultants();
            loadConsultants.start();

            LoadOffices loadOffices = new LoadOffices();
            loadOffices.start();

            LoadProjects loadProjects = new LoadProjects();
            loadProjects.start();

            LoadTasks loadTasks = new LoadTasks();
            loadTasks.start();

            fadeOut.play();
        });

        // After fade out, load actual content
        fadeOut.setOnFinished((e) ->
        {
            Menubar menubar = new Menubar();
            MainWindow.root.setLeft(menubar.GetMenubarRoot());

            MainWindow.sender = UIButton.consultantsButton;

            UIButton.consultantsButton.fire();

            isDone = true;
        });
    }

    /**
     * Returns the actual visual splashscreen.
     */
    public VBox loadingScreen()
    {
        VBox loadScreen = new VBox(10);
        loadScreen.setAlignment(Pos.CENTER);
        loadScreen.setStyle("-fx-background-color: #EEF5FD");

        Label title = new Label("Administration Tool");
        title.setStyle("-fx-font-size: 36; -fx-border-color: transparent transparent lightgrey transparent");

        Label status = new Label("Loading");
        status.setStyle("-fx-font-size: 24");

        // Create an arc
        Arc arc = new Arc();

        arc.setCenterX(100.0f);
        arc.setCenterY(100.0f);

        arc.setRadiusX(20.0f);
        arc.setRadiusY(20.0f);

        arc.setStartAngle(0.0f);
        arc.setLength(270.0f);

        arc.setFill(null);
        arc.setStroke(Color.LIGHTPINK);
        arc.setStrokeWidth(2);

        // Instantiating RotateTransition class
        RotateTransition rotate = new RotateTransition();

        rotate.setAxis(Rotate.Z_AXIS);

        rotate.setByAngle(360);

        rotate.setCycleCount(500);

        // Don't stop between cycles
        rotate.setInterpolator(Interpolator.LINEAR);

        rotate.setDuration(Duration.millis(1000));

        rotate.setAutoReverse(false);

        rotate.setNode(arc);

        rotate.play();

        loadScreen.getChildren().addAll(title, arc, status);

        return loadScreen;
    }
}
