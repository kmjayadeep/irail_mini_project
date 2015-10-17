package irail;

import irail.models.*;
import irail.views.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jayadeep
 */
public class MainController implements ActionListener,ListSelectionListener{
    
    MainView mainView;

    public static void main(String[] args) {
        Model.initialize();
        new MainController();
    }
    
    public MainController(){
        mainView = new MainView();
        mainView.search.addActionListener(this);
        mainView.table.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String from = mainView.src.getText().toString().toUpperCase();
        String to = mainView.des.getText().toString().toUpperCase();
        int day = mainView.daylist.getSelectedIndex();
        ArrayList<Train> tr = Train.search(from,to,day);
        int ttt = tr.size();
        String trains[][] = new String[ttt][5];
        int i=0;
        for (Iterator<Train> it = tr.iterator(); it.hasNext();) {
            Train tt = it.next();
            trains[i++] = tt.toRow();
        }
        
        mainView.table.setModel(new DefaultTableModel(trains, Train.getColumns()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println(e);
    }

    
}
