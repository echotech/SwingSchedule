/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import javax.swing.JFrame;

/**
 *
 */
public class UserFrame extends JFrame {
    
    private UserPanel panel;
    
    public UserFrame (String title) {
        super (title);
        
        add (panel = new UserPanel());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    
   

    public UserPanel getPanel() {
        return panel;
    }
    
    
}
