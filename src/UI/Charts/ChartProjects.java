package UI.Charts;

import UI.TableViews.TWConsultants;
import UI.TableViews.TWProjects;
import UI.TableViews.TWTasks;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Collections;

public class ChartProjects
{

    public static BarChart<String, Number> barChart;

    public ChartProjects()
    {

    }

    public BarChart<String, Number> getBarChart()
    {
        // Defining the x-axis
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.observableArrayList(Collections.singletonList("Projects")));
        xAxis.setLabel("");

        // Defining the y-axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(TWTasks.tasks.size());
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);
        yAxis.setLabel("tasks");

        // Creating the Bar chart
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Projects overview");

        for (int i = 0; i < TWProjects.projects.size(); i++)
        {
            int value = 0;

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(TWProjects.projects.get(i).getName());

            for (int j = 0; j < TWTasks.tasks.size(); j++)
            {
                if (TWTasks.tasks.get(j).getProjectId() == TWProjects.projects.get(i).getId())
                {
                    value++;
                }
            }

            series.getData().add(new XYChart.Data<>("Projects", value));

            barChart.getData().add(series);
        }
        return barChart;
    }
}
