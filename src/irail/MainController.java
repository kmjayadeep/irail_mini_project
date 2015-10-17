package irail;

import irail.models.*;
import irail.views.MainView;
import irail.views.Splash;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jayadeep
 */
public class MainController {

    public static void main(String[] args) {
        
        Model.initialize();
        ArrayList<Train> tr = Train.search("KZE", "TVC");
        
        for (Iterator<Train> it = tr.iterator(); it.hasNext();) {
            Train tt = it.next();
            System.out.println(tt);
        }
        
        MainView mv = new MainView();
    }
    
}
