package UI.Forms;

import Application.MainWindow;
import Foundation.TextFieldValidation;
import UI.Misc.PopUp;
import UI.TableViews.TWConsultants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class DialogConsultants
{
    public DialogConsultants(String sender)
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
        subRoot.setPadding(new Insets(40, 50, 40, 50));
        subRoot.setStyle("-fx-border-radius: 7px; -fx-background-radius: 7px; -fx-border-color: black; -fx-background-color: #FFFFFF");
        subRoot.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(subRoot, MainWindow.root.getWidth() - 100, 60);
        dialogScene.getStylesheets().add("dialogStyle.css");

        // TextFields
        TextFieldValidation nameTextField = new TextFieldValidation();
        nameTextField.setPromptText("Name");
        nameTextField.setOnKeyReleased(event ->
        {
                nameTextField.validate("text");
        });

        TextFieldValidation mailTextField = new TextFieldValidation();
        mailTextField.setPromptText("Mail");
        mailTextField.setOnKeyReleased(event ->
        {
            mailTextField.validate("mail");
        });

        TextFieldValidation officeTextField = new TextFieldValidation();
        officeTextField.setPromptText("Office");
        officeTextField.setOnKeyReleased(event ->
        {
            officeTextField.validate("text");
        });

        TextFieldValidation workTimeTextField = new TextFieldValidation();
        workTimeTextField.setPromptText("Work");
        workTimeTextField.setOnKeyReleased(event ->
        {
            workTimeTextField.validate("time");
        });

        TextFieldValidation breakTimeTextField = new TextFieldValidation();
        breakTimeTextField.setPromptText("Break");
        breakTimeTextField.setOnKeyReleased(event ->
        {
            breakTimeTextField.validate("time");
        });

        TextFieldValidation lBreakTimeTextField = new TextFieldValidation();
        lBreakTimeTextField.setPromptText("Long Break");
        lBreakTimeTextField.setOnKeyReleased(event ->
        {
            lBreakTimeTextField.validate("time");
        });

        subRoot.getChildren().addAll(nameTextField, mailTextField, officeTextField, workTimeTextField, breakTimeTextField, lBreakTimeTextField);

        // If the user has selected an item and pressed EDIT
        if (TWConsultants.selected != null && MainWindow.action.equalsIgnoreCase("edit"))
        {
            nameTextField.setText(TWConsultants.selected.getName());
            mailTextField.setText(TWConsultants.selected.getMail());
            officeTextField.setText(TWConsultants.selected.getOffice());
            workTimeTextField.setText(TWConsultants.selected.getWorkTime());
            breakTimeTextField.setText(TWConsultants.selected.getBreakTime());
            lBreakTimeTextField.setText(TWConsultants.selected.getLongBreakTime());
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
                    !officeTextField.isWrongInput &&
                    !workTimeTextField.isWrongInput &&
                    !breakTimeTextField.isWrongInput &&
                    !lBreakTimeTextField.isWrongInput)
            {
                // SAVE STUFF WITH DB METHOD IF NOT EXIST CREATE ELSE UPDATE
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
