package UI.Navigation;

import Application.MainWindow;
import UI.Forms.AddDialog;
import UI.Overview;
import UI.Report;
import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import UI.TableViews.TWProjects;
import UI.TableViews.TWTasks;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class UIButton
{
    public static Button overViewButton, consultantsButton, officesButton, projectsButton, tasksButton, reportButton, addButton, editButton, deleteButton;

    public UIButton()
    {
    }

    public Button overviewButton()
    {
        Overview overview = new Overview();
        overViewButton = new Button("Overview");
        overViewButton.setMaxSize(150, 30);
        overViewButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/home.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(overViewButton.heightProperty());

        overViewButton.setGraphic(imageView);

        overViewButton.setOnAction(event -> {
            MainWindow.root.setCenter(overview.getView());
            MainWindow.sender = overViewButton.getText();
        });

        return overViewButton;
    }

    public Button consultantsButton()
    {
        TWConsultants listViewConsultant = new TWConsultants();
        consultantsButton = new Button("Consultants");
        consultantsButton.setMaxSize(150, 30);
        consultantsButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/cons.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(consultantsButton.heightProperty());

        consultantsButton.setGraphic(imageView);

        consultantsButton.setOnAction(event -> {
            MainWindow.root.setCenter(listViewConsultant.getView());
            MainWindow.sender = consultantsButton.getText();
        });

        return consultantsButton;
    }

    public Button officesButton()
    {
        TWOffices listViewOffices = new TWOffices();
        officesButton = new Button("Offices");
        officesButton.setMaxSize(150, 30);
        officesButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/office.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(officesButton.heightProperty());

        officesButton.setGraphic(imageView);

        officesButton.setOnAction(event -> {
            MainWindow.root.setCenter(listViewOffices.getView());
            MainWindow.sender = officesButton.getText();
        });

        return officesButton;
    }

    public Button projectsButton()
    {
        TWProjects listViewProjects = new TWProjects();
        projectsButton = new Button("Projects");
        projectsButton.setMaxSize(150, 30);
        projectsButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/project.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(projectsButton.heightProperty());

        projectsButton.setGraphic(imageView);

        projectsButton.setOnAction(event -> {
            MainWindow.root.setCenter(listViewProjects.getView());
            MainWindow.sender = projectsButton.getText();
        });

        return projectsButton;
    }

    public Button tasksButton()
    {
        TWTasks listViewTasks = new TWTasks();
        tasksButton = new Button("Tasks");
        tasksButton.setMaxSize(150, 30);
        tasksButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/task.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(tasksButton.heightProperty());

        tasksButton.setGraphic(imageView);

        tasksButton.setOnAction(event -> {
            MainWindow.root.setCenter(listViewTasks.getView());
            MainWindow.sender = tasksButton.getText();
        });

        return tasksButton;
    }

    public Button reportButton()
    {
        Report report = new Report();

        reportButton = new Button("Report");
        reportButton.setMaxSize(150, 30);
        reportButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/report.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(reportButton.heightProperty());

        reportButton.setGraphic(imageView);

        reportButton.setOnAction(event -> {
            MainWindow.root.setCenter(report.getView());
            MainWindow.sender = reportButton.getText();
        });

        return reportButton;
    }

    public Button addButton()
    {
        addButton = new Button();
        addButton.setMinSize(50, 40);
        addButton.setMaxSize(50, 40);
        addButton.setStyle("-fx-alignment: center");

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/add.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(addButton.heightProperty());

        addButton.setGraphic(imageView);

        AddDialog addForm = new AddDialog(MainWindow.sender);
        addButton.setOnAction(event -> addForm.getDialog());

        return addButton;
    }


    public Button editButton()
    {
        editButton = new Button();
        editButton.setMinSize(50, 40);
        editButton.setMaxSize(50, 40);
        editButton.setStyle("-fx-alignment: center");

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/edit.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(editButton.heightProperty());

        editButton.setGraphic(imageView);

        // editButton.setOnAction(event -> editFunction(sender));

        return editButton;
    }

    public Button deleteButton()
    {
        deleteButton = new Button();
        deleteButton.setMinSize(50, 40);
        deleteButton.setMaxSize(50, 40);
        deleteButton.setStyle("-fx-alignment: center");

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/delete.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(deleteButton.heightProperty());

        deleteButton.setGraphic(imageView);

        // deleteButton.setOnAction(event -> deleteFunction(sender));

        return deleteButton;
    }

}
