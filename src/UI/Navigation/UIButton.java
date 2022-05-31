package UI.Navigation;

import Application.MainWindow;
import Domain.Consultant;
import Domain.Office;
import Foundation.DAO.DBController;
import UI.Forms.DialogConsultants;
import UI.Forms.DialogOffices;
import UI.Forms.DialogProjects;
import UI.Forms.DialogTasks;
import UI.Home;
import UI.KeyBindings;
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
    public static Button homeButton, consultantsButton, officesButton, projectsButton, tasksButton,
            reportButton, addButton, editButton, deleteButton, bindingsButton, searchButton, moveUpButton, moveDownButton;

    // Multiple buttons will have to call this
    DialogConsultants dialogConsultants = new DialogConsultants(MainWindow.sender);
    DialogOffices dialogOffices = new DialogOffices(MainWindow.sender);
    DialogProjects dialogProjects = new DialogProjects();
    DialogTasks dialogTasks = new DialogTasks();

    // We'll use this several times
    DBController controller = new DBController();

    public UIButton()
    {
    }

    public Button homeButton()
    {
        Home home = new Home();
        homeButton = new Button("Home");
        homeButton.setMaxSize(150, 30);
        homeButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/home.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(homeButton.heightProperty());

        homeButton.setGraphic(imageView);

        homeButton.setOnAction(event -> {
            MainWindow.root.setCenter(home.getView());
            MainWindow.sender = homeButton.getText();
        });

        return homeButton;
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
            TWConsultants.consultantTableView.requestFocus();
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
            MainWindow.sender = reportButton.getText();
        });

        return reportButton;
    }

    public Button bindingsButton()
    {
        KeyBindings keyBindings = new KeyBindings();

        bindingsButton = new Button("Key Bindings");
        bindingsButton.setMaxSize(150, 30);
        bindingsButton.setMinSize(150, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/keyboard.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(bindingsButton.heightProperty());

        bindingsButton.setGraphic(imageView);

        bindingsButton.setOnAction(event -> {
            MainWindow.root.setCenter(keyBindings.getView());
            MainWindow.sender = bindingsButton.getText();
        });

        return bindingsButton;
    }

    public Button addButton()
    {
        addButton = new Button("add");
        addButton.setMinSize(30, 30);
        addButton.setMaxSize(30, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/add.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(addButton.heightProperty());

        addButton.setGraphic(imageView);

        addButton.setOnAction(event -> {

            MainWindow.action = addButton.getText();

            switch (MainWindow.sender)
            {
                case "Consultants":
                    TWConsultants.selected = new Consultant();
                    dialogConsultants.getDialog();
                    break;
                case "Offices":
                    TWOffices.selected = new Office();
                    dialogOffices.getDialog();
                    break;
                case "Projects":
                    // do something
                case "Tasks":
            }
        });
        return addButton;
    }


    public Button editButton()
    {
        editButton = new Button("edit");
        editButton.setMinSize(30, 30);
        editButton.setMaxSize(30, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/edit.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(editButton.heightProperty());

        editButton.setGraphic(imageView);

        editButton.setOnAction(event -> {

            MainWindow.action = editButton.getText();

            switch (MainWindow.sender)
            {
                case "Consultants":
                    if (TWConsultants.selected != null) // We can't edit something we haven't selected!
                    {
                        dialogConsultants.getDialog();
                    }
                    break;
                case "Offices":
                    if (TWOffices.selected != null) // We can't edit something we haven't selected!
                    {
                        dialogOffices.getDialog();
                    }
                    break;
                case "Projects":
                    // do something
                case "Tasks":
            }
        });
        return editButton;
    }

    public Button deleteButton()
    {
        deleteButton = new Button("delete");
        deleteButton.setMinSize(30, 30);
        deleteButton.setMaxSize(30, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/delete.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(deleteButton.heightProperty());

        deleteButton.setGraphic(imageView);

        deleteButton.setOnAction(event -> {
            switch (MainWindow.sender)
            {
                case "Consultants":
                    if (TWConsultants.selected != null) // We can't edit something we haven't selected!
                    {
                        TWConsultants.consultants.remove(TWConsultants.selected);
                        controller.deleteConsultant(TWConsultants.selected);
                    }
                    break;

                case "Offices":
                    // do something
                case "Projects":
                    // do something
                case "Tasks":
            }
        });

        return deleteButton;
    }

    public Button moveUpButton()
    {
        moveUpButton = new Button("moveUp");
        moveUpButton.setMinSize(30, 30);
        moveUpButton.setMaxSize(30, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/up.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(moveUpButton.heightProperty());

        moveUpButton.setGraphic(imageView);

        moveUpButton.setOnAction(event -> {
            MainWindow.action = moveUpButton().getText();

            switch (MainWindow.sender)
            {
                case "Consultants":
                    if (TWConsultants.selected != null) // We can't edit something we haven't selected!
                    {
                        controller.moveUpConsultant(TWConsultants.selected);
                    }
                    break;
                case "Offices":
                    if (TWOffices.selected != null)
                    {
                        controller.moveUpOffice(TWOffices.selected);
                    }
                    break;
                case "Projects":
                    // do something
                case "Tasks":
            }
        });

        return moveUpButton;
    }

    public Button moveDownButton()
    {
        moveDownButton = new Button("moveDown");
        moveDownButton.setMinSize(30, 30);
        moveDownButton.setMaxSize(30, 30);

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/down.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(moveDownButton.heightProperty());

        moveDownButton.setGraphic(imageView);

        moveDownButton.setOnAction(event -> {
            MainWindow.action = moveDownButton().getText();

            switch (MainWindow.sender)
            {
                case "Consultants":
                    if (TWConsultants.selected != null) // We can't edit something we haven't selected!
                    {
                        controller.moveDownConsultant(TWConsultants.selected);
                    }
                    break;

                case "Offices":
                    if (TWOffices.selected != null)
                    {
                        controller.moveDownOffice(TWOffices.selected);
                    }
                    break;
                case "Projects":
                    // do something
                case "Tasks":
            }
        });

        return moveDownButton;
    }
}
