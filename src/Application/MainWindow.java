package Application;

import Foundation.Loading.LoadConsultants;
import Foundation.Loading.LoadOffices;
import Foundation.Loading.LoadProjects;
import Foundation.Loading.LoadTasks;
import UI.Misc.Splash;
import UI.Navigation.UIButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @Author Nikolai P on 05-06-2022.
 * @Version 1.0
 * Final release.
 */

public class MainWindow extends Application {

    // Is public and static to enable changing the center from other classes.
    public static BorderPane root = new BorderPane();

    // We'll use this to keep track of the latest menu button pressed.
    public static Button sender;

    // We'll use this to note which action button have been pressed.
    public static String action;

    // For pop-ups, we need access to the stage.
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        stage = primaryStage;

        int minWidth = 1400;
        int minHeight = 700;

        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);

        // Load splashscreen on launch
        Splash loadScreen = new Splash();
        loadScreen.loadSplashScreen();

        // CSS
        root.getStylesheets().add("tableviewStyle.css");
        root.setStyle("-fx-background-color: #FFFFFF;");

        Scene scene = new Scene(root, minWidth, minHeight);

        // Refresh content per minute
        Timeline timelineRefresher = new Timeline(new KeyFrame(javafx.util.Duration.millis(10000), (e) ->
        {
            primaryStage.setTitle("Updating");
            try {
            if (sender != UIButton.consultantsButton)
            {
                LoadConsultants loadConsultants = new LoadConsultants();
                loadConsultants.start();
                loadConsultants.join();
            }
            if (sender != UIButton.officesButton)
            {
                LoadOffices loadOffices = new LoadOffices();
                loadOffices.start();
                loadOffices.join();
            }
            if (sender != UIButton.projectsButton)
            {
                LoadProjects loadProjects = new LoadProjects();
                loadProjects.start();
                loadProjects.join();
            }
            if (sender != UIButton.tasksButton)
            {
                LoadTasks loadTasks = new LoadTasks();
                loadTasks.start();
                loadTasks.join();
            }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            primaryStage.setTitle("Administration");

        }));
        timelineRefresher.setCycleCount(Timeline.INDEFINITE);
        timelineRefresher.play();

        // Enabling switching menu with numpad
        scene.setOnKeyPressed(e ->
                {
                    if (loadScreen.isDone)
                    {
                        if (e.getCode() == KeyCode.DIGIT1)
                        {
                            UIButton.consultantsButton.fire();
                        }
                        if (e.getCode() == KeyCode.DIGIT2)
                        {
                            UIButton.officesButton.fire();
                        }
                        if (e.getCode() == KeyCode.DIGIT3)
                        {
                            UIButton.projectsButton.fire();
                        }
                        if (e.getCode() == KeyCode.DIGIT4)
                        {
                            UIButton.tasksButton.fire();
                        }
                        if (e.getCode() == KeyCode.DIGIT5)
                        {
                            UIButton.bindingsButton.fire();
                        }
                    }
                });

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/resources/logo.png")).toExternalForm()));
        primaryStage.setTitle("Administration");
        primaryStage.show();
    }
}
