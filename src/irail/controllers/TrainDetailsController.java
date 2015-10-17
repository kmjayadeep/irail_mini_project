/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irail.controllers;

import irail.models.Station;
import irail.models.Train;
import irail.views.TrainDetails;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author krishnaraj
 */
public class TrainDetailsController {
    
   TrainDetails trainView;
   
   public TrainDetailsController(Train T, Station src,Station des,String day ){
       trainView = new TrainDetails();
       trainView.srcname.setText(src.getName());
       trainView.desname.setText(des.getName());
       trainView.tname.setText(T.getName());
       String timeTable[][] = T.getTimeTable();
       trainView.table.setModel(new DefaultTableModel(timeTable,new String[] {"Station","Day","Time"}));
      /* trainView.bg.getSelection().addItemListener(new ItemListener() {

           @Override
           public void itemStateChanged(ItemEvent e) {
             //  e.getItem()
           }
       });*/
       trainView.setVisible(true);
   }
}
