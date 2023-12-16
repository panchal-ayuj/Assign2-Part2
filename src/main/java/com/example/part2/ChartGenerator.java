package com.example.part2;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartGenerator {
    public static JFreeChart createChart(List<ExcelData> data) {
        // Example: Create a bar chart with team names and the count of interviews
        CategoryDataset dataset = createDataset(data);
        JFreeChart chart = ChartFactory.createBarChart(
                "Interviews by Team",
                "Team",
                "Number of Interviews",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Customize chart properties as needed

        return chart;
    }

    private static CategoryDataset createDataset(List<ExcelData> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Example: Count interviews by team
        Map<String, Long> teamInterviewCounts = data.stream().filter(data1->data1.getTeam() != null)
                .collect(Collectors.groupingBy(data1 -> data1.getTeam(), Collectors.counting()));

        teamInterviewCounts.forEach((team, count) -> {
            dataset.addValue(count, "Interviews", team);
        });

        // Add other dataset entries as needed

        return dataset;
    }
}