package UI.TableViews;

import Controller.Controller;
import Domain.Office;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class TWOffices
{
    TableView<Office> tableViewOffices;

    public TWOffices()
    {

    }

    public BorderPane getView()
    {
        BorderPane subRoot = new BorderPane();
        subRoot.setPadding(new Insets(10, 20, 10, 20));

        String sender = "";

        subRoot.setCenter(tableViewOffices());

        ActionBar bb = new ActionBar(UIButton.officesButton.getText());
        subRoot.setBottom(bb.getBottomBar());

        return subRoot;
    }

    public TableView<Office> tableViewOffices()
    {
        tableViewOffices = new TableView<>();
        tableViewOffices.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Office, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Office, String> column2 = new TableColumn<>("Location");
        column2.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Office, String> column3 = new TableColumn<>("Capacity");
        column3.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        TableColumn<Office, String> column4 = new TableColumn<>("Consultants Connected");
        column4.setCellValueFactory(new PropertyValueFactory<>("consultantsConnected"));

        tableViewOffices.getColumns().addAll(column1, column2, column3, column4);

        // Testing
        for (int i = 0; i < 30; i++)
        {
            tableViewOffices.getItems().add(new Office("The office", 30, 20, 10));
            tableViewOffices.getItems().add(new Office("The other office", 30, 20, 10));
        }


        // Fill with data
        Controller.getFromDB("Offices");

        return tableViewOffices;
    }


}
