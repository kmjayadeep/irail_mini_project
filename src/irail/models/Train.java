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
    String days;
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
        days = rs.getString("days");
        type = rs.getString("type");
    }
    
    public static ArrayList<Train> search(String from,String to,int day){
        ArrayList<Train> res = new ArrayList<Train>();
        from = escapeString(from);
        to = escapeString(to);
        Train t;
        try {
            String sql = "select t.train_no,t.name,t.start,t.dest,t.days,t.type from train_station t1 left join train_station t2 on t1.train_no=t2.train_no left join train t on t1.train_no = t.train_no where t1.station_code = "+from+" and t2.station_code="+to+" and (t1.day<t2.day or (t1.day=t2.day and t1.time<t2.time))";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                t = new Train(rs);
                if(t.isOnDay(day)){
                    res.add(t);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private boolean isOnDay(int day) {
        if(days.charAt(day)=='1')
            return true;
        else
            return false;
    }
    
    public String[][] getTimeTable(){
        ArrayList<String[]> stations = new ArrayList();
        String[] temp;
        ResultSet rs;
        try {
            rs = statement.executeQuery("select * from train_station where train_no = "+trainNo+" order by day,time");
            while(rs.next()){
                temp = new String[3];
                temp[0] = rs.getString(2);
                temp[1] = rs.getString(3);
                temp[2] = rs.getString(4);
                stations.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[][] res = new String[stations.size()][3];
        return (String[][]) stations.toArray(res);
    }
    
    public String[] toRow(){
        String res[] = {String.valueOf(trainNo),name,start.getName(),dest.getName(),type};
        return res;
    }
    
    public static String[] getColumns(){
        String s[] =  {"Train No","Name","From","To","Type "};
        return s;
    }
    
    public String getName(){
        return name;
    }
    
}
