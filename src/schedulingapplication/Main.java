//TODO Add functionality to edit customers

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication;


import java.util.Locale;
import javax.swing.SwingUtilities;
import schedulingapplication.model.User;
import schedulingapplication.presenter.UserPresenter;
import schedulingapplication.view.UserFrame;

/**
 *
 */
public class Main {

public static Locale locale= Locale.getDefault();    
//public static Locale locale= new Locale("en","US");
//public static Locale locale= new Locale("es","ES");
//public static Locale locale = new Locale("fr","FR");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserFrame view = new UserFrame("Login");
            User model = new User();
            UserPresenter presenter = new UserPresenter(view, model);
            presenter.initView();
            presenter.initPresenter();
            view.setVisible(true);
            
            
            
        });
    }
    
    public static String getLocale(){
        return locale.getDisplayLanguage().toString();
    }
}
