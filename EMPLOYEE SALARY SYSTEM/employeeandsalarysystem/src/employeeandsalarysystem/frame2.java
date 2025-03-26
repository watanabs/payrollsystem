package employeeandsalarysystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frame2 extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame2 frame = new frame2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public frame2() {
    	setResizable(false);
        setTitle("Employee and Salary System");
        setIconImage(Toolkit.getDefaultToolkit().getImage(frame2.class.getResource("/employeeandsalarysystem_resources/ESS.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1225, 716);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("EMPLOYEE INFORMATION");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblNewLabel.setBounds(270, 71, 669, 61);
        contentPane.add(lblNewLabel);

        // Create a DefaultTableModel to hold the data
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Employee Number");
        model.addColumn("Last Name");
        model.addColumn("First Name");
        model.addColumn("Birthday");

        // Read data from CSV and populate the table model
        String line = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader("src/employeeandsalarysystemdatabase/employeedetails - show.csv"))) {
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
        scrollPane.setBounds(100, 185, 1000, 248);
        contentPane.add(scrollPane);

        JButton btnNewButton = new JButton("Employee Weekly Hours & Salary\r\n");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame3weeklyhours jf2 = new frame3weeklyhours();
				jf2.setVisible(true); // Use setVisible() instead of show()
				
				dispose(); // close current frame
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton.setBounds(458, 485, 307, 42);
        contentPane.add(btnNewButton);

        JButton btnEmployeeSalaryCalculation = new JButton("Employee Net Weekly Salary");
        btnEmployeeSalaryCalculation.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame4netsalary jf2 = new frame4netsalary();
				jf2.setVisible(true); // Use setVisible() instead of show()
        	}
        });
        btnEmployeeSalaryCalculation.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnEmployeeSalaryCalculation.setBounds(458, 537, 307, 42);
        contentPane.add(btnEmployeeSalaryCalculation);
    }
}

