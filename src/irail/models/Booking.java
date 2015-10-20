package irail.models;

import irail.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Booking extends Model{

    int pnr;
    Birth birth;
    String date;
    char payment_type;
    int payment_id;
    User users[];
    
    public Booking(Birth b,String dat,char pt,int pid,User[] u){
        birth = b;
        date = dat;
        payment_type = pt;
        payment_id = pid;
        users = u;
    }
    
    public void save() throws SQLException{
        String sql = "insert into booking values (null,"+birth.train.trainNo+","+escapeString(birth.src.shortName)+",";
        sql+=escapeString(birth.des.shortName)+","+escapeString(birth.birth)+","+escapeString(date);
        sql+=",'"+payment_type+"',"+payment_id+","+birth.fare*users.length+")";
        statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
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
        Train train = new Train(res.getInt(2));
        Station src = new Station(res.getString(3));
        Station des = new Station(res.getString(4));
        String coach = res.getString(5);
        birth = new Birth(train,src,des,coach);
        date = res.getString(6);
        payment_type = res.getString(7).charAt(0);
        payment_id = res.getInt(8);
    }
    
}
