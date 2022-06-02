package Application;

import UI.Misc.Splash;
import UI.Navigation.UIButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow extends Application {

    // Is public and static to enable changing the center from other classes
    public static BorderPane root = new BorderPane();

    // We'll use this to keep track of the latest button pressed!
    public static Button sender;
    public static String action;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        int minWidth = 1400;
        int minHeight = 700;

        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);

        // This will change in runtime but launches with the home screen
        Splash loadScreen = new Splash();
        loadScreen.loadSplashScreen();

        // CSS
        root.getStylesheets().add("tableviewStyle.css");
        root.setStyle("-fx-background-color: #FFFFFF");

        Scene scene = new Scene(root, minWidth, minHeight);

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
                            UIButton.reportButton.fire();
                        }
                        if (e.getCode() == KeyCode.DIGIT6)
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
