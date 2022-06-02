package UI.Forms;

import Application.MainWindow;
import Domain.Task;
import Foundation.DAO.DBController;
import Foundation.Modified.TextFieldValidation;
import UI.Misc.PopUp;
import UI.Navigation.UIButton;
import UI.TableViews.TWConsultants;
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


public class DialogTasks
{
    public DialogTasks(Button sender)
    {

    }

    public void getDialog()
    {
        // Max characters for a time input. We'll use this several times.
        int maxCharacters = 4;

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
        nameTextField.setPromptText("Task");
        nameTextField.setOnKeyTyped(event -> nameTextField.validate("text"));

        TextFieldValidation mailTextField = new TextFieldValidation();
        mailTextField.setPromptText("Cons. Mail");
        mailTextField.setOnKeyReleased(event -> mailTextField.validate("mail"));

        TextFieldValidation timeTextField = new TextFieldValidation();
        timeTextField.setPromptText("Time Spent");
        timeTextField.setOnKeyReleased(event -> timeTextField.validate("time"));
        timeTextField.setOnKeyTyped(event ->
        {
            if(timeTextField.getText().length() > maxCharacters) event.consume();
        });

        CheckBox statusCheckBox = new CheckBox("Active");

        subRoot.getChildren().addAll(nameTextField, mailTextField, timeTextField, statusCheckBox);

        // If the user has selected an item and pressed EDIT
        if (TWConsultants.selected != null && MainWindow.action.equalsIgnoreCase("edit"))
        {
            nameTextField.setText(TWConsultants.selected.getName());
            mailTextField.setText(TWConsultants.selected.getMail());
            timeTextField.setText(TWConsultants.selected.getWorkTime().toString());
            statusCheckBox.setSelected(TWConsultants.selected.getActive());
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
            if (!nameTextField.isWrongInput &&
                    !mailTextField.isWrongInput &&
                    !timeTextField.isWrongInput)
            {
                // SAVE STUFF WITH DB METHOD IF NOT EXIST CREATE ELSE UPDATE
                if (MainWindow.action.equalsIgnoreCase(UIButton.addButton.getText()))
                {
                    TWTasks.selected = new Task();
                }
                TWTasks.selected.setName(nameTextField.getText());
                TWTasks.selected.setConsultantMail(mailTextField.getText());
                if (timeTextField.getText().length() <= 5)
                {
                    TWTasks.selected.setTimeSpent(Time.valueOf("00:" + timeTextField.getText()));
                }
                else
                {
                    TWTasks.selected.setTimeSpent(Time.valueOf(timeTextField.getText()));
                }

                TWTasks.selected.setCompleted(statusCheckBox.isSelected());

                DBController dbController = new DBController();
                dbController.updateOrInsertConsultant(TWConsultants.selected);

                // If it's a new item
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
            TWConsultants.selected = TWConsultants.consultantTableView.getSelectionModel().getSelectedItem();
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
