package UI.Charts;

import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Collections;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class ChartConsultants
{
    public static BarChart<String, Number> barChart;

    public ChartConsultants()
    {
    }

    /**
     * Returns a BarChart showing how consultants a divided in the offices.
     */
    public BarChart<String, Number> getBarChart()
    {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.observableArrayList(Collections.singletonList("Offices")));
        xAxis.setLabel("");

        /* Defining the y-axis. Apparently, it's not possible to only show integers with AutoRanging, so we
         set an UpperBound of the total amount of consultants as this is the maximum value a single office can contain.
        */
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(TWConsultants.consultants.size());
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);
        yAxis.setLabel("consultants");

        // Creating the Bar chart
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Distribution of consultants");

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
            series.getData().add(new XYChart.Data<>("Offices", value));

            barChart.getData().add(series);
        }
        return barChart;
    }
}
