package irail.models;

import irail.Model;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jayadeep
 */
public class Booking extends Model{

    int pnr;
    Train train;
    Station src;
    Station des;
    String date;
    char payment_type;
    int payment_id;
    int amount;
    User users[];
    
    public Booking(Train t,Station s,Station d,String dat,char pt,char pid,int am,User[] u){
        train = t;
        src = s;
        des = d;
        date = dat;
        payment_type = pt;
        payment_id = pid;
        amount = am;
        users = u;
    }
    
    public void save() throws SQLException{
        String sql = "insert into booking values (null,"+train.trainNo+","+escapeString(src.shortName)+",";
        sql+=escapeString(des.shortName)+","+escapeString(date)+","+payment_id+","+amount+")";
        statement.executeUpdate(sql);
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next())
            pnr = rs.getInt(1);
        for(User user : users){
            user.save(pnr);
        }
        sql = "select * from booking where pnr="+pnr;
        rs = statement.executeQuery(sql);
        if(rs.next())
            fromResultSet(rs);
        
    }
    
    @Override
    public void fromResultSet(ResultSet res) throws SQLException {
        pnr = res.getInt(1);
        train = new Train(res.getInt(2));
        src = new Station(res.getString(3));
        des = new Station(res.getString(4));
        date = res.getString(5);
        payment_type = (char) res.getInt(6);
        payment_id = res.getInt(7);
        amount = res.getInt(8);
    }
    
}
