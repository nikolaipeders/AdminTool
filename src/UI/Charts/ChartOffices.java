package UI.Charts;

import Domain.Office;
import UI.TableViews.TWOffices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class ChartOffices
{
    public static PieChart pieChart;

    public ChartOffices()
    {

    }

    public PieChart getPieChart()
    {
        // Preparing ObservableList object
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Total capacity
        int capacity = 0;

        for (Office office : TWOffices.offices)
        {
            pieChartData.add(new PieChart.Data(office.getName(), office.getCapacity()));
            capacity += office.getCapacity();
        }

        // Creating a Pie chart
        pieChart = new PieChart(pieChartData);

        // Setting the title of the Pie chart
        pieChart.setTitle("Office capacity\nTotal: " + capacity);

        // Setting the direction to arrange the data
        pieChart.setClockwise(true);

        // Setting the length of the label line
        pieChart.setLabelLineLength(50);

        // Setting the labels of the pie chart
        pieChart.setLabelsVisible(true);

        // Setting the start angle of the pie chart
        pieChart.setStartAngle(180);

        return pieChart;
    }
}
