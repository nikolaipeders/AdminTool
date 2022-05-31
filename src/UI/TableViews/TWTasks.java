package UI.TableViews;

import Domain.Task;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class TWTasks
{
    public static TableView<Task> taskTableView;

    public TWTasks()
    {

    }

    public BorderPane getView()
    {
        BorderPane subRoot = new BorderPane();
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


        return taskTableView;
    }
}
