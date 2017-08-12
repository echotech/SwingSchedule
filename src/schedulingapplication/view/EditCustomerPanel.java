/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import schedulingapplication.dao.TestConnection;

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
            pack();
            setLocationRelativeTo(null);

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    private void initComponents() throws Exception {
        try {
            Connection con = TestConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select c.customerId, c.customerName, a.address, a.address2, a.phone, a.postalCode, ci.city\n"
                    + "from `U03q1A`.`customer` c\n"
                    + "join `U03q1A`.`address` a on c.addressId=a.addressId\n"
                    + "join `U03q1A`.`city` ci on a.cityId=ci.cityId;");
            JTable table = new JTable(buildTableModel(rs));
            
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            this.jtable = table;
            
            table.getModel().addTableModelListener(table);
            table.putClientProperty("terminateEditOnFocusLost", true);
        
        int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            String sql = null;    
            
        try {
            
                if (col == 1) {
                    sql = "Update `U03q1A`.`customer` set `customerName`=? where `customerId`=?" ;
                    
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, table.getValueAt(row,col).toString());
                    ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                    ps.executeUpdate();

                }
                if (col == 2) {
                    sql = "Update `U03q1A`.`address` set `address`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, table.getValueAt(row,col).toString());
                    ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                    ps.execute();
                }
                if (col == 3) {
                    sql = "Update `U03q1A`.`address` set `address2`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, table.getValueAt(row,col).toString());
                    ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                    ps.execute();
                }
                if (col == 4) {
                    sql = "Update `U03q1A`.`address` set `phone`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, table.getValueAt(row,col).toString());
                    ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                    ps.execute();
                }
                if (col == 5) {
                    sql = "Update `U03q1A`.`address` set `postalCode`=? where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, table.getValueAt(row,col).toString());
                    ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                    ps.execute();
                }
                if (col == 6) {
                    sql = "Update `U03q1A`.`city` set `city`=? where `cityId`=(select `cityID` from `U03q1A`.`address` where `addressId`=(select `addressId` from `U03q1A`.`customer` where `customerId`=?))";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, table.getValueAt(row,col).toString());
                    ps.setInt(2, Integer.parseInt(table.getValueAt(row, 0).toString()));
                    ps.execute();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
           
                
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            new EditCustomerPanel(("Edit Customers")).setVisible(true);
        });

    }

}
