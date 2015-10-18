/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Birth extends Model{

    Train train;
    Station src;
    Station des;
    public int distance;
    public int fare;
    public int seats;
    String birth;
    
    public Birth(Train t,Station s,Station d,String b){
        train = t;
        src = s;
        des = d;
        birth = b;
        String ss = escapeString(src.shortName);
        String dd = escapeString(des.shortName);
        String sql1 = "select distance from station_station where (station1 = "+ss+" and station2 = "+dd+" )";
        sql1+=" or (station2 = "+ss+" and station1 = "+dd+" )";
        String sql2 = "select rate from train_cost where type="+escapeString(train.type)+" and coach="+escapeString(birth);
        String sql3 = "select seats from train_seats where train_no="+train.trainNo+" and coach_type="+escapeString(birth);
        String sql = "select ("+sql1+") as distance, ("+sql2+") as rate, ("+sql3+") as seats";
        try {
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next())
                fromResultSet(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Birth.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void fromResultSet(ResultSet res) throws SQLException {
        distance = res.getInt("distance");
        int rate = res.getInt("rate");
        fare = distance*rate;
        seats = res.getInt("seats");
    }
    
    public String toString(){
        return distance+" "+fare+" "+seats;
    }
}
