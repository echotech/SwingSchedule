/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.SwingUtilities;
import schedulingapplication.model.Customer;
import schedulingapplication.model.User;
import schedulingapplication.view.CustomerFrame;
import schedulingapplication.view.UserFrame;

/**
 *
 */
public class UserPresenter implements ActionListener {

    private UserFrame view;

    private User model;

    public UserPresenter(UserFrame view, User model) {
        this.view = view;
        this.model = model;
    }

    public void initView() {
        SwingUtilities.invokeLater(() -> new UserFrame("Login"));
        view.getPanel().getJbLogin().setActionCommand("login");
        view.getPanel().getJbCancel().setActionCommand("cancel");
        view.getPanel().getJcbLanguage().setActionCommand("language");
    }

    public void initPresenter() {
        view.getPanel().getJbLogin().addActionListener(this);
        view.getPanel().getJbCancel().addActionListener(this);
        view.getPanel().getJcbLanguage().addActionListener(this);       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "language":
                selectLanguage();
                break;
            case "login":
                login();
                break;
            case "cancel":
                System.exit(0);
                break;
        }
    }

    private void selectLanguage() {
        String language = schedulingapplication.Main.getLocale();
        view.getPanel().setLanguage();
    }

    private void login() {
        model.setUsername(view.getPanel().getJtfLogin().getText());
        model.setPassword(new String(view.getPanel().getJpf().getPassword()));
        try {
            String language = (String) view.getPanel().getJcbLanguage().getSelectedItem();
            if (model.checkUser()) {
                writeToFile();
                view.getPanel().applyTimer(true, language);
                Customer custModel = new Customer();
                CustomerFrame custView = new CustomerFrame("Maintain Customer");
                CustomerPresenter custPres = new CustomerPresenter(custModel, custView);
                custPres.initView();
                custPres.initPresenter();
                view.setVisible(false);
                custView.setVisible(true);
            } else {
                view.getPanel().applyTimer(false, language);
                view.getPanel().clearFields();
            }
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }
    
    private void writeToFile() throws IOException {
        try (FileWriter fw = new FileWriter (new File ("user_acrivity.txt"), true)) {
            fw.write (model.toString() + "\r\nLogin: " + Timestamp.valueOf(LocalDateTime.now()) + "\r\n\r\n");
        }
    }
}
