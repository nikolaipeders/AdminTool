package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Home
{
    public Home()
    {
    }

    public VBox getView()
    {
        VBox subRoot = new VBox(20);
        subRoot.setPadding(new Insets(100));
        subRoot.setAlignment(Pos.TOP_CENTER);
        subRoot.setStyle("-fx-background-color: #FFFFFF");

        Label titleLabel = new Label("Administration Tool");
        titleLabel.setStyle("-fx-font-size: 24");

        Label bodyLabel = new Label("Select a menu from the menubar to the left to start administrating");
        bodyLabel.setStyle("-fx-font-size: 18");

        Label lowerLabel = new Label("To get help with key bindings, press the lowest option in menubar");
        lowerLabel.setStyle("-fx-font-size: 18");

        subRoot.getChildren().addAll(titleLabel, bodyLabel, lowerLabel);

        return subRoot;
    }

}
