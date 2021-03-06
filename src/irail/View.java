package irail;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class View extends JFrame{
    
    public static JDesktopPane desk;
    
    public View(){
        super("iRail online railway enquiry system");
        desk = new JDesktopPane();
        this.setSize(GlobalConstants.windowWidth, GlobalConstants.windowHeight);
        this.add(desk);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void addToDesk(JInternalFrame frame){
        frame.setBounds(0,0,GlobalConstants.windowWidth,GlobalConstants.windowHeight);
        frame.setBorder(BorderFactory.createEmptyBorder());
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI)frame.getUI()).getNorthPane();
        frame.remove(titlePane);
        frame.setVisible(true);
        desk.add(frame);
    }
}
