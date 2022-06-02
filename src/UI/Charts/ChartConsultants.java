package UI.Charts;

import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import UI.TableViews.TWTasks;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Collections;

public class ChartConsultants
{

    public static BarChart<String, Number> barChart;

    public ChartConsultants()
    {

    }

    public BarChart<String, Number> getBarChart()
    {
        // Defining the x-axis
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.observableArrayList(Collections.singletonList("Number of consultants")));
        xAxis.setLabel("offices");

        // Defining the y-axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);
        yAxis.setLabel("consultants");

        // Creating the Bar chart
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Consultants Placements");

        for (int i = 0; i < TWOffices.offices.size(); i++)
        {
            int value = 0;

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(TWOffices.offices.get(i).getName());

            for (int j = 0; j < TWConsultants.consultants.size(); j++)
            {
                if (TWOffices.offices.get(i).getName().equalsIgnoreCase(TWConsultants.consultants.get(j).getOffice()))
                {
                    value++;
                }
            }

            series.getData().add(new XYChart.Data<>("Number of consultants", value));

            barChart.getData().add(series);
        }
        return barChart;
    }
}
