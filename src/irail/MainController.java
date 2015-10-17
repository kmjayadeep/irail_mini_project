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
        ArrayList<Train> tr = Train.search("KZE", "TVC",1);
        int ttt = tr.size();
        String trains[][] = new String[ttt][5];
        
        int i=0;
        for (Iterator<Train> it = tr.iterator(); it.hasNext();) {
            Train tt = it.next();
            System.out.println(tt);
            trains[i++] = tt.toRow();
        }
        MainView mv = new MainView();
    }
    
}
