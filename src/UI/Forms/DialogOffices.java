package UI.Forms;

import Application.MainWindow;
import Domain.Office;
import Foundation.DAO.DBController;
import Foundation.Modified.TextFieldAutoCompletion;
import Foundation.Modified.TextFieldValidation;
import UI.Misc.PopUp;
import UI.Navigation.UIButton;
import UI.TableViews.TWOffices;
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

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class DialogOffices
{

    // We'll use the DBController several times.
    DBController dbController = new DBController();

    public DialogOffices(Button sender)
    {
    }

    /*
    Opens a new dialog with TextFields and Buttons.
     */
    public void getDialog()
    {
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
        nameTextField.setPromptText("Office Name");
        nameTextField.setOnKeyTyped(event -> nameTextField.validate("text"));

        TextFieldValidation locationTextField = new TextFieldValidation();
        locationTextField.setPromptText("Location");
        locationTextField.setOnKeyReleased(event -> locationTextField.validate("numbers"));

        TextFieldAutoCompletion capacityTextField = new TextFieldAutoCompletion();
        capacityTextField.setPromptText("Capacity");
        capacityTextField.setOnKeyReleased(event -> capacityTextField.validate("numbers"));

        // This TextField can't be edited as it's the amount of consultants connected, which is calculated with SQL.
        TextFieldValidation consultantsTextField = new TextFieldValidation();
        consultantsTextField.setEditable(false);
        consultantsTextField.setStyle("-fx-background-color: #EDEDED"); // Help the user understand that this is uneditable.
        consultantsTextField.setOnKeyReleased(event -> consultantsTextField.validate("numbers"));

        subRoot.getChildren().addAll(nameTextField, locationTextField, capacityTextField, consultantsTextField);

        // If the user has selected an item and pressed EDIT, fill the TextFields with data of this item.
        if (TWOffices.selected != null && MainWindow.action.equalsIgnoreCase("edit"))
        {
            nameTextField.setText(TWOffices.selected.getName());
            locationTextField.setText("" + TWOffices.selected.getLocation());
            capacityTextField.setText("" + TWOffices.selected.getCapacity());
            consultantsTextField.setText("" + TWOffices.selected.getConsultantsConnected());
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
                    !locationTextField.isWrongInput &&
                    !capacityTextField.isWrongInput &&
                    !consultantsTextField.isWrongInput)
            {
                // Then set attributes
                TWOffices.selected.setName(nameTextField.getText());
                TWOffices.selected.setLocation(Integer.parseInt(locationTextField.getText()));
                TWOffices.selected.setCapacity(Integer.parseInt(capacityTextField.getText()));

                dbController.updateOrInsertOffice(TWOffices.selected);

                // If it's a new item -> add to ObservableList
                if (MainWindow.action.equalsIgnoreCase(UIButton.addButton.getText()))
                {
                    TWOffices.offices.add(TWOffices.selected);
                }
                TWOffices.tableViewOffices.refresh();
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
            TWOffices.selected = TWOffices.tableViewOffices.getSelectionModel().getSelectedItem();
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
