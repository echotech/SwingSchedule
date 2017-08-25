/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import schedulingapplication.dao.TestConnection;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import schedulingapplication.dao.CustomerDAO;
import schedulingapplication.model.Customer;
import schedulingapplication.model.Schedule;
import schedulingapplication.presenter.CustomerPresenter;

/**
 *
 * @author jreisner
 */
public class EditAppointmentPanel extends JFrame {
    private final String[] COL_NAMES = {"Appointment Id", "Customer", "Title",
        "Description", "Location", "Url", "Start", "End", "Consultant"};
    private JTable jtable;
    
    
    /**
     * Creates new form CustomerForm
     */
    public EditAppointmentPanel(String title) {
        super(title);
        try {
            
            initComponents();

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            setLocationRelativeTo(null);

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("Something broke in constructor.");
        }

    }

    private void initComponents() throws Exception {
        try {
            List<Schedule> sch = CustomerDAO.getSchedule();

            DefaultTableModel dtm = new DefaultTableModel(COL_NAMES, 0);
            JTable jt = new JTable(dtm);
            sch.forEach(e -> {

                dtm.addRow(new Object[]{e.getAppointment().getId(), e.getCustomer().getName(),
                    e.getAppointment().getTitle(), e.getAppointment().getDescription(), e.getAppointment().getLocation(), e.getAppointment().getUrl(),
                    e.getAppointment().getStart(),e.getAppointment().getEnd(), e.getAppointment().getCreatedBy()});
                    
            });
            JPanel jpReport = new JPanel(new BorderLayout());
            jpReport.add(new JScrollPane(jt), BorderLayout.CENTER);
            JFrame jf = new JFrame("Edit Appointments");
            jf.add(jpReport);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
            

            jt.getModel().addTableModelListener((TableModelEvent e) -> {
                
                try {
                    int row = e.getFirstRow();
            int col = e.getColumn();
                    System.out.println("Row"+row+"Col"+col);
                    updateTable(e);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            jt.putClientProperty("terminateEditOnFocusLost", true);
            this.jtable = jt;
            Object[] options = {"Ok"};
            //int input = JOptionPane.showOptionDialog(null, new JScrollPane(jt), "Edit Appointments", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            //if (input == JOptionPane.OK_OPTION) {
                
            //}

        } catch (Exception e) {

            System.out.println("Initcomponents broke.");
            e.printStackTrace();
        }

    }

    public void updateTable(TableModelEvent e) throws Exception {

        try {
            Connection con = TestConnection.getConnection();
            JTable table = this.jtable;

            int row = e.getFirstRow();
            int col = e.getColumn();

            String sql = null;

            if (col == 2) {
                sql = "Update `U03q1A`.`appointment` set `title`=? where `appointmentId`=?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.executeUpdate();
                System.out.println("Updated Title");

            }
            if (col == 3) {
                sql = "Update `U03q1A`.`appointment` set `description`=? where `appointmentId`=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                
            }
            if (col == 4) {
                sql = "Update `U03q1A`.`appointment` set `location`=? where `appointmentId`=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                
            }
            
            if (col == 5) {
                sql = "Update `U03q1A`.`appointment` set `url`=? where `appointmentId`=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                System.out.println("Set url to: "+table.getValueAt(row,col).toString());
                
            }
            
            if (col == 6) {
                sql = "Update `U03q1A`.`appointment` set `start`=? where `appointmentId`=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ZonedDateTime start = ZonedDateTime.parse(table.getValueAt(row, col).toString());
                ZonedDateTime zonedStart = start.withZoneSameInstant(ZoneOffset.UTC);
                ps.setTimestamp(1, Timestamp.valueOf(zonedStart.toLocalDateTime()));
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                
            }
            if (col == 7) {
                sql = "Update `U03q1A`.`appointment` set `end`=? where `appointmentId`=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ZonedDateTime end = ZonedDateTime.parse(table.getValueAt(row, col).toString());
                ZonedDateTime zonedEnd = end.withZoneSameInstant(ZoneOffset.UTC);
                ps.setTimestamp(1, Timestamp.valueOf(zonedEnd.toLocalDateTime()));
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                
            }
            if (col == 8) {
                sql = "Update `U03q1A`.`appointment` set `createdBy`=? where `appointmentId`=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Updates aren't working");
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

   

}
