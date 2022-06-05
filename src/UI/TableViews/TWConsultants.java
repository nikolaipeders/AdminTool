package UI.TableViews;

import Domain.Consultant;
import Foundation.DAO.DBController;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class TWConsultants
{
    public static TableView<Consultant> consultantTableView;
    public static ObservableList<Consultant> consultants;
    public static Consultant selected;
    public static BorderPane subRoot;

    public TWConsultants()
    {
    }

    /*
    Returns a BorderPane with a tableview at the center and a custom menubar at the bottom.
     */
    public BorderPane getView()
    {
        subRoot = new BorderPane();
        subRoot.setPadding(new Insets(10, 20, 10, 20));

        subRoot.setCenter(tableViewConsultant());

        ActionBar bb = new ActionBar(UIButton.consultantsButton.getText());
        subRoot.setBottom(bb.getBottomBar());

        return subRoot;
    }

    /**
     * @return TableView for the Consultant domain.
     */
    public TableView<Consultant> tableViewConsultant()
    {
        consultantTableView = new TableView<>();
        consultantTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Consultant, String> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Consultant, String> columnMail = new TableColumn<>("Mail");
        columnMail.setCellValueFactory(new PropertyValueFactory<>("mail"));

        TableColumn<Consultant, String> columnOffice = new TableColumn<>("Office");
        columnOffice.setCellValueFactory(new PropertyValueFactory<>("office"));

        TableColumn<Consultant, String> columnWorkTime = new TableColumn<>("Work Time");
        columnWorkTime.setCellValueFactory(new PropertyValueFactory<>("workTime"));

        TableColumn<Consultant, String> columnBreakTime = new TableColumn<>("Break Time");
        columnBreakTime.setCellValueFactory(new PropertyValueFactory<>("breakTime"));

        TableColumn<Consultant, String> columnLBreakTime = new TableColumn<>("Long Break Time");
        columnLBreakTime.setCellValueFactory(new PropertyValueFactory<>("longBreakTime"));

        TableColumn<Consultant, Boolean> columnStatus = new TableColumn<>("Status");
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("active"));

        // Show "active/inactive instead of true/false
        columnStatus.setCellFactory(tc -> new TableCell<Consultant, Boolean>()
        {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null :
                        item ? "Active" : "Inactive");
            }
        });

        consultantTableView.getColumns().addAll(columnName, columnMail, columnOffice, columnWorkTime, columnBreakTime, columnLBreakTime, columnStatus);

        // Enable selecting an item
        consultantTableView.setOnMouseClicked(event ->
        {
            if (consultantTableView.getSelectionModel().getSelectedItem() != null)
            {
                selected = consultantTableView.getSelectionModel().getSelectedItem();
            }
            if (event.getButton().equals(MouseButton.PRIMARY))
            {
                if (event.getClickCount() == 2)
                {
                    UIButton.editButton.fire();
                }
            }
        });

        // Enable KEY bindings
        consultantTableView.setOnKeyPressed(e ->
        {
            // Enable opening an item with ENTER key
            if (e.getCode() == KeyCode.ENTER)
            {
                selected = consultantTableView.getSelectionModel().getSelectedItem();
                UIButton.editButton.fire();
            }
            // Enable adding an item with PLUS key
            if (e.getCode() == KeyCode.PLUS)
            {
                UIButton.addButton.fire();
            }
            if (e.getCode() == KeyCode.DELETE)
            {
                UIButton.deleteButton.fire();
            }
            if (e.isControlDown() && (e.getCode() == KeyCode.F))
            {
                ActionBar.searchField.requestFocus();
            }
            if (e.isControlDown() && (e.getCode() == KeyCode.UP))
            {
                UIButton.moveUpButton.fire();
            }
            if (e.isControlDown() && (e.getCode() == KeyCode.DOWN))
            {
                UIButton.moveDownButton.fire();
            }
        });
        return consultantTableView;
    }
}
