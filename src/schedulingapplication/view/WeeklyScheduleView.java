/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import schedulingapplication.dao.CustomerDAO;
import schedulingapplication.model.Appointment;

/**
 *
 */
public class WeeklyScheduleView extends JFrame {

    private LocalDate curDate;
    private final int DAYS_TOTAL = 7;

    public WeeklyScheduleView(String title) {
        super(title);
        initComponents();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        curDate = LocalDate.now();
        JPanel jpNorth = new JPanel(new BorderLayout());
        JPanel jpMonth = new JPanel();
        JLabel jlMonth = new JLabel();
        jpMonth.add(jlMonth);
        JPanel jpDayOfWeek = new JPanel(new GridLayout(1, 7));
        jpNorth.add(jpMonth, BorderLayout.NORTH);
        jpNorth.add(jpDayOfWeek, BorderLayout.CENTER);
        JPanel jpCenter = new JPanel(new GridLayout(1, 7));
        JPanel jpMain = new JPanel(new BorderLayout());
        jpMain.setPreferredSize(new Dimension(800, 600));
        JPanel jpSouth = new JPanel();
        JButton jbPrev = new JButton("Previous");
        JButton jbNext = new JButton("Next");

        jpSouth.add(jbPrev);
        jpSouth.add(jbNext);

        for (DayOfWeek dow : DayOfWeek.values()) {
            JPanel jpDow = new JPanel();
            jpDow.add(new JLabel(dow.name()));
            jpDayOfWeek.add(jpDow);
        }

        JPanel[] jpDays = new JPanel[DAYS_TOTAL];
        for (int day = 1; day <= DAYS_TOTAL; day++) {
            JPanel jpDay = new JPanel();
            jpDay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jpDay.add(new JLabel());
            jpDays[day - 1] = jpDay;
            jpCenter.add(new JScrollPane(jpDay));
        }
        try {
            jbNext.addActionListener(e -> {
                try {
                    next(jpDays, jlMonth);
                } catch (Exception ex) {
                    Logger.getLogger(MonthlyScheduleView.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            jbPrev.addActionListener(e -> {
                try {
                    prev(jpDays, jlMonth);
                } catch (Exception ex) {
                    Logger.getLogger(MonthlyScheduleView.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            updateMonth(jpDays, jlMonth);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        jpMain.add(jpNorth, BorderLayout.NORTH);
        jpMain.add(jpCenter, BorderLayout.CENTER);
        jpMain.add(jpSouth, BorderLayout.SOUTH);
        add(jpMain);
    }

    private void updateMonth(JPanel[] jpDays, JLabel jlMonth) throws Exception {
        Month curMonth = curDate.getMonth();
        int numWeek = curDate.get(WeekFields.of(Locale.getDefault()).
                weekOfWeekBasedYear());
        jlMonth.setText("Week " + numWeek + ", "
                + curMonth.name() + ", " + curDate.getYear());
        LocalDate firstDay = curDate.with(TemporalAdjusters.previousOrSame
        (WeekFields.of(Locale.getDefault()).getFirstDayOfWeek())).plusDays(1);
  
        curDate = firstDay;
        DayOfWeek dowNow = firstDay.getDayOfWeek();
        int indexDow = dowNow.getValue(); // From 1 to 7.
        boolean first = false;
        List<Appointment> listApp = null;
        try {
            listApp = CustomerDAO.getAppointmentList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (int day = 1; day <= DAYS_TOTAL; day++) {
            JLabel jl = (JLabel) jpDays[day - 1].getComponent(0);
            jl.setText("");
            if (day == indexDow) {
                first = true;
            }
            if (first) {
                String text = "";
                  for (Appointment ap: getAppointment(firstDay, listApp)) {
                    text += ap.display() + "<br>";
                }
                jl.setText("<html>" + firstDay.getDayOfMonth() + "<br>" + text + "</html>");

                    firstDay = firstDay.plus(1, ChronoUnit.DAYS);

            }
        }
    }

     private List<Appointment> getAppointment(LocalDate date, List<Appointment> listApp) {
       List<Appointment> aps = new ArrayList<>();
        for (Appointment ap: listApp) {
            if ((ap.getStart().getYear() == date.getYear() && 
                    ap.getStart().getMonth() == date.getMonth() &&
                    ap.getStart().getDayOfMonth() == date.getDayOfMonth()) ||
                    (ap.getEnd().getYear() == date.getYear() && 
                    ap.getEnd().getMonth() == date.getMonth() &&
                    ap.getEnd().getDayOfMonth() == date.getDayOfMonth())) {
                aps.add(ap);
            }
        }
        return aps;
    }

    private void prev(JPanel[] jpDays, JLabel jlMonth) throws Exception {
        curDate = curDate.minusWeeks(1);
        updateMonth(jpDays, jlMonth);
    }

    private void next(JPanel[] jpDays, JLabel jlMonth) throws Exception {
        curDate = curDate.plusWeeks(1);
        updateMonth(jpDays, jlMonth);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WeeklyScheduleView(("Weekly View")).setVisible(true);
        });
    }
}
