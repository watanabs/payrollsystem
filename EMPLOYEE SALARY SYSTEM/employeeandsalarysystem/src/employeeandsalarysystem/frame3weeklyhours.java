package employeeandsalarysystem;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class frame3weeklyhours extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtBasicSalary;
    private JTextField txtHourlyRate;
    private JLabel lblBasicSalary;
    private JLabel lblHourlyRate;
    private JButton btnCalculate;
    private JLabel lblResult; // New JLabel for displaying the result
    private JLabel lblSalary;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame3weeklyhours frame = new frame3weeklyhours();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public frame3weeklyhours() {
        setResizable(false);
        setTitle("Employee and Salary System");
        setIconImage(Toolkit.getDefaultToolkit().getImage(frame3weeklyhours.class.getResource("/employeeandsalarysystem_resources/ESS.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1170, 847);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblEmployeeWeeklyHours = new JLabel("EMPLOYEE WEEKLY HOURS & SALARY");
        lblEmployeeWeeklyHours.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmployeeWeeklyHours.setBounds(10, 67, 1136, 61);
        lblEmployeeWeeklyHours.setFont(new Font("Tahoma", Font.BOLD, 50));
        contentPane.add(lblEmployeeWeeklyHours);
        
        // Create a DefaultTableModel to hold the data
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Employee Number");
        model.addColumn("Last Name");
        model.addColumn("First Name");
        model.addColumn("Basic Salary");
        model.addColumn("Hourly Rate");

        // Read data from CSV and populate the table model
        String line = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader("src/employeeandsalarysystemdatabase/employeeWEEKLYHOURS.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the JTable using the populated table model
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 184, 1000, 248);
        contentPane.add(scrollPane);
        
        txtBasicSalary = new JTextField();
        txtBasicSalary.setForeground(new Color(0, 0, 0));
        txtBasicSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtBasicSalary.setBounds(422, 492, 260, 27);
        contentPane.add(txtBasicSalary);
        txtBasicSalary.setColumns(10);
        
        txtHourlyRate = new JTextField();
        txtHourlyRate.setForeground(new Color(0, 0, 0));
        txtHourlyRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtHourlyRate.setColumns(10);
        txtHourlyRate.setBounds(422, 529, 260, 27);
        contentPane.add(txtHourlyRate);
        
        lblBasicSalary = new JLabel("Basic Salary");
        lblBasicSalary.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblBasicSalary.setBounds(284, 492, 128, 27);
        contentPane.add(lblBasicSalary);
        
        lblHourlyRate = new JLabel("Hourly Rate");
        lblHourlyRate.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblHourlyRate.setBounds(284, 529, 128, 27);
        contentPane.add(lblHourlyRate);
        
        btnCalculate = new JButton("Calculate");
        btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnCalculate.setBounds(478, 572, 154, 35);
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
                calculateSalary();
            }
        });
        contentPane.add(btnCalculate);
        
        // Create the JLabels for displaying the result
        lblResult = new JLabel();
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblResult.setForeground(new Color(0, 0, 255));
        lblResult.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblResult.setBounds(272, 674, 575, 27);
        contentPane.add(lblResult);
        
        lblSalary = new JLabel(); // corrected the variable name
        lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalary.setForeground(Color.BLUE);
        lblSalary.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblSalary.setBounds(272, 718, 575, 27);
        contentPane.add(lblSalary);
        
        JButton btnMainMenu = new JButton("Main Menu");
        btnMainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame2 jf2 = new frame2();
				jf2.setVisible(true); // Use setVisible() instead of show()
				
				dispose(); // close current frame
        	}
        });
        btnMainMenu.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnMainMenu.setBounds(916, 442, 154, 35);
        contentPane.add(btnMainMenu);
    }
    
    private void calculateTotal() {
        try {
            // Retrieve the values entered in the text fields
            double basicSalary = Double.parseDouble(txtBasicSalary.getText());
            double hourlyRate = Double.parseDouble(txtHourlyRate.getText());

            // Calculate the result of dividing the basic salary by the hourly rate
            double result = basicSalary / hourlyRate;

            // Display the calculated result
            lblResult.setText("Weekly Hours: " + result);
        } catch (NumberFormatException ex) {
            // Handle the case where the user entered invalid input
            System.out.println("Invalid input. Please enter valid numeric values for Basic Salary and Hourly Rate.");
        }
    }

    private void calculateSalary() {
        try {
            double basicSalary = Double.parseDouble(txtBasicSalary.getText());
            double hourlyRate = Double.parseDouble(txtHourlyRate.getText());
            double result = basicSalary / hourlyRate;

            // Calculate the weekly salary by multiplying the hourly rate by the weekly hours
            double weeklySalary = result * hourlyRate;

            // Display the calculated result
            lblSalary.setText("Weekly Salary: Php " + weeklySalary);
        } catch (NumberFormatException ex) {
            // Handle the case where the user entered invalid input
            System.out.println("Invalid input. Please enter valid numeric values for Basic Salary and Hourly Rate.");
        }
    }
}


