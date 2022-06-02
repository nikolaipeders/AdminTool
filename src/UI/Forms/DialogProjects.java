package UI.Forms;

import Application.MainWindow;
import Domain.Project;
import Foundation.DAO.DBController;
import Foundation.Modified.TextFieldValidation;
import UI.Misc.PopUp;
import UI.Navigation.UIButton;
import UI.TableViews.TWProjects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class DialogProjects
{
    public DialogProjects(Button sender)
    {

    }

    public void getDialog()
    {
        final Stage dialog = new Stage();
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setTitle("Add " + MainWindow.sender);

        // Disables the option to use main window when dialog is active
        dialog.initModality(Modality.APPLICATION_MODAL);

        HBox subRoot = new HBox(10);
        subRoot.setPadding(new Insets(40, 0, 40, 0));
        subRoot.setStyle("-fx-border-radius: 7px; -fx-background-radius: 7px; -fx-border-color: black; -fx-background-color: #FFFFFF");
        subRoot.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(subRoot, MainWindow.root.getWidth() - 250, 60);
        dialogScene.getStylesheets().add("dialogStyle.css");

        // TextFields
        TextFieldValidation nameTextField = new TextFieldValidation();
        nameTextField.setPromptText("Project Name");
        nameTextField.setOnKeyTyped(event -> nameTextField.validate("text"));

        subRoot.getChildren().add(nameTextField);

        // If the user has selected an item and pressed EDIT
        if (TWProjects.selected != null && MainWindow.action.equalsIgnoreCase("edit"))
        {
            nameTextField.setText(TWProjects.selected.getName());
        }

        // Add Save Button (saves to DB)
        Button saveButton = new Button();
        saveButton.setMinSize(30, 30);
        saveButton.setMaxSize(30, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/done.png")).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(saveButton.heightProperty());

        saveButton.setGraphic(imageView);

        subRoot.getChildren().add(saveButton);

        saveButton.setOnAction(event -> {
            if (!nameTextField.isWrongInput)
            {
                // SAVE STUFF WITH DB METHOD IF NOT EXIST CREATE ELSE UPDATE
                if (MainWindow.action.equalsIgnoreCase(UIButton.addButton.getText())) // Check if user pressed the ADD BUTTON
                {
                    TWProjects.selected = new Project(); // THEN WE CREATE A NEW OBJECT
                }
                TWProjects.selected.setName(nameTextField.getText());

                DBController controller = new DBController();
                controller.updateOrInsertProject(TWProjects.selected);

                // If it's a new item
                if (MainWindow.action.equalsIgnoreCase(UIButton.addButton.getText()))
                {
                    TWProjects.projects.add(TWProjects.selected);
                }
                TWProjects.projectTableView.refresh();
                dialog.close();
            }
            else // Show an error message
            {
                PopUp errorMessage = new PopUp();
                errorMessage.popText("Wrong input in some fields!", dialog, dialog.getHeight(), dialog.getWidth());
            }
        });

        // Add cancel button
        Button exitButton = new Button();
        exitButton.setMinSize(30, 30);
        exitButton.setMaxSize(30, 30);

        imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/cancel.png")).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(saveButton.heightProperty());

        exitButton.setGraphic(imageView);
        subRoot.getChildren().add(exitButton);
        exitButton.setOnAction(event -> {
            TWProjects.selected = TWProjects.projectTableView.getSelectionModel().getSelectedItem();
            dialog.close();
        });

        // We want to be able to close the dialog with the ESCAPE key
        dialogScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
            {
                exitButton.fire();
            }
            if (e.getCode() == KeyCode.ENTER)
            {
                saveButton.fire();
            }
        });

        // Initialize Window
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
