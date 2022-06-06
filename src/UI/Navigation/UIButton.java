package UI.Navigation;

import Application.MainWindow;
import Domain.Consultant;
import Domain.Office;
import Domain.Project;
import Domain.Task;
import Foundation.DAO.DBController;
import UI.Charts.ChartConsultants;
import UI.Charts.ChartOffices;
import UI.Charts.ChartProjects;
import UI.Charts.ChartTasks;
import UI.Forms.DialogConsultants;
import UI.Forms.DialogOffices;
import UI.Forms.DialogProjects;
import UI.Forms.DialogTasks;
import UI.Misc.KeyBindings;
import UI.Misc.PopUp;
import UI.Report;
import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import UI.TableViews.TWProjects;
import UI.TableViews.TWTasks;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class UIButton
{
    public static Button consultantsButton, officesButton, projectsButton, tasksButton, reportButton,
            addButton, editButton, deleteButton, bindingsButton, moveUpButton, moveDownButton, chartButton;

    // Multiple buttons will have to call this
    DialogConsultants dialogConsultants = new DialogConsultants(MainWindow.sender);
    DialogOffices dialogOffices = new DialogOffices(MainWindow.sender);
    DialogProjects dialogProjects = new DialogProjects(MainWindow.sender);
    DialogTasks dialogTasks = new DialogTasks(MainWindow.sender);

    // We'll use this several times
    DBController controller = new DBController();

    public UIButton()
    {
    }

    /**
     * @return A button for entering the Consultants menu.
     */
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
            MainWindow.sender.setStyle(null);
            MainWindow.sender = consultantsButton;
            TWConsultants.consultantTableView.requestFocus();

            // Keep visual track of selected menu button
            MainWindow.sender.setStyle("-fx-background-color: #FFFFFF; -fx-background-insets: -10 0 -10 0");
        });

        return consultantsButton;
    }

    /**
     * @return A button for entering the Offices menu.
     */
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
            MainWindow.sender.setStyle(null);
            MainWindow.sender = officesButton;
            TWOffices.tableViewOffices.requestFocus();

            // Keep visual track of selected menu button
            MainWindow.sender.setStyle("-fx-background-color: #FFFFFF; -fx-background-insets: -10 0 -10 0;");
        });

        return officesButton;
    }

    /**
     * @return A button for entering the Projects menu.
     */
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
            MainWindow.sender.setStyle(null);
            MainWindow.sender = projectsButton;
            TWProjects.projectTableView.requestFocus();

            // Keep visual track of selected menu button
            MainWindow.sender.setStyle("-fx-background-color: #FFFFFF; -fx-background-insets: -10 0 -10 0");
        });

        return projectsButton;
    }

    /**
     * @return A button for entering the Tasks menu.
     */
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
            MainWindow.sender.setStyle(null);
            MainWindow.sender = tasksButton;
            TWTasks.taskTableView.requestFocus();

            // Keep visual track of selected menu button
            MainWindow.sender.setStyle("-fx-background-color: #FFFFFF; -fx-background-insets: -10 0 -10 0");
        });

        return tasksButton;
    }

    /**
     * @return A button for entering the Consultants menu.
     */
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
            MainWindow.sender.setStyle(null);
            MainWindow.sender = bindingsButton;

            // Keep visual track of selected menu button
            MainWindow.sender.setStyle("-fx-background-color: #FFFFFF; -fx-background-insets: -10 0 -10 0");

        });

        return bindingsButton;
    }

    /**
     * @return A button for adding an item to a tableview.
     */
    public Button addButton()
    {
        addButton = new Button("add");
        addButton.setMinSize(30, 30);
        addButton.setMaxSize(30, 30);
        addButton.setTooltip(new Tooltip("Add new item"));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/add.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(addButton.heightProperty());

        addButton.setGraphic(imageView);

        addButton.setOnAction(event -> {

            MainWindow.action = addButton.getText();

            switch (MainWindow.sender.getText())
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
                    TWProjects.selected = new Project();
                    dialogProjects.getDialog();
                    break;
                case "Tasks":
                    TWTasks.selected = new Task();
                    dialogTasks.getDialog();
                    break;
            }
        });
        return addButton;
    }

    /**
     * @return A button for editing an item to a tableview.
     */
    public Button editButton()
    {
        editButton = new Button("edit");
        editButton.setMinSize(30, 30);
        editButton.setMaxSize(30, 30);
        editButton.setTooltip(new Tooltip("Edit selected item"));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/edit.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(editButton.heightProperty());

        editButton.setGraphic(imageView);

        editButton.setOnAction(event -> {

            MainWindow.action = editButton.getText();

            switch (MainWindow.sender.getText())
            {
                case "Consultants":
                    if (TWConsultants.selected != null)
                    {
                        dialogConsultants.getDialog();
                    }
                    break;
                case "Offices":
                    if (TWOffices.selected != null)
                    {
                        dialogOffices.getDialog();
                    }
                    break;
                case "Projects":
                    if (TWProjects.selected != null)
                    {
                        dialogProjects.getDialog();
                    }
                    break;
                case "Tasks":
                    if (TWTasks.selected != null)
                    {
                        dialogTasks.getDialog();
                    }
            }
        });
        return editButton;
    }

    /**
     * @return A button for deleting an item to a tableview.
     */
    public Button deleteButton()
    {
        deleteButton = new Button("delete");
        deleteButton.setMinSize(30, 30);
        deleteButton.setMaxSize(30, 30);
        deleteButton.setTooltip(new Tooltip("Delete selected item"));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/delete.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(deleteButton.heightProperty());

        deleteButton.setGraphic(imageView);

        deleteButton.setOnAction(event ->
        {
            ContextMenu confirmMenu = new ContextMenu();
            MenuItem confirm = new MenuItem("Confirm");
            MenuItem cancel = new MenuItem("Cancel");
            SeparatorMenuItem separator = new SeparatorMenuItem();
            confirmMenu.getItems().addAll(cancel, separator, confirm);

            confirmMenu.show(deleteButton, Side.BOTTOM, 0, 0);

            cancel.setOnAction(cancelEvent -> confirmMenu.hide());
            confirm.setOnAction(confirmEvent ->
            {
                switch (MainWindow.sender.getText())
                {
                    case "Consultants":
                        boolean isDeletable = true;

                        TWConsultants.selected = TWConsultants.consultantTableView.getSelectionModel().getSelectedItem();

                        // Check if any Tasks are bound to this Consultant
                        for (int i = 0; i < TWTasks.tasks.size(); i++)
                        {
                            if (TWTasks.tasks.get(i).getConsultantMail().equalsIgnoreCase(TWConsultants.selected.getMail()))
                            {
                                isDeletable = false;
                                PopUp popUp = new PopUp();
                                popUp.popText("Can't delete consultant with tasks!", MainWindow.stage, 50, 300);
                                break;
                            }
                        }
                        if (isDeletable)
                        {
                            controller.deleteConsultant(TWConsultants.selected);
                            TWConsultants.consultants.remove(TWConsultants.selected);
                        }
                        break;

                    case "Offices":
                        TWOffices.selected = TWOffices.tableViewOffices.getSelectionModel().getSelectedItem();
                        controller.deleteOffice(TWOffices.selected);
                        TWOffices.offices.remove(TWOffices.selected);
                        break;

                    case "Projects":
                        TWProjects.selected = TWProjects.projectTableView.getSelectionModel().getSelectedItem();
                        controller.deleteProject(TWProjects.selected);
                        TWProjects.projects.remove(TWProjects.selected);
                        break;

                    case "Tasks":
                        TWTasks.selected = TWTasks.taskTableView.getSelectionModel().getSelectedItem();
                        controller.deleteTask(TWTasks.selected);
                        TWTasks.tasks.remove(TWTasks.selected);
                        break;
                }
            });
        });
        return deleteButton;
    }

    /**
     * @return A button for moving an item up in a tableview.
     */
    public Button moveUpButton()
    {
        moveUpButton = new Button("moveUp");
        moveUpButton.setMinSize(30, 30);
        moveUpButton.setMaxSize(30, 30);
        moveUpButton.setTooltip(new Tooltip("Move selected item up"));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/up.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(moveUpButton.heightProperty());

        moveUpButton.setGraphic(imageView);

        moveUpButton.setOnAction(event -> {
            MainWindow.action = moveUpButton().getText();

            switch (MainWindow.sender.getText())
            {
                case "Consultants":
                    if (TWConsultants.selected != null)
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
                    if (TWProjects.selected != null)
                    {
                        controller.moveUpProject(TWProjects.selected);
                    }
                    break;
                case "Tasks":
                    if (TWTasks.selected != null)
                    {
                        controller.moveUpTask(TWTasks.selected);
                    }
                    break;
            }
        });
        return moveUpButton;
    }

    /**
     * @return A button for moving an item down in a tableview.
     */
    public Button moveDownButton()
    {
        moveDownButton = new Button("moveDown");
        moveDownButton.setMinSize(30, 30);
        moveDownButton.setMaxSize(30, 30);
        moveDownButton.setTooltip(new Tooltip("Move selected item down"));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/down.png")).toExternalForm());

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(moveDownButton.heightProperty());

        moveDownButton.setGraphic(imageView);

        moveDownButton.setOnAction(event -> {
            MainWindow.action = moveDownButton().getText();

            switch (MainWindow.sender.getText())
            {
                case "Consultants":
                    if (TWConsultants.selected != null)
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
                    if (TWProjects.selected != null)
                    {
                        controller.moveDownProject(TWProjects.selected);
                    }
                    break;
                case "Tasks":
                    if (TWTasks.selected != null)
                    {
                        controller.moveDownTask(TWTasks.selected);
                    }
                    break;
            }
        });
        return moveDownButton;
    }

    /**
     * @return A button for showing a chart based on content in a tableview.
     */
    public Button chartButton()
    {
        chartButton = new Button("chart");
        chartButton.setMinSize(30, 30);
        chartButton.setMaxSize(30, 30);
        chartButton.setTooltip(new Tooltip("Show statistics"));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/bar.png")).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(chartButton.heightProperty());

        // Switch icon to a cancel icon to help the user understand that clicking it again hides the chart.
        ImageView alternativeView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/cancel.png")).toExternalForm());
        alternativeView.setPreserveRatio(true);
        alternativeView.fitHeightProperty().bind(chartButton.heightProperty());

        chartButton.setGraphic(imageView);

        chartButton.setOnAction(event -> {
            MainWindow.action = chartButton.getText();

            if (chartButton.getGraphic() == imageView)
            {
                chartButton.setGraphic(alternativeView);
            }
            else
            {
                chartButton.setGraphic(imageView);
            }

            switch (MainWindow.sender.getText())
            {
                case "Consultants":
                    ChartConsultants chartConsultants = new ChartConsultants();
                    if (TWConsultants.subRoot.getCenter() == ChartConsultants.barChart)
                    {
                        TWConsultants.subRoot.setCenter(TWConsultants.consultantTableView);
                    }
                    else
                    {
                        TWConsultants.subRoot.setCenter(chartConsultants.getBarChart());
                    }
                    break;

                case "Offices":
                    ChartOffices chartOffices = new ChartOffices();
                    if (TWOffices.subRoot.getCenter() == ChartOffices.pieChart)
                    {
                        TWOffices.subRoot.setCenter(TWOffices.tableViewOffices);
                    }
                    else
                    {
                        TWOffices.subRoot.setCenter(chartOffices.getPieChart());
                    }
                    break;

                case "Projects":
                    ChartProjects chartProjects = new ChartProjects();
                    if (TWProjects.subRoot.getCenter() == ChartProjects.barChart)
                    {
                        TWProjects.subRoot.setCenter(TWProjects.projectTableView);
                    }
                    else
                    {
                        TWProjects.subRoot.setCenter(chartProjects.getBarChart());
                    }
                    break;

                case "Tasks":
                    ChartTasks chartTasks = new ChartTasks();
                    if (TWTasks.subRoot.getCenter() == ChartTasks.barChart)
                    {
                        TWTasks.subRoot.setCenter(TWTasks.taskTableView);
                    }
                    else
                    {
                        TWTasks.subRoot.setCenter(chartTasks.getBarChart());
                    }
                    break;
            }
        });
        return chartButton;
    }

    /**
     * @return A button for generating a report based on content in a tableview.
     */
    public Button reportButton()
    {
        Report report = new Report();

        reportButton = new Button("report");
        reportButton.setMinSize(30, 30);
        reportButton.setMaxSize(30, 30);
        reportButton.setTooltip(new Tooltip("Generate report"));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/resources/report.png")).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(reportButton.heightProperty());

        reportButton.setGraphic(imageView);

        reportButton.setOnAction(event -> {
            MainWindow.action = reportButton.getText();

            report.saveReport(MainWindow.sender.getText());

            // TODO POP UP WITH "REPORT SAVED"
            PopUp confirmationPop = new PopUp();
            confirmationPop.popText("Report saved!", MainWindow.stage, 50, 200);
        });
        return reportButton;
    }
}
