/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import schedulingapplication.model.LanguageModel;

/**
 *
 */
public class UserPanel extends javax.swing.JPanel {
    
    
    /**
     * Creates new form LoginPanel
     */
    public UserPanel() {
        
        initComponents();
        setLanguage();
        jcbLanguage.setVisible(false);
        jlSelectLanguage.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlLogin = new javax.swing.JLabel();
        jlPassword = new javax.swing.JLabel();
        jtfLogin = new javax.swing.JTextField("demo");
        jbLogin = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();
        jpf = new javax.swing.JPasswordField("demo");
        jlSelectLanguage = new javax.swing.JLabel();
        jcbLanguage = new javax.swing.JComboBox<>();
        jlSuccessFailed = new javax.swing.JLabel();

        jlLogin.setText("Login:");

        jlPassword.setText("Password:");

        jtfLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfLoginActionPerformed(evt);
            }
        });

        jbLogin.setText("Login");
        jbLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLoginActionPerformed(evt);
            }
        });

        jbCancel.setText("Cancel");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });

        jlSelectLanguage.setText("Select language:");

        jcbLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Spanish", "French", " " }));
        jcbLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbLanguageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlSelectLanguage)
                    .addComponent(jlPassword)
                    .addComponent(jlLogin))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlSuccessFailed, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbCancel))
                    .addComponent(jtfLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(jpf)
                    .addComponent(jcbLanguage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSelectLanguage))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlLogin))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPassword))
                .addGap(11, 11, 11)
                .addComponent(jlSuccessFailed, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbLogin)
                    .addComponent(jbCancel))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtfLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfLoginActionPerformed

    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed

    }//GEN-LAST:event_jbCancelActionPerformed

    private void jbLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLoginActionPerformed

    }//GEN-LAST:event_jbLoginActionPerformed

    private void jcbLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbLanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbLanguageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbLogin;
    private javax.swing.JComboBox<String> jcbLanguage;
    private javax.swing.JLabel jlLogin;
    private javax.swing.JLabel jlPassword;
    private javax.swing.JLabel jlSelectLanguage;
    private javax.swing.JLabel jlSuccessFailed;
    private javax.swing.JPasswordField jpf;
    private javax.swing.JTextField jtfLogin;
    // End of variables declaration//GEN-END:variables

    public JButton getJbCancel() {
        return jbCancel;
    }

    public JButton getJbLogin() {
        return jbLogin;
    }

    public javax.swing.JComboBox<String> getJcbLanguage() {
        return jcbLanguage;
    }

    public javax.swing.JLabel getJlLogin() {
        return jlLogin;
    }

    public javax.swing.JLabel getJlPassword() {
        return jlPassword;
    }

    public javax.swing.JLabel getJlSelectLanguage() {
        return jlSelectLanguage;
    }

    public void displayError(Exception exc) {
        JOptionPane.showMessageDialog(this, exc, "Exception occurred",
                JOptionPane.ERROR_MESSAGE);
    }

    public void setLanguage() {
        if (schedulingapplication.Main.getLocale().equals("English")) {
            jlSelectLanguage.setText(LanguageModel.selectLanguageEnglish);
            jlLogin.setText(LanguageModel.loginEnglish);
            jlPassword.setText(LanguageModel.passwordEnglish);
            jbLogin.setText(LanguageModel.loginBtnEnglish);
            jbCancel.setText(LanguageModel.cancelEnglish);
        } else if (schedulingapplication.Main.getLocale().equals("Spanish")) {
            jlSelectLanguage.setText(LanguageModel.selectLanguageSpanish);
            jlLogin.setText(LanguageModel.loginBtnSpanish);
            jlPassword.setText(LanguageModel.passwordSpanish);
            jbLogin.setText(LanguageModel.loginBtnSpanish);
            jbCancel.setText(LanguageModel.cancelSpanish);
        } else { // If French.
            jlSelectLanguage.setText(LanguageModel.selectLanguageFrench);
            jlLogin.setText(LanguageModel.loginBtnFrench);
            jlPassword.setText(LanguageModel.passwordFrench);
            jbLogin.setText(LanguageModel.loginBtnFrench);
            jbCancel.setText(LanguageModel.cancelFrench);
        }
        
    }

    public void applyTimer(boolean success, String language) throws InterruptedException {
        
        if (success) {
            jlSuccessFailed.setForeground(Color.GREEN);
            if (schedulingapplication.Main.getLocale().equals("English")) {
                jlSuccessFailed.setText(LanguageModel.successEnglish);
            } else if (schedulingapplication.Main.getLocale().equals("Spanish")) {
                jlSuccessFailed.setText(LanguageModel.successSpanish);
            } else {
                jlSuccessFailed.setText(LanguageModel.successFrench);
            }

        } else {
            jlSuccessFailed.setForeground(Color.RED);
             if (language.equals("English")) {
                jlSuccessFailed.setText(LanguageModel.wrongLoginEnglish);
            } else if (language.equals("Spanish")) {
                jlSuccessFailed.setText(LanguageModel.wrongLoginSpanish);
            } else {
                jlSuccessFailed.setText(LanguageModel.wrongLoginFrench);
            }
        }
    }
    
    public void clearFields() {
        jtfLogin.setText("");
        jpf.setText("");
    }

    public javax.swing.JLabel getJlSuccessFailed() {
        return jlSuccessFailed;
    }

    public javax.swing.JPasswordField getJpf() {
        return jpf;
    }

    public javax.swing.JTextField getJtfLogin() {
        return jtfLogin;
    }
}
