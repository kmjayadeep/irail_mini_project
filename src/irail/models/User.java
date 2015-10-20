package irail.models;

import irail.Model;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model{

    int pnr;
    String name;
    char sex;
    int age;
    
    public User(String n,char s,int a){
        name=n;
        sex=s;
        age=a;
    }
    
    public void save(int pnr) throws SQLException{
        String sql = "insert into user values("+pnr+","+escapeString(name)+",'"+sex+"',"+age+")";
        statement.executeUpdate(sql);
    }
    
    @Override
    public void fromResultSet(ResultSet res) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
