package irail.models;

import irail.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jayadeep
 */
public class Station extends Model{
    String name;
    String shortName; //primary
    
    public String toString(){
        return name+" "+shortName;
    }
    
    public Station(ResultSet res) throws SQLException{
        fromResultSet(res);
    }
    
    public Station(String sName){
        try {
            shortName = sName;
            ResultSet res = statement.executeQuery("select * from station where short_name='"+shortName+"'");
            if(res.next())
                fromResultSet(res);
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Override
    public void fromResultSet(ResultSet res) throws SQLException {
        name = res.getString("name");
        shortName = res.getString("short_name");
    }

    public String getName() {
        return name;
    }
}
