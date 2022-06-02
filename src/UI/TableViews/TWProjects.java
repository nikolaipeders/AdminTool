package UI.TableViews;

import Domain.Office;
import Domain.Project;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class TWProjects
{
    public static TableView<Project> projectTableView;
    public static ObservableList<Project> projects;
    public static Project selected;
    public static BorderPane subRoot;
    public TWProjects()
    {

    }

    public BorderPane getView()
    {
        subRoot = new BorderPane();
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

        TableColumn<Project, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        projectTableView.getColumns().addAll(column1);

        // Enable selecting an item
        projectTableView.setOnMouseClicked(event ->
        {
            if (projectTableView.getSelectionModel().getSelectedItem() != null)
            {
                selected = projectTableView.getSelectionModel().getSelectedItem();
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
        projectTableView.setOnKeyPressed(e ->
        {
            // Enable opening an item with ENTER key
            if (e.getCode() == KeyCode.ENTER)
            {
                selected = projectTableView.getSelectionModel().getSelectedItem();
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

        return projectTableView;
    }
}
