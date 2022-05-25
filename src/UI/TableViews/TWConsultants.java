package UI.TableViews;

import Controller.Controller;
import Domain.Consultant;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class TWConsultants
{

    public static TableView<Consultant> consultantTableView;

    public TWConsultants()
    {
    }

    public BorderPane getView()
    {
        BorderPane subRoot = new BorderPane();
        subRoot.setPadding(new Insets(10, 20, 10, 20));

        subRoot.setCenter(tableViewConsultant());

        ActionBar bb = new ActionBar(UIButton.consultantsButton.getText());
        subRoot.setBottom(bb.getBottomBar());

        return subRoot;
    }

    public TableView<Consultant> tableViewConsultant()
    {
        consultantTableView = new TableView<>();
        consultantTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Consultant, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Consultant, String> column2 = new TableColumn<>("Mail");
        column2.setCellValueFactory(new PropertyValueFactory<>("mail"));

        TableColumn<Consultant, String> column3 = new TableColumn<>("Office");
        column3.setCellValueFactory(new PropertyValueFactory<>("office"));

        TableColumn<Consultant, String> column4 = new TableColumn<>("Work Time");
        column4.setCellValueFactory(new PropertyValueFactory<>("workTime"));

        TableColumn<Consultant, String> column5 = new TableColumn<>("Break Time");
        column5.setCellValueFactory(new PropertyValueFactory<>("breakTime"));

        TableColumn<Consultant, String> column6 = new TableColumn<>("Long Break Time");
        column6.setCellValueFactory(new PropertyValueFactory<>("longBreakTime"));

        TableColumn<Consultant, String> column7 = new TableColumn<>("Active");
        column7.setCellValueFactory(new PropertyValueFactory<>("active"));

        consultantTableView.setRowFactory(tableView -> {
            TableRow<Consultant> row = new TableRow<>();
            return row;
        });

        consultantTableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);

        // Fill with data
        Controller.getFromDB("Consultants");

        return consultantTableView;
    }
}
