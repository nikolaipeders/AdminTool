package Application;

import Domain.Consultant;
import Domain.Office;
import Domain.Project;
import Domain.Task;
import UI.Navigation.Menubar;
import UI.Home;
import UI.Navigation.UIButton;
import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import UI.TableViews.TWProjects;
import UI.TableViews.TWTasks;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow extends Application {

    // Is public and static to enable changing the center from other classes
    public static BorderPane root = new BorderPane();

    // We'll use this to keep track of the latest button pressed!
    public static String sender;
    public static String action;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        int minWidth = 1400;
        int minHeight = 700;

        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);

        // This will not change
        Menubar menubar = new Menubar();
        root.setLeft(menubar.GetMenubarRoot());

        // This will change in runtime but launches with the home screen
        Home homeScreen = new Home();
        root.setCenter(homeScreen.getView());

        // CSS
        root.getStylesheets().add("tableviewStyle.css");
        root.setStyle("-fx-background-color: #FFFFFF");

        Scene scene = new Scene(root, minWidth, minHeight);

        // Enabling switching menu with numpad
        scene.setOnKeyPressed(e ->
                {
                    if (e.getCode() == KeyCode.DIGIT1)
                    {
                        UIButton.homeButton.fire();
                    }
                    if (e.getCode() == KeyCode.DIGIT2)
                    {
                        UIButton.consultantsButton.fire();
                    }
                    if (e.getCode() == KeyCode.DIGIT3)
                    {
                        UIButton.officesButton.fire();
                    }
                    if (e.getCode() == KeyCode.DIGIT4)
                    {
                        UIButton.projectsButton.fire();
                    }
                    if (e.getCode() == KeyCode.DIGIT5)
                    {
                        UIButton.tasksButton.fire();
                    }
                    if (e.getCode() == KeyCode.DIGIT6)
                    {
                        UIButton.reportButton.fire();
                    }
                    if (e.getCode() == KeyCode.DIGIT7)
                    {
                        UIButton.bindingsButton.fire();
                    }
                });

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/resources/logo.png")).toExternalForm()));
        primaryStage.setTitle("Administration");
        primaryStage.show();
    }
}
