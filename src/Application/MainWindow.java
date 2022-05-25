package Application;

import UI.Navigation.Menubar;
import UI.Overview;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow extends Application {

    // Is public and static to enable changing the center from other classes
    public static BorderPane root = new BorderPane();

    // We'll use this to keep track of latest button pressed!
    public static String sender;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        int minWidth = 1200;
        int minHeight = 700;

        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);

        // This will not change
        Menubar menubar = new Menubar();
        root.setLeft(menubar.GetMenubarRoot());

        // This will change in runtime but launches with the overview
        Overview overview = new Overview();
        root.setCenter(overview.getView());

        // CSS
        root.getStylesheets().add("tableviewStyle.css");
        root.setStyle("-fx-background-color: #FFFFFF");

        Scene scene = new Scene(root, minWidth, minHeight);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/resources/logo.png")).toExternalForm()));
        primaryStage.setTitle("Administration");
        primaryStage.show();
    }
}
