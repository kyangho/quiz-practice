/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Function;

/**
 *
 * @author ducky
 */
public class FunctionDBContext extends DBContext{
    public ArrayList<Function> getFunctions(){
        ArrayList<Function> functions = new ArrayList<>();
        
        String sql_select = "SELECT * FROM function;";
        try{
            PreparedStatement stm = connection.prepareStatement(sql_select);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Function function = new Function();
                function.setId(rs.getInt("function_id"));
                function.setName(rs.getString("function_name"));
                function.setUrl(rs.getString("function_url"));
                functions.add(function);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FunctionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return functions;
        
    }
}
