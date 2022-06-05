package UI.Charts;

import Domain.Office;
import UI.TableViews.TWOffices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class ChartOffices
{
    public static PieChart pieChart;

    public ChartOffices()
    {
    }

    /**
     * Returns a PieChart showing how many empty spots there are in all offices.
     */
    public PieChart getPieChart()
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Office office : TWOffices.offices)
        {
            pieChartData.add(new PieChart.Data(office.getName() + " " + office.getConsultantsConnected() + "/" +
                    office.getCapacity(), office.getCapacity()- office.getConsultantsConnected()));
        }

        pieChart = new PieChart(pieChartData);

        pieChart.setTitle("Offices");

        pieChart.setClockwise(true);

        pieChart.setLabelLineLength(50);

        pieChart.setLabelsVisible(true);

        pieChart.setStartAngle(180);

        return pieChart;
    }
}
