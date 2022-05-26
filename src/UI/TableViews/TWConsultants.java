package UI.TableViews;

import Application.MainWindow;
import Controller.Controller;
import Domain.Consultant;
import UI.Forms.DialogConsultants;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class TWConsultants
{

    public static TableView<Consultant> consultantTableView;
    public static Consultant selected;

    public TWConsultants()
    {
    }

    public BorderPane getView()
    {
        BorderPane subRoot = new BorderPane();
        subRoot.setPadding(new Insets(0, 20, 10, 20));

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

        consultantTableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);

        // Testing
        for (int i = 0; i < 10; i++) {
            consultantTableView.getItems().add(new Consultant("Astrid Paul", "astrid@mail.com", "Tiger Office",
                    "20:00", "05:00", "15:00", true));
            consultantTableView.getItems().add(new Consultant("Nikolai Pedersen", "nikolai@mail.com", "Polar Office",
                    "20:00", "05:00", "15:00", true));
        }


        // Enable selecting an item
        consultantTableView.setOnMouseClicked(event -> {
            if (consultantTableView.getSelectionModel().getSelectedItem() != null)
            {
                selected = consultantTableView.getSelectionModel().getSelectedItem();
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
        });

        // Fill with data
        Controller.getFromDB("Consultants");

        return consultantTableView;
    }
}
