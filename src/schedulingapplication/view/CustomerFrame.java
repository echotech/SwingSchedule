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
import javax.swing.table.DefaultTableModel;
import schedulingapplication.dao.CustomerDAO;
import schedulingapplication.dao.TestConnection;
import schedulingapplication.model.Appointment;
import schedulingapplication.model.Schedule;
import static schedulingapplication.view.EditCustomerPanel.buildTableModel;

/**
 *
 */
public class CustomerFrame extends JFrame {

    private CustomerPanel panel;

    private final String[] COL_NAMES = {"CustomerName", "Active", "City",
        "Countty", "Title", "Description", "Start", "End"};

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
        JMenuItem jmiEditCustomer = new JMenuItem("Edit Customer");
        jmiEditCustomer.addActionListener(e->editCustomer());
        JMenuItem jmiNumTypesByMonths = new JMenuItem("Number Types By Months");
        jmiNumTypesByMonths.addActionListener(e -> reportTypesByMonth());
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
        jmView.add(jmiMonthlyView);
        jmView.add(jmiWeeklyView);
        jmEdit.add(jmiEditCustomer);
        jmFile.add(jmiExit);
        jmb.add(jmFile);
        jmb.add(jmReport);
        jmb.add(jmView);
        jmb.add(jmEdit);

        setJMenuBar(jmb);
    }
    
    //TODO move this to EditCustomerPanel using this https://stackoverflow.com/questions/27271234/how-can-i-open-a-jpanel-from-a-menu
    
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
    
    private void editCustomer(){
        
        try {
            Connection con = TestConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select c.customerId, c.customerName, a.address, a.address2, a.phone, a.postalCode, ci.city\n" +
            "from `U03q1A`.`customer` c\n" +
            "join `U03q1A`.`address` a on c.addressId=a.addressId\n" +
            "join `U03q1A`.`city` ci on a.cityId=ci.cityId;");
            JTable table = new JTable(buildTableModel(rs));
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
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
            List<Schedule> sch = CustomerDAO.getSchedule();

            DefaultTableModel dtm = new DefaultTableModel(COL_NAMES, 0);
            JTable jt = new JTable(dtm);
            sch.forEach(e -> {
                System.out.println("1");
                dtm.addRow(new Object[]{e.getCustomer().getName(),
                    e.getCustomer().isActive(), e.getCity(), e.getCountry(), e.getAppointment().getTitle(),
                    e.getAppointment().getDescription(), e.getAppointment().getStart(),
                    e.getAppointment().getEnd()});
            });
            JPanel jpReport = new JPanel(new BorderLayout());
            jpReport.add(new JScrollPane(jt), BorderLayout.CENTER);
            JFrame jf = new JFrame("Schedule");
            jf.add(jpReport);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public CustomerPanel getPanel() {
        return panel;
    }
}
