/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irail.views;

import irail.GlobalConstants;
import irail.models.Train;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jayadeep
 */
public class MainView extends JInternalFrame{
    
    public JLabel day;
    public JComboBox daylist;
    public JTextField des;
    public JLabel from;
    public JScrollPane jScrollPane1;
    public JTable table;
    public JButton search;
    public JTextField src;
    public JLabel to;
    
    public MainView(){
        initComponents();
        this.setVisible(true);
        this.setSize(GlobalConstants.windowWidth,GlobalConstants.windowHeight);
//        this.setBorder(BorderFactory.createEmptyBorder());
    }
    public void initComponents() {

        from = new JLabel();
        to = new JLabel();
        src = new JTextField();
        des = new JTextField();
        day = new JLabel();
        daylist = new JComboBox();
        search = new JButton();
        jScrollPane1 = new JScrollPane();
        table = new JTable();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        from.setText("From:");
        to.setText("To:");
        day.setText("Day:");
        daylist.setModel(new DefaultComboBoxModel(new String[] { "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday" }));
        search.setText("Search");
        table.setModel(new DefaultTableModel(
            null,
            Train.getColumns()
        ));
        jScrollPane1.setViewportView(table);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addComponent(from)
                .addGap(0, 0, 0)
                .addComponent(src, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(to)
                .addGap(0, 0, 0)
                .addComponent(des, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(day)
                .addGap(1, 1, 1)
                .addComponent(daylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
            .addGroup(layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(search, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {des, src});

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(from)
                    .addComponent(src, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(to)
                    .addComponent(des, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(day)
                    .addComponent(daylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 453, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {des, src});
        pack();
    }
}
