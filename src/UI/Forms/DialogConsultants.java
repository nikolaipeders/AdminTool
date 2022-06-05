package UI.Forms;

import Application.MainWindow;
import Foundation.DAO.DBController;
import Foundation.Modified.TextFieldAutoCompletion;
import Foundation.Modified.TextFieldValidation;
import UI.Misc.PopUp;
import UI.Navigation.UIButton;
import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
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

public class DialogConsultants
{
    // We'll use the DBController several times.
    DBController dbController = new DBController();
    public DialogConsultants(Button sender)
    {
    }

    /*
    Opens a new dialog with TextFields and Buttons.
     */
    public void getDialog()
    {
        // Max characters for a time input. We'll use this several times.
        int maxCharacters = 4;

        final Stage dialog = new Stage();
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setTitle("" + MainWindow.sender);

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
        nameTextField.setPromptText("Name");
        nameTextField.setOnKeyTyped(event -> nameTextField.validate("text"));

        TextFieldValidation mailTextField = new TextFieldValidation();
        mailTextField.setPromptText("Mail");
        mailTextField.setOnKeyReleased(event -> mailTextField.validate("mail"));

        // This TextField has autocompletion!
        TextFieldAutoCompletion officeTextField = new TextFieldAutoCompletion();
        officeTextField.getResults().addAll(dbController.getOfficeNames()); // Autocompletion searches for results in all offices.
        officeTextField.setPromptText("Office");
        officeTextField.setOnKeyReleased(event ->
        {
            officeTextField.isWrongInput = true; // By default, this TextField is of the wrong input. Only if a match is found it turns to right input.

            for (int i = 0; i < TWOffices.offices.size(); i++)
            {
                if (officeTextField.getText().equals(TWOffices.offices.get(i).getName()))
                {
                    officeTextField.isWrongInput = false;
                }
            }
            officeTextField.changeBackgroundColor(officeTextField.isWrongInput);
        });

        TextFieldValidation workTimeTextField = new TextFieldValidation();
        workTimeTextField.setPrefWidth(100);
        workTimeTextField.setPromptText("Work");
        workTimeTextField.setOnKeyReleased(event -> workTimeTextField.validate("mm:ss"));
        workTimeTextField.setOnKeyTyped(event ->
        {
            if(workTimeTextField.getText().length() > maxCharacters) event.consume();
        });

        TextFieldValidation breakTimeTextField = new TextFieldValidation();
        breakTimeTextField.setPrefWidth(100);
        breakTimeTextField.setPromptText("Break");
        breakTimeTextField.setOnKeyReleased(event -> breakTimeTextField.validate("mm:ss"));
        breakTimeTextField.setOnKeyTyped(event ->
        {
            if(breakTimeTextField.getText().length() > maxCharacters) event.consume();
        });

        TextFieldValidation lBreakTimeTextField = new TextFieldValidation();
        lBreakTimeTextField.setPrefWidth(100);
        lBreakTimeTextField.setPromptText("Long Break");
        lBreakTimeTextField.setOnKeyReleased(event -> lBreakTimeTextField.validate("mm:ss"));
        lBreakTimeTextField.setOnKeyTyped(event ->
        {
            if(lBreakTimeTextField.getText().length() > maxCharacters) event.consume();
        });

        // CheckBox to select the status of a consultant.
        CheckBox statusCheckBox = new CheckBox("Active");

        subRoot.getChildren().addAll(nameTextField, mailTextField, officeTextField, workTimeTextField, breakTimeTextField, lBreakTimeTextField, statusCheckBox);

        // If the user has selected an item and pressed EDIT, fill the TextFields with data of this item.
        if (TWConsultants.selected != null && MainWindow.action.equalsIgnoreCase(UIButton.editButton.getText()))
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

        // When user presses the save button, only accept the click if all inputs are of the right format!
        saveButton.setOnAction(event ->
        {
            if (!nameTextField.isWrongInput &&
                    !mailTextField.isWrongInput &&
                    !officeTextField.isWrongInput &&
                    !workTimeTextField.isWrongInput &&
                    !breakTimeTextField.isWrongInput &&
                    !lBreakTimeTextField.isWrongInput)
            {

                // Start by removing the consultant from the office.
                for (int i = 0; i < TWOffices.offices.size(); i++)
                {
                    if (TWOffices.offices.get(i).getName().equalsIgnoreCase(TWConsultants.selected.getOffice()))
                    {
                        TWOffices.offices.get(i).setConsultantsConnected(TWOffices.offices.get(i).getConsultantsConnected()-1);
                    }
                }

                // Then set attributes
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

                // Update consultant
                dbController.updateOrInsertConsultant(TWConsultants.selected);

                // Run through all offices and update attached consultants.
                for (int i = 0; i < TWOffices.offices.size(); i++)
                {
                    dbController.updateOrInsertOffice(TWOffices.offices.get(i));
                }

                // Also update in our Observable List to save a DB query.
                for (int i = 0; i < TWOffices.offices.size(); i++)
                {
                    if (TWOffices.offices.get(i).getName().equalsIgnoreCase(TWConsultants.selected.getOffice()))
                    {
                        TWOffices.offices.get(i).setConsultantsConnected(TWOffices.offices.get(i).getConsultantsConnected()+1);
                    }
                }

                // If it's a new item -> add to ObservableList
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

        exitButton.setOnAction(event ->
        {
            TWConsultants.selected = TWConsultants.consultantTableView.getSelectionModel().getSelectedItem();
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
