package UI.Forms;

import Application.MainWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class AddDialog
{
    public AddDialog(String sender)
    {

    }

    public HBox getDialog()
    {
        HBox subRoot = new HBox(10);
        subRoot.setPadding(new Insets(40, 100, 40, 100));
        subRoot.setStyle("-fx-border-radius: 7px; -fx-background-radius: 7px; -fx-border-color: black; -fx-background-color: #FFFFFF");
        subRoot.setAlignment(Pos.CENTER);

        // Starting procedures and Set the Scene
        final Stage dialog = new Stage();
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.UNDECORATED);

        dialog.setTitle("Add " + MainWindow.sender);

        //dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        Scene dialogScene = new Scene(subRoot, MainWindow.root.getWidth() - 100, 60);
        dialogScene.getStylesheets().add("dialogStyle.css");


        // Disables the option to use main window when dialog is active
        dialog.initModality(Modality.APPLICATION_MODAL);

        // TextFields
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");

        TextField mailTextField = new TextField();
        mailTextField.setPromptText("Mail");

        TextField officeTextField = new TextField();
        officeTextField.setPromptText("Office");

        TextField workTimeTextField = new TextField();
        workTimeTextField.setPromptText("Work");

        TextField breakTimeTextField = new TextField();
        breakTimeTextField.setPromptText("Break");

        TextField lBreakTimeTextField = new TextField();
        lBreakTimeTextField.setPromptText("Long Break");

        subRoot.getChildren().addAll(nameTextField, mailTextField, officeTextField, workTimeTextField, breakTimeTextField, lBreakTimeTextField);

        // Add Save Button (saves to DB)
        Button saveButton = new Button();
        saveButton.setStyle("-fx-background-color: null");
        saveButton.setMinSize(30, 30);
        saveButton.setMaxSize(30, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/done.png")).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(saveButton.heightProperty());

        saveButton.setGraphic(imageView);

        subRoot.getChildren().add(saveButton);
        saveButton.setOnAction(event -> {
            dialog.close();
        });

        // Add cancel button
        Button exitButton = new Button();
        exitButton.setStyle("-fx-background-color: null");
        exitButton.setMinSize(30, 30);
        exitButton.setMaxSize(30, 30);

        imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/cancel.png")).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(saveButton.heightProperty());

        exitButton.setGraphic(imageView);
        subRoot.getChildren().add(exitButton);
        exitButton.setOnAction(event -> {
            dialog.close();
        });

        // Initialize Window
        dialog.setScene(dialogScene);
        dialog.show();

        return subRoot;
    }
}
