package UI.Forms;

import Application.MainWindow;
import Foundation.DAO.DBController;
import Foundation.Modified.TextFieldAutoCompletion;
import Foundation.Modified.TextFieldValidation;
import UI.Misc.PopUp;
import UI.Navigation.UIButton;
import UI.TableViews.TWProjects;
import UI.TableViews.TWTasks;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Time;
import java.util.Objects;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class DialogTasks
{
    // We'll use the DBController several times.
    DBController dbController = new DBController();

    public DialogTasks(Button sender)
    {
    }

    /*
    Opens a new dialog with TextFields and Buttons.
     */
    public void getDialog()
    {
        // Max characters for a time input. We'll use this several times.
        int maxCharacters = 7;

        final Stage dialog = new Stage();
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setTitle("Add " + MainWindow.sender);

        // Disables the option to use main window when dialog is active
        dialog.initModality(Modality.APPLICATION_MODAL);

        // Parent settings
        HBox subRoot = new HBox(10);
        subRoot.setPadding(new Insets(40, 0, 40, 0));
        subRoot.setStyle("-fx-border-radius: 7px; -fx-background-radius: 7px; -fx-border-color: black; -fx-background-color: #FFFFFF");
        subRoot.setAlignment(Pos.CENTER);

        // Scene settings
        Scene dialogScene = new Scene(subRoot, MainWindow.root.getWidth() - 250, 60);
        dialogScene.getStylesheets().add("dialogStyle.css");

        // TextFields. All of these are of "TextFieldValidation" type. This enables the option to use regex for input validation.
        TextFieldValidation nameTextField = new TextFieldValidation();
        nameTextField.setPromptText("Task");
        nameTextField.setOnKeyTyped(event -> nameTextField.validate("text"));

        // This TextField has autocompletion!
        TextFieldAutoCompletion projectTextField = new TextFieldAutoCompletion();
        projectTextField.getResults().addAll(dbController.getProjectNames()); // Autocompletion searches for results in all Projects.
        projectTextField.setPromptText("Project");
        projectTextField.setOnKeyReleased(event ->
        {
            projectTextField.isWrongInput = true; // By default, this TextField is of the wrong input. Only if a match is found it turns to right input.

            for (int i = 0; i < TWProjects.projects.size(); i++)
            {
                if (projectTextField.getText().equals(TWProjects.projects.get(i).getName()))
                {
                    projectTextField.isWrongInput = false;
                }
            }
            projectTextField.changeBackgroundColor(projectTextField.isWrongInput);
        });

        TextFieldValidation mailTextField = new TextFieldValidation();
        mailTextField.setPromptText("Cons. Mail");
        mailTextField.setOnKeyReleased(event -> mailTextField.validate("mail"));

        TextFieldValidation timeTextField = new TextFieldValidation();
        timeTextField.setPromptText("Time Spent");
        timeTextField.setOnKeyReleased(event -> timeTextField.validate("hh:mm:ss"));
        timeTextField.setOnKeyTyped(event ->
        {
            if(timeTextField.getText().length() > maxCharacters) event.consume();
        });

        CheckBox statusCheckBox = new CheckBox("Completed");

        subRoot.getChildren().addAll(nameTextField, projectTextField, mailTextField, timeTextField, statusCheckBox);

        // If the user has selected an item and pressed EDIT, fill the TextFields with data of this item.
        if (TWTasks.selected != null && MainWindow.action.equalsIgnoreCase("edit"))
        {
            nameTextField.setText(TWTasks.selected.getName());
            projectTextField.setText("" + TWTasks.selected.getProjectId());
            mailTextField.setText(TWTasks.selected.getConsultantMail());
            timeTextField.setText(TWTasks.selected.getTimeSpent().toString());
            statusCheckBox.setSelected(TWTasks.selected.getCompleted());
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

        // When user presses the save button, only accept the click if all inputs are of the right format!
        saveButton.setOnAction(event -> {
            if (!nameTextField.isWrongInput &&
                    !mailTextField.isWrongInput &&
                    !timeTextField.isWrongInput)
            {

                // Then set attributes
                TWTasks.selected.setName(nameTextField.getText());
                TWTasks.selected.setConsultantMail(mailTextField.getText());

                for (int i = 0; i < TWProjects.projects.size(); i++)
                {
                    if (projectTextField.getText().equals(TWProjects.projects.get(i).getName()))
                    {
                        TWTasks.selected.setProjectId(TWProjects.projects.get(i).getId());
                    }
                }

                if (timeTextField.getText().length() <= 5)
                {
                    TWTasks.selected.setTimeSpent(Time.valueOf("00:" + timeTextField.getText()));
                }
                else
                {
                    TWTasks.selected.setTimeSpent(Time.valueOf(timeTextField.getText()));
                }

                TWTasks.selected.setCompleted(statusCheckBox.isSelected());

                dbController.updateOrInsertTask(TWTasks.selected);

                // If it's a new item -> add to ObservableList
                if (MainWindow.action.equalsIgnoreCase(UIButton.addButton.getText()))
                {
                    TWTasks.tasks.add(TWTasks.selected);
                }
                TWTasks.taskTableView.refresh();
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
            TWTasks.selected = TWTasks.taskTableView.getSelectionModel().getSelectedItem();
            dialog.close();
        });

        /*  We want to be able to close the dialog with the ESCAPE key
            AND accept input with ENTER key
         */
        dialogScene.setOnKeyPressed(e ->
        {
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
