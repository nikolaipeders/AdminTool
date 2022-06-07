package UI.TableViews;

import Domain.Office;
import UI.Misc.FontSlider;
import UI.Navigation.ActionBar;
import UI.Navigation.UIButton;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class TWOffices
{
    public static TableView<Office> officesTableview;
    public static ObservableList<Office> offices;
    public static Office selected;
    public static BorderPane subRoot;

    public TWOffices()
    {
    }

    /*
    Returns a BorderPane with a tableview at the center and a custom menubar at the bottom.
     */
    public BorderPane getView()
    {
        subRoot = new BorderPane();
        subRoot.setPadding(new Insets(10, 20, 10, 20));

        subRoot.setCenter(tableViewOffices());

        ActionBar bb = new ActionBar(UIButton.officesButton.getText());
        subRoot.setBottom(bb.getBottomBar());

        return subRoot;
    }

    /**
     * @return TableView for the Office domain.
     */
    public TableView<Office> tableViewOffices()
    {
        officesTableview = new TableView<>();
        officesTableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Office, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Office, String> column2 = new TableColumn<>("Location");
        column2.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Office, String> column3 = new TableColumn<>("Capacity");
        column3.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        TableColumn<Office, String> column4 = new TableColumn<>("Consultants Connected");
        column4.setCellValueFactory(new PropertyValueFactory<>("consultantsConnected"));

        officesTableview.getColumns().addAll(column1, column2, column3, column4);

        TWOffices.officesTableview.styleProperty().bind(Bindings.concat("-fx-font-size: ", FontSlider.sliderFont.valueProperty().asString()));

        // Enable selecting an item
        officesTableview.setOnMouseClicked(event ->
        {
            if (officesTableview.getSelectionModel().getSelectedItem() != null)
            {
                System.out.println(officesTableview.getSelectionModel().getSelectedIndex());
                selected = officesTableview.getSelectionModel().getSelectedItem();
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
        officesTableview.setOnKeyPressed(e ->
        {
            // Enable opening an item with ENTER key
            if (e.getCode() == KeyCode.ENTER)
            {
                selected = officesTableview.getSelectionModel().getSelectedItem();
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
            if (e.getCode() == KeyCode.ESCAPE)
            {
                TWOffices.officesTableview.getSelectionModel().clearSelection();
            }
        });
        return officesTableview;
    }
}
