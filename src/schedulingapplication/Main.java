//TODO Add functionality to edit customers
//TODO Change functionality of adding customers
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication;

import javax.swing.SwingUtilities;
import schedulingapplication.model.User;
import schedulingapplication.presenter.UserPresenter;
import schedulingapplication.view.UserFrame;

/**
 *
 */
public class Main {

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
}
