package irail.models;

import irail.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public static ArrayList<Train> search(String from,String to){
        ArrayList<Train> res = new ArrayList<Train>();
        from = escapeString(from);
        to = escapeString(to);
        try {
            String sql = "select t.train_no,t.name,t.start,t.dest,t.days,t.type from train_station t1 left join train_station t2 on t1.train_no=t2.train_no left join train t on t1.train_no = t.train_no where t1.station_code = "+from+" and t2.station_code="+to+" and (t1.day<t2.day or (t1.day=t2.day and t1.time<t2.time))";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                res.add(new Train(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
