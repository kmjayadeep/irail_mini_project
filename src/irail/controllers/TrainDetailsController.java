package irail.controllers;

import irail.View;
import irail.models.Birth;
import irail.models.Station;
import irail.models.Train;
import irail.views.TrainDetails;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author krishnaraj
 */
public class TrainDetailsController implements ActionListener{
    
   TrainDetails trainView;
   Train train;
   Station src,des;
   String day;
   
   public TrainDetailsController(Train T, Station src,Station des,String day ){
       train = T;
       this.src = src;
       this.des = des;
       this.day = day;
       trainView = new TrainDetails();
       trainView.srcname.setText(src.getName());
       trainView.desname.setText(des.getName());
       trainView.tname.setText(T.getNo()+" - "+T.getName());
       String timeTable[][] = T.getTimeTable();
       trainView.table.setModel(new DefaultTableModel(timeTable,new String[] {"Station","Day","Time"}));
       trainView.jRadioButton1.addActionListener(this);
       trainView.jRadioButton2.addActionListener(this);
       trainView.jRadioButton3.addActionListener(this);
       trainView.jRadioButton4.addActionListener(this);
       trainView.infoPanel.setVisible(false);
       trainView.setVisible(true);
       View.addToDesk(trainView);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        String b = e.getActionCommand();
        Birth birth = new Birth(train,src,des,b);
        trainView.lDist.setText(birth.distance+" KM");
        if(birth.seats!=0)
            trainView.lFare.setText("Rs."+birth.fare);
        else
            trainView.lFare.setText("-");
        trainView.lSeats.setText(String.valueOf(birth.seats));
        trainView.infoPanel.setVisible(true);
    }
}
