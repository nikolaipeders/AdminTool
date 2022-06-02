package UI.TableViews;

import Domain.Consultant;
import Domain.Task;
import Foundation.DAO.DBController;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;

public class TWTasks
{
    public static TableView<Task> taskTableView;
    public static ObservableList<Task> tasks;
    public static Task selected;
    public static BorderPane subRoot;

    public TWTasks()
    {

    }

    public BorderPane getView()
    {
        subRoot = new BorderPane();
        subRoot.setPadding(new Insets(10, 20, 10, 20));

        subRoot.setCenter(tableViewTasks());

        ActionBar bb = new ActionBar(UIButton.tasksButton.getText());
        subRoot.setBottom(bb.getBottomBar());

        return subRoot;
    }

    public TableView<Task> tableViewTasks()
    {
        taskTableView = new TableView<>();
        taskTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Task, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Task, String> column2 = new TableColumn<>("Mail");
        column2.setCellValueFactory(new PropertyValueFactory<>("consultantMail"));

        TableColumn<Task, String> column3 = new TableColumn<>("Project ID");
        column3.setCellValueFactory(new PropertyValueFactory<>("projectId"));

        TableColumn<Task, String> column4 = new TableColumn<>("Name");
        column4.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task, String> column5 = new TableColumn<>("Time spent");
        column5.setCellValueFactory(new PropertyValueFactory<>("timeSpent"));

        TableColumn<Task, String> column6 = new TableColumn<>("Completed");
        column6.setCellValueFactory(new PropertyValueFactory<>("completed"));

        taskTableView.getColumns().addAll(column1, column2, column3, column4, column5, column6);

        // Enable selecting an item
        taskTableView.setOnMouseClicked(event ->
        {
            if (taskTableView.getSelectionModel().getSelectedItem() != null)
            {
                selected = taskTableView.getSelectionModel().getSelectedItem();
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
        taskTableView.setOnKeyPressed(e ->
        {
            // Enable opening an item with ENTER key
            if (e.getCode() == KeyCode.ENTER)
            {
                selected = taskTableView.getSelectionModel().getSelectedItem();
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
            if (e.isControlDown() && (e.getCode() == KeyCode.F)) {
                ActionBar.searchField.requestFocus();
            }
            if (e.isControlDown() && (e.getCode() == KeyCode.UP)) {
                UIButton.moveUpButton.fire();
            }
            if (e.isControlDown() && (e.getCode() == KeyCode.DOWN)) {
                UIButton.moveDownButton.fire();
            }
        });
        return taskTableView;
    }
}
