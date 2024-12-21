package mypackage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dashboardnew extends JFrame {
    private JPanel piepanel;
    private JPanel deadlinesPanel;
    private JButton backButton;  // Declare the Back button

    // Constructor to initialize the frame and components
    public Dashboardnew() {
        // Initialize the frame
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Using absolute positioning

        // Set background color to light purple
        getContentPane().setBackground(new Color(202, 172, 205)); // Light purple

        // Centered heading for the report
        JLabel reportHeading = new JLabel("My Progress Report", JLabel.CENTER);
        reportHeading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        reportHeading.setForeground(new Color(250, 244, 235)); // Light color for the heading
        reportHeading.setBounds(150, 30, 500, 40);
        add(reportHeading);

        // Initialize the pie chart panel
        piepanel = new JPanel();
        piepanel.setBackground(new Color(255, 249, 249)); // Set background color of the pie panel
        piepanel.setBounds(60, 160, 330, 280);  // Set position and size for the pie chart panel
        add(piepanel);

        // Initialize the deadlines panel on the right side
        deadlinesPanel = new JPanel();
        deadlinesPanel.setBackground(new Color(255, 249, 249)); // Set background color of the deadlines panel
        deadlinesPanel.setBounds(420, 160, 330, 280); // Set position and size for the deadlines panel
        deadlinesPanel.setLayout(null); // Absolute layout for custom positioning
        add(deadlinesPanel);

        // Add the Deadlines heading
        JLabel deadlinesHeading = new JLabel("Deadlines");
        deadlinesHeading.setFont(new Font("Arial", Font.BOLD, 20));
        deadlinesHeading.setForeground(new Color(104, 69, 117)); // Dark purple color
        deadlinesHeading.setBounds(120, 10, 150, 30);
        deadlinesPanel.add(deadlinesHeading);

        // Fetch and display the closest 5 tasks
        addTasksToDeadlines();

        // Call the method to add the pie chart to the panel
        addPieChart();

        // Initialize the Back button
//        backButton = new JButton("Back");
//        backButton.setBounds(330, 500, 120, 40); // Position the button
//        backButton.setBackground(new Color(104, 60, 117)); // Set button color
//        backButton.setForeground(Color.WHITE); // Set text color to white
//        backButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font
//        backButton.setFocusPainted(false); // Remove focus ring
//        backButton.addActionListener(e -> dispose()); // Dispose the window when clicked
//        add(backButton);
backButton = new JButton("Back");
backButton.setBounds(330, 500, 120, 40); // Position the button
backButton.setBackground(new Color(104, 60, 117)); // Set button color
backButton.setForeground(Color.WHITE); // Set text color to white
backButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font
backButton.setFocusPainted(false); // Remove focus ring
backButton.addActionListener(e -> {
    // Create the main frame and set its size, position, and visibility
    main mainFrame = new main();
    mainFrame.setSize(720, 620); // Set the size of the main frame
    mainFrame.setLocationRelativeTo(null); // Center the frame on the screen
    mainFrame.setVisible(true); // Make the frame visible
    
    // Dispose of the current (Dashboardnew) frame
    dispose();
});
add(backButton);

        // Set the size of the frame
        setSize(790, 790);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Method to fetch tasks with the closest deadline and display them
    private void addTasksToDeadlines() {
    // Get today's date in the correct format (YYYY-MM-DD) to compare with task deadlines
    java.sql.Date today = new java.sql.Date(System.currentTimeMillis());

    // Query to get the tasks with the closest due_date and "pending" status
    // Filtering by status = 'pending' and deadlines within a close range of today's date (next 7 days)
    String query = "SELECT title, due_date FROM tasks " +
                   "WHERE status = 'Pending' AND due_date >= ? AND due_date <= ? " +
                   "ORDER BY due_date ASC LIMIT 3"; // Limit to closest 5 tasks

    // Define the range of due dates (for example, within the next 7 days)
    java.sql.Date sevenDaysFromNow = new java.sql.Date(System.currentTimeMillis() + (7L * 24 * 60 * 60 * 1000)); // 7 days from now

    try (Connection conn = new DatabaseConnection().getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the parameters for the query
        stmt.setDate(1, today); // Today's date
        stmt.setDate(2, sevenDaysFromNow); // 7 days from today

        // Execute the query
        try (ResultSet rs = stmt.executeQuery()) {

            int yPosition = 50; // Initial y position for the tasks
            int taskNumber = 1; // Initialize task number

            while (rs.next()) {
                String taskTitle = rs.getString("title");
                String dueDate = rs.getString("due_date");

                // Create a label for each task with numbering and the title in bold
                JLabel taskLabel = new JLabel("<html><b>" + taskNumber + ". " + taskTitle + "</b><br>Deadline: " + dueDate + "</html>");
                taskLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                taskLabel.setForeground(new Color(104, 69, 117)); // Dark purple color
                taskLabel.setBounds(20, yPosition, 290, 40); // Adjust position and size

                deadlinesPanel.add(taskLabel);
                yPosition += 50; // Move down for the next task
                taskNumber++; // Increment task number
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions
    }
}

    // Method to add the pie chart to the piepanel
    private void addPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Database query to count tasks by status
        String query = "SELECT status, COUNT(*) AS count FROM tasks GROUP BY status";

        // Database connection and query execution
        try (Connection conn = new DatabaseConnection().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Loop through the result set and populate the dataset
            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("count");

                // Add the status and its count to the dataset
                dataset.setValue(status + " (" + count + ")", count);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }

        // Create the pie chart using the dataset
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Task Status Distribution",  // Chart title
                dataset,                    // Dataset
                true,                       // Include legend
                true,                       // Include tooltips
                false                       // Exclude URLs
        );

        // Get the plot and set custom colors for the pie chart sections
        PiePlot plot = (PiePlot) pieChart.getPlot();
        
        // Get the dataset and assign colors dynamically
        int sectionCount = dataset.getKeys().size();
        Color[] colors = new Color[] {
            new Color(104, 69, 117), // Dark purple
            new Color(250, 244, 235)  // Light purple
        };

        int colorIndex = 0;

        // Assign colors to the sections dynamically
        for (int i = 0; i < sectionCount; i++) {
            String key = (String) dataset.getKeys().get(i);
            plot.setSectionPaint(key, colors[colorIndex % colors.length]);
            colorIndex++;
        }

        // Set the label generator to include the count in the labels
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));

        // Create a ChartPanel and add it to the piepanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setBounds(0, 0, piepanel.getWidth(), piepanel.getHeight());
        piepanel.setLayout(null); // Ensure absolute positioning
        piepanel.add(chartPanel);
        piepanel.validate(); // Refresh the panel
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the Dashboardnew frame
                new Dashboardnew(); // Automatically calls the constructor to create the frame
            }
        });
    }
}
