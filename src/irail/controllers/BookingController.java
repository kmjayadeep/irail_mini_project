package irail.controllers;

import irail.View;
import irail.models.*;
import irail.views.BookingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jayadeep
 */
public class BookingController implements ActionListener{
    Birth birth;
    BookingView bookingView;
    String date;
    
    public BookingController(Birth b,String d){
        birth = b;
        date = d;
        bookingView = new BookingView();
        bookingView.bAdd.addActionListener(this);
        bookingView.bPay.addActionListener(this);
        bookingView.back.addActionListener(this);
        View.addToDesk(bookingView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        if(ac.equals("Add")){
            DefaultTableModel model = (DefaultTableModel) bookingView.table.getModel();
            String name = bookingView.tName.getText().toString();
            String sex = (String) bookingView.cbSex.getSelectedItem();
            int age = Integer.valueOf(bookingView.tAge.getText().toString());
            model.addRow(new String[]{name,sex,age+""});
            int rows = model.getRowCount();
            int payment = rows*birth.fare;
            bookingView.tPayment.setText("Total Payment : "+payment);
        }else if(ac.equals("Pay")){
            DefaultTableModel model = (DefaultTableModel) bookingView.table.getModel();
            Vector data = model.getDataVector();
            ArrayList<User> users = new ArrayList();
            int i=0;
            data.forEach(new Consumer() {
                @Override
                public void accept(Object t) {
                    System.out.println(t.getClass());
                    Vector user = (Vector) t;
                    char sex;
                    if(user.get(1).equals("Male"))
                        sex = 'M';
                    else
                        sex = 'F';
                    int age = Integer.parseInt((String) user.get(2));
                    User u = new User((String) user.get(0),sex,age);
                    users.add(u);
                }
            });
            User us[] = new User[data.size()];
            users.toArray(us);
            char type;
            if(bookingView.cbPayment.getSelectedIndex()==0)
                type='b';
            else
                type='c';
            int payId = Integer.parseInt(bookingView.tbPaymentId.getText().toString());
            Booking booking = new Booking(birth,date,type,payId,us);
            try {
                booking.save();
                JOptionPane.showMessageDialog(bookingView, "Success","Booking Successful",JOptionPane.INFORMATION_MESSAGE);
                bookingView.table.removeAll();
                bookingView.table.setModel(new DefaultTableModel(new String[][] {},new String[]{"Name","Sex","Age"}));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(bookingView, "Error","Booking Unsuccessful",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(ac.equals("back")){
            bookingView.dispose();
            TrainDetailsController.trainView.setVisible(true);
        }
    }
    
}
