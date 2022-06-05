package UI.TableViews;

import Domain.Task;
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

public class TWTasks
{
    public static TableView<Task> taskTableView;
    public static ObservableList<Task> tasks;
    public static Task selected;
    public static BorderPane subRoot;

    public TWTasks()
    {
    }

    /*
    Returns a BorderPane with a tableview at the center and a custom menubar at the bottom.
     */
    public BorderPane getView()
    {
        subRoot = new BorderPane();
        subRoot.setPadding(new Insets(10, 20, 10, 20));

        subRoot.setCenter(tableViewTasks());

        ActionBar bb = new ActionBar(UIButton.tasksButton.getText());
        subRoot.setBottom(bb.getBottomBar());

        return subRoot;
    }

    /**
     * @return TableView for the Task domain.
     */
    public TableView<Task> tableViewTasks()
    {
        taskTableView = new TableView<>();
        taskTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Task, String> taskColumn = new TableColumn<>("Task");
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task, String> mailColumn = new TableColumn<>("Consultant Mail");
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("consultantMail"));

        TableColumn<Task, String> timeColumn = new TableColumn<>("Time spent");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeSpent"));

        TableColumn<Task, Boolean> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
        // Show "completed/in process" instead of true/false
        statusColumn.setCellFactory(tc -> new TableCell<Task, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null :
                        item ? "Completed" : "In process");
            }
        });

        taskTableView.getColumns().addAll(taskColumn, mailColumn, timeColumn, statusColumn);

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
        return taskTableView;
    }
}
