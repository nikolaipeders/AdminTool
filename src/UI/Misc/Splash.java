package UI.Misc;

import Application.MainWindow;
import Foundation.Loading.LoadConsultants;
import Foundation.Loading.LoadOffices;
import Foundation.Loading.LoadProjects;
import Foundation.Loading.LoadTasks;
import UI.Navigation.Menubar;
import UI.TableViews.TWConsultants;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Splash
{

    public void loadSplashScreen() {

        MainWindow.root.setCenter(loadingScreen());

        // Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2),loadingScreen());
        fadeIn.setFromValue(0);
        fadeIn.setToValue(2);
        fadeIn.setCycleCount(1);

        // Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), loadingScreen());
        fadeOut.setFromValue(2);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        // After fade in, start threads
        fadeIn.setOnFinished((e) -> {

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
        fadeOut.setOnFinished((e) -> {
            Menubar menubar = new Menubar();
            MainWindow.root.setLeft(menubar.GetMenubarRoot());

            TWConsultants twConsultants = new TWConsultants();
            MainWindow.root.setCenter(twConsultants.getView());

        });
    }

    public VBox loadingScreen()
    {
        VBox loadScreen = new VBox(10);
        loadScreen.setAlignment(Pos.CENTER);
        loadScreen.setStyle("-fx-background-color: #EEF5FD");

        Label title = new Label("Administration Tool");
        title.setStyle("-fx-font-size: 36; -fx-border-color: transparent transparent lightgrey transparent");

        Label loadingMessage = new Label("Loading");
        loadingMessage.setStyle("-fx-font-size: 18");

        // Create an arc
        Arc arc = new Arc();

        // Set center
        arc.setCenterX(100.0f);
        arc.setCenterY(100.0f);

        // Set radius
        arc.setRadiusX(20.0f);
        arc.setRadiusY(20.0f);

        // Set start angle and length
        arc.setStartAngle(0.0f);
        arc.setLength(270.0f);

        // Set colors
        arc.setFill(null);
        arc.setStroke(Color.LIGHTPINK);
        arc.setStrokeWidth(2);

        // Instantiating RotateTransition class
        RotateTransition rotate = new RotateTransition();

        // Setting Axis of rotation
        rotate.setAxis(Rotate.Z_AXIS);

        // Setting the angle of rotation
        rotate.setByAngle(360);

        // Setting cycle count of the rotation
        rotate.setCycleCount(500);

        // Don't stop between cycles
        rotate.setInterpolator(Interpolator.LINEAR);

        // Setting duration of the transition
        rotate.setDuration(Duration.millis(1000));

        // Don't auto reverse on cycle end
        rotate.setAutoReverse(false);

        // Select node to be rotating
        rotate.setNode(arc);

        // Play the transition
        rotate.play();

        loadScreen.getChildren().addAll(title, arc, loadingMessage);

        return loadScreen;
    }
}
