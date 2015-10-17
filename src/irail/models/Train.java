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
public class Train extends Model{
    int trainNo;
    String name;
    Station start;
    Station dest;
    int days;
    String type;
    
    public String toString(){
        return trainNo+" "+name+" "+start+" "+dest+" "+days+" "+type;
    }
    
    public Train(){
    }
    
    public Train(int tno){
        trainNo = tno;
        ResultSet rs;
        try {
            rs = statement.executeQuery("select * from train where train_no = "+trainNo);
            if(rs.next())
                fromResultSet(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Train(ResultSet rs) throws SQLException{
        fromResultSet(rs);
    }
    
    @Override
    public void fromResultSet(ResultSet rs) throws SQLException {
        trainNo = rs.getInt("train_no");
        name = rs.getString("name");
        start = new Station(rs.getString("start"));
        dest = new Station(rs.getString("dest"));
        days = rs.getInt("days");
        type = rs.getString("type");
    }
}
