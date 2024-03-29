/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import schedulingapplication.dao.TestConnection;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import schedulingapplication.model.Customer;
import schedulingapplication.presenter.CustomerPresenter;

/**
 *
 * @author jreisner
 */
public class EditCustomerPanel extends JFrame {

    private JTable jtable;
    
    
    /**
     * Creates new form CustomerForm
     */
    public EditCustomerPanel(String title) {
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
            Connection con = TestConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select c.customerId, c.customerName,  a.address, a.address2, a.phone, a.postalCode, ci.city, c.active\n"
                    + "from `U03q1A`.`customer` c\n"
                    + "join `U03q1A`.`address` a on c.addressId=a.addressId\n"
                    + "join `U03q1A`.`city` ci on a.cityId=ci.cityId;");
            JTable table = new JTable(buildTableModel(rs));
            

            table.getModel().addTableModelListener((TableModelEvent e) -> {
                
                try {
                    updateTable(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            table.putClientProperty("terminateEditOnFocusLost", true);
            this.jtable = table;
            Object[] options = {"Ok"};
            int input = JOptionPane.showOptionDialog(null, new JScrollPane(table), "Edit Customers", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            if (input == JOptionPane.OK_OPTION) {
                //TODO add logic that updates customers combobox
                //System.out.println("Clicked Ok");
            }

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

            if (col == 1) {
                sql = "Update `U03q1A`.`customer` set `customerName`=? where `customerId`=?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.executeUpdate();
                System.out.println("Updated customer name");

            }
            if (col == 2) {
                sql = "Update `U03q1A`.`address` set `address`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                System.out.println("Updated Address1");
            }
            if (col == 3) {
                sql = "Update `U03q1A`.`address` set `address2`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                System.out.println("Updated Address2");
            }
            if (col == 4) {
                sql = "Update `U03q1A`.`address` set `phone`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                System.out.println("Updated Phone");
            }
            if (col == 5) {
                sql = "Update `U03q1A`.`address` set `postalCode`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                System.out.println("Updated zip code.");
            }
            if (col == 6) {
                sql = "Update `U03q1A`.`city` set `city`=? where `cityId`=(select `cityID` from `U03q1A`.`address` where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?))";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, table.getValueAt(row, col).toString());
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.execute();
                System.out.println("Updated city.");
            }
            if (col == 7){
            sql = "Update `U03q1A`.`customer` set `active`=? where `customerId`=?";

                PreparedStatement ps = con.prepareStatement(sql);
                if (table.getValueAt(row,col).toString().equalsIgnoreCase("true")){
                    ps.setString(1, "1");}
                if (table.getValueAt(row,col).toString().equalsIgnoreCase("false")){
                    ps.setString(1, "0");}
                ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                ps.executeUpdate();
                System.out.println("Updated active status");
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
