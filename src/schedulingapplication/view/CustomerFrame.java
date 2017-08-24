/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import schedulingapplication.dao.CustomerDAO;
import schedulingapplication.dao.TestConnection;
import schedulingapplication.model.Appointment;
import schedulingapplication.model.Customer;
import schedulingapplication.model.Schedule;
import static schedulingapplication.view.EditAppointmentPanel.buildTableModel;
import static schedulingapplication.view.EditCustomerPanel.buildTableModel;

/**
 *
 */
public class CustomerFrame extends JFrame {

    private CustomerPanel panel;
    
    private final String[] COL_NAMES = {"Consultant", "Customer", "City", "Country", "Title",
        "Description", "Start", "End"};

    public CustomerFrame(String title) {
        super(title);

        addMenuReports();
        add(panel = new CustomerPanel());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void addMenuReports() {
        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenu jmReport = new JMenu("Report");
        JMenu jmView = new JMenu("View");
        JMenu jmEdit = new JMenu("Edit");
        JMenuItem jmiEditCustomer = new JMenuItem("Edit Customers");
        jmiEditCustomer.addActionListener(e -> editCustomer());
        JMenuItem jmiEditAppointment = new JMenuItem("Edit Appointments");
        jmiEditAppointment.addActionListener(e -> editAppointment());
        JMenuItem jmiNumTypesByMonths = new JMenuItem("Number Types By Months");
        jmiNumTypesByMonths.addActionListener(e -> reportTypesByMonth());
        JMenuItem jmiActiveCustomers = new JMenuItem("Active Customers");
        jmiActiveCustomers.addActionListener(e -> activeCustomerReport());
        JMenuItem jmiSchedule = new JMenuItem("Schedule");
        jmiSchedule.addActionListener(e -> reportSchedule());
        JMenuItem jmiMonthlyView = new JMenuItem("Monthly View");
        jmiMonthlyView.addActionListener(e -> monthlyView());
        JMenuItem jmiWeeklyView = new JMenuItem("Weekly View");
        jmiWeeklyView.addActionListener(e -> weeklyView());
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmiExit.addActionListener(e -> System.exit(0));
        jmReport.add(jmiNumTypesByMonths);
        jmReport.add(jmiSchedule);
        jmReport.add(jmiActiveCustomers);
        jmView.add(jmiMonthlyView);
        jmView.add(jmiWeeklyView);
        jmEdit.add(jmiEditCustomer);
        jmEdit.add(jmiEditAppointment);
        jmFile.add(jmiExit);
        jmb.add(jmFile);
        jmb.add(jmReport);
        jmb.add(jmView);
        jmb.add(jmEdit);

        setJMenuBar(jmb);
    }

    private void editCustomer() {
        SwingUtilities.invokeLater(() -> {
            new EditCustomerPanel("Edit Customers");
        });

    }

    private void editAppointment() {
        SwingUtilities.invokeLater(() -> {
            new EditAppointmentPanel("Edit Appointments");
        });

    }

    private void monthlyView() {
        SwingUtilities.invokeLater(() -> {
            new MonthlyScheduleView(("Daily View")).setVisible(true);
        });
    }

    private void weeklyView() {
        SwingUtilities.invokeLater(() -> {
            new WeeklyScheduleView(("Weekly View")).setVisible(true);
        });
    }

    private void reportTypesByMonth() {
        try {
            List<Appointment> listApp = CustomerDAO.getAppointmentList();
            Map<Month, Integer> mapMonthNum = new HashMap<>();
            listApp.forEach(ap -> {
                System.out.println(ap.getStart());
                if (mapMonthNum.containsKey(ap.getStart().getMonth())) {
                    mapMonthNum.put(ap.getStart().getMonth(), mapMonthNum.get(ap.getStart().getMonth()) + 1);
                } else {
                    mapMonthNum.put(ap.getStart().getMonth(), 1);
                }
            });
            StringBuilder sb = new StringBuilder();
            mapMonthNum.entrySet().forEach(e -> {
                sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
            });
            JOptionPane.showMessageDialog(this, sb);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reportSchedule() {
        try {
            Connection con = TestConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select appointmentId, customerId, title, description, location, contact, url, start, end, createdBy from `U03q1A`.`appointment` order by createdBy" );
            JTable table = new JTable(buildTableModel(rs));
            table.putClientProperty("terminateEditOnFocusLost", true);
            
            Object[] options = {"Ok"};
            int input = JOptionPane.showOptionDialog(null, new JScrollPane(table), "Schedule By Consultant", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            if (input == JOptionPane.OK_OPTION) {
                
            }

        } catch (Exception e) {

            System.out.println("Initcomponents broke.");
            e.printStackTrace();
        }
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
    
    private void activeCustomerReport() {
        try {
            Connection con = TestConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select c.customerId, c.customerName\n"
                    + "from `U03q1A`.`customer` c\n"
                    + "where `active`=1");
            JTable table = new JTable(buildTableModel(rs));
            

            
            
            
            Object[] options = {"Ok"};
            int input = JOptionPane.showOptionDialog(null, new JScrollPane(table), "Active Customers", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public CustomerPanel getPanel() {
        return panel;
    }
}
