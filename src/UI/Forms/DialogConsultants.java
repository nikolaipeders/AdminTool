package UI.Forms;

import Application.MainWindow;
import Domain.Consultant;
import Foundation.DAO.DBController;
import Foundation.Modified.TextFieldAutoCompletion;
import Foundation.Modified.TextFieldValidation;
import UI.Misc.PopUp;
import UI.Navigation.UIButton;
import UI.TableViews.TWConsultants;
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


public class DialogConsultants
{
    public DialogConsultants(String sender)
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
        nameTextField.setPromptText("Name");
        nameTextField.setOnKeyTyped(event -> nameTextField.validate("text"));


        TextFieldValidation mailTextField = new TextFieldValidation();
        mailTextField.setPromptText("Mail");
        mailTextField.setOnKeyReleased(event -> mailTextField.validate("mail"));

        TextFieldAutoCompletion officeTextField = new TextFieldAutoCompletion();
        DBController dbController = new DBController();
        officeTextField.getResults().addAll(dbController.getOfficeNames());
        officeTextField.setPromptText("Office");
        officeTextField.setOnKeyReleased(event -> officeTextField.validate("text"));

        TextFieldValidation workTimeTextField = new TextFieldValidation();
        workTimeTextField.setPrefWidth(100);
        workTimeTextField.setPromptText("Work");
        workTimeTextField.setOnKeyReleased(event -> workTimeTextField.validate("time"));
        workTimeTextField.setOnKeyTyped(event ->
        {
            if(workTimeTextField.getText().length() > maxCharacters) event.consume();
        });

        TextFieldValidation breakTimeTextField = new TextFieldValidation();
        breakTimeTextField.setPrefWidth(100);
        breakTimeTextField.setPromptText("Break");
        breakTimeTextField.setOnKeyReleased(event -> breakTimeTextField.validate("time"));
        breakTimeTextField.setOnKeyTyped(event ->
        {
            if(breakTimeTextField.getText().length() > maxCharacters) event.consume();
        });

        TextFieldValidation lBreakTimeTextField = new TextFieldValidation();
        lBreakTimeTextField.setPrefWidth(100);
        lBreakTimeTextField.setPromptText("Long Break");
        lBreakTimeTextField.setOnKeyReleased(event -> lBreakTimeTextField.validate("time"));
        lBreakTimeTextField.setOnKeyTyped(event ->
        {
            if(lBreakTimeTextField.getText().length() > maxCharacters) event.consume();
        });

        CheckBox statusCheckBox = new CheckBox("Active");

        subRoot.getChildren().addAll(nameTextField, mailTextField, officeTextField, workTimeTextField, breakTimeTextField, lBreakTimeTextField, statusCheckBox);

        // If the user has selected an item and pressed EDIT
        if (TWConsultants.selected != null && MainWindow.action.equalsIgnoreCase("edit"))
        {
            nameTextField.setText(TWConsultants.selected.getName());
            mailTextField.setText(TWConsultants.selected.getMail());
            officeTextField.setText(TWConsultants.selected.getOffice());
            workTimeTextField.setText(TWConsultants.selected.getWorkTime().toString().substring(3));
            breakTimeTextField.setText(TWConsultants.selected.getBreakTime().toString().substring(3));
            lBreakTimeTextField.setText(TWConsultants.selected.getLongBreakTime().toString().substring(3));
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
                    !officeTextField.isWrongInput &&
                    !workTimeTextField.isWrongInput &&
                    !breakTimeTextField.isWrongInput &&
                    !lBreakTimeTextField.isWrongInput)
            {
                // SAVE STUFF WITH DB METHOD IF NOT EXIST CREATE ELSE UPDATE
                DBController consultantDAOImplementation = new DBController();
                if (MainWindow.action.equalsIgnoreCase(UIButton.addButton.getText()))
                {
                    TWConsultants.selected = new Consultant();
                }
                TWConsultants.selected.setName(nameTextField.getText());
                TWConsultants.selected.setMail(mailTextField.getText());
                TWConsultants.selected.setOffice(officeTextField.getText());
                if (workTimeTextField.getText().length() <= 5)
                {
                    TWConsultants.selected.setWorkTime(Time.valueOf("00:" + workTimeTextField.getText()));
                }
                else
                {
                    TWConsultants.selected.setWorkTime(Time.valueOf(workTimeTextField.getText()));
                }
                if (breakTimeTextField.getText().length() <= 5)
                {
                    TWConsultants.selected.setBreakTime(Time.valueOf("00:" + breakTimeTextField.getText()));
                }
                else
                {
                    TWConsultants.selected.setBreakTime(Time.valueOf(breakTimeTextField.getText()));
                }
                if (lBreakTimeTextField.getText().length() <= 5)
                {
                    TWConsultants.selected.setLongBreakTime(Time.valueOf("00:" + lBreakTimeTextField.getText()));
                }
                else
                {
                    TWConsultants.selected.setLongBreakTime(Time.valueOf(lBreakTimeTextField.getText()));
                }

                TWConsultants.selected.setActive(statusCheckBox.isSelected());

                //TWConsultants.consultants.add(TWConsultants.selected);
                consultantDAOImplementation.updateOrInsertConsultant(TWConsultants.selected);

                // If it's a new item
                if (MainWindow.action.equalsIgnoreCase(UIButton.addButton.getText()))
                {
                    TWConsultants.consultants.add(TWConsultants.selected);
                }
                TWConsultants.consultantTableView.refresh();
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
