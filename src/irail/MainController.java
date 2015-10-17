package irail;

import irail.models.*;
import irail.views.MainView;
import irail.views.Splash;

/**
 *
 * @author jayadeep
 */
public class MainController {

    public static void main(String[] args) {
        Train t = new Train(16604);
        System.out.println(t);
        
        MainView mv = new MainView();
    }
    
}
