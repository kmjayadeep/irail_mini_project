package irail;

import irail.controllers.TrainDetailsController;
import irail.models.*;
import irail.views.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jayadeep
 */
public class MainController implements ActionListener,ListSelectionListener{
    
    MainView mainView;
    View view;

    public static void main(String[] args) {
        Model.initialize();
        new View();
        new MainController();
    }

    public MainController(){
        mainView = new MainView();
        mainView.search.addActionListener(this);
        mainView.table.getTableHeader().setReorderingAllowed(false);
        mainView.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainView.table.getSelectionModel().addListSelectionListener(this);
        mainView.setVisible(true);
        View.addToDesk(mainView);
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
        if(e.getValueIsAdjusting())
            return;
        int iSelectedIndex = e.getFirstIndex();
        int trainNo = Integer.parseInt((String) mainView.table.getModel().getValueAt(iSelectedIndex, 0));
        mainView.setVisible(false);
        Train train = new Train(trainNo);
        Station src = new Station(mainView.src.getText().toString().toUpperCase());
        Station dest = new Station(mainView.des.getText().toString().toUpperCase());
        String date = "2015-10-17"; //change later
        new TrainDetailsController(train, src, dest, date);
    }

    
}
