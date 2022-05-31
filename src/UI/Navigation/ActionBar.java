package UI.Navigation;

import Domain.Consultant;
import Domain.Office;
import Foundation.Modified.TextFieldAutoCompletion;
import Foundation.Modified.TextFieldValidation;
import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ActionBar
{
    public static TextFieldAutoCompletion searchField;
    String sender;
    UIButton uib = new UIButton();

    public ActionBar(String sender)
    {
        this.sender = sender;
    }

    public HBox getBottomBar()
    {
        // HBox settings
        HBox bottomBar = new HBox(30);

        HBox leftSide = new HBox();
        leftSide.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(leftSide, Priority.ALWAYS);

        if (sender.equalsIgnoreCase("consultants"))
        {
            leftSide.getChildren().add(searchBarConsultants());
        }
        else if (sender.equalsIgnoreCase("offices"))
        {
            leftSide.getChildren().add(searchBarOffices());
        }
        else if (sender.equalsIgnoreCase("projects"))
        {

        }
        else if (sender.equalsIgnoreCase("tasks"))
        {

        }

        HBox rightSide = new HBox(30);
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightSide, Priority.ALWAYS);
        rightSide.setPadding(new Insets(0, 20, 0, 0));
        rightSide.setMaxHeight(50);
        rightSide.setMinHeight(50);
        rightSide.getChildren().addAll(uib.addButton(), uib.editButton(), uib.deleteButton(), uib.moveUpButton(), uib.moveDownButton());

        // Add all items and return parent HBox
        bottomBar.getChildren().addAll(leftSide, rightSide);

        return bottomBar;
    }

    public static TextFieldValidation searchBarConsultants()
    {
        searchField = new TextFieldAutoCompletion();
        searchField.setPromptText("Search table");

        // Search for item
        FilteredList<Consultant> filteredList = new FilteredList<>(TWConsultants.consultants, p -> true);
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(consultant -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (consultant.getName().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else if (consultant.getMail().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else if (consultant.getOffice().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else if (consultant.getWorkTime().toString().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else if (consultant.getBreakTime().toString().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else if (consultant.getLongBreakTime().toString().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else if (consultant.getActive().toString().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            });
        }));

        SortedList<Consultant> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(TWConsultants.consultantTableView.comparatorProperty());

        TWConsultants.consultantTableView.setItems(sortedList);

        return searchField;
    }

    public static TextFieldValidation searchBarOffices()
    {
        searchField = new TextFieldAutoCompletion();
        searchField.setPromptText("Search table");

        // Search for item
        FilteredList<Office> filteredList = new FilteredList<>(TWOffices.offices, p -> true);
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(office -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (office.getName().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            });
        }));

        SortedList<Office> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(TWOffices.tableViewOffices.comparatorProperty());

        TWOffices.tableViewOffices.setItems(sortedList);

        return searchField;
    }

}
