/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import schedulingapplication.dao.TestConnection;

/**
 *
 * @author jreisner
 */
public class EditCustomerPanel extends JFrame{
    
      /**
     * Creates new form CustomerForm
     */
    public EditCustomerPanel(String title) {
        super(title);
        try{
        initComponents();}
        catch (Exception e){
            System.out.println("You broke edit customer.");
        }
        setLocationRelativeTo(null);
    }

    public void initComponents() throws Exception{
        
               
        Connection con = TestConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select c.customerId, c.customerName, a.address, a.address2, a.phone, a.postalCode, ci.city\n" +
        "from customer c\n" +
        "join address a on c.addressId=a.addressId\n" +
        "join city ci on a.cityId=ci.cityId;");
        JTable table = new JTable(buildTableModel(rs));
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
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
    
    public void editCustomer(){
        
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
    
}

