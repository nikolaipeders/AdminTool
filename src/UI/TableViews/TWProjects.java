package UI.TableViews;

import Controller.Controller;
import Domain.Project;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class TWProjects
{
    public static TableView<Project> projectTableView;
    public TWProjects()
    {

    }

    public BorderPane getView()
    {
        BorderPane subRoot = new BorderPane();
        subRoot.setPadding(new Insets(10, 20, 10, 20));

        subRoot.setCenter(tableViewProjects());

        ActionBar bb = new ActionBar(UIButton.projectsButton.getText());
        subRoot.setBottom(bb.getBottomBar());

        return subRoot;
    }

    public TableView<Project> tableViewProjects()
    {
        projectTableView = new TableView<>();
        projectTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Project, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Project, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        projectTableView.getColumns().addAll(column1, column2);

        // Testing
        projectTableView.getItems().add(new Project(1, "A project"));

        // Fill with data
        Controller.getFromDB("Projects");

        return projectTableView;
    }
}
