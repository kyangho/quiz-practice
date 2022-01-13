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
public class FunctionDBContext extends DBContext {

    public ArrayList<Function> getFunctions() {
        ArrayList<Function> functions = new ArrayList<>();

        String sql_select = "SELECT `function`.`function_id`,"
                + "    `function`.`function_name`,"
                + "    `function`.`function_url`"
                + "FROM `quiz_practice_db`.`function`;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql_select);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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

    /**
     *
     * @param id
     * @return Function
     */
    public Function getFunctionByFunctionId(int id) {
        String sql_get = "SELECT `function`.`function_id`,"
                + "    `function`.`function_name`,"
                + "    `function`.`function_url`"
                + "FROM `quiz_practice_db`.`function`"
                + "WHERE function_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql_get);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Function f = new Function();
                f.setId(rs.getInt("function_id"));
                f.setName(rs.getString("function_name"));
                f.setUrl(rs.getString("function_url"));

                return f;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FunctionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Function> getFunctionsByRoleId(int id) {
        String sql_get = "SELECT \n"
                + "	rf.role_id,"
                + "	f.`function_id`,"
                + "   f.`function_name`,"
                + "   f.`function_url`"
                + "FROM `quiz_practice_db`.`function` as f\n"
                + "INNER JOIN `quiz_practice_db`.`role_function` as rf on rf.function_id = f.function_id\n"
                + "WHERE rf.role_id = ?";
        ArrayList<Function> functions = new ArrayList<>();

        try {
            PreparedStatement stm = connection.prepareStatement(sql_get);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Function f = new Function();
                f.setId(rs.getInt("function_id"));
                f.setName(rs.getString("function_name"));
                f.setUrl(rs.getString("function_url"));

                functions.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FunctionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return functions;
    }

    public boolean updateFunction(ArrayList<Function> functions) {
        String sql_update = "UPDATE `quiz_practice_db`.`function`"
                + "SET\n"
                + "`function_id` = ?,"
                + "`function_name` = ?,"
                + "`function_url` = ?"
                + "WHERE `function_id` = ?;";

        try {
            for (Function f : functions) {
                PreparedStatement stm = connection.prepareStatement(sql_update);
                stm.setInt(1, f.getId());
                stm.setString(2, f.getName());
                stm.setString(3, f.getUrl());

                stm.setInt(4, f.getId());
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FunctionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean insertFunction(ArrayList<Function> functions) {
        String sql_insert = "INSERT INTO `quiz_practice_db`.`function`"
                + "("
                + "`function_name`,"
                + "`function_url`"
                + ")"
                + "VALUES\n"
                + "(?,?);";
        try {
            for (Function f : functions) {
                PreparedStatement stm = connection.prepareStatement(sql_insert);
                stm.setString(1, f.getName());
                stm.setString(2, f.getUrl());

                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FunctionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void test() {
        try {
            //        DBContext db = new DBContext();
//        System.out.println(db);

            String sql = " select * from table1;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testInsert(int id, String name) {
        try {
            String sql = "INSERT INTO `testsql`.`table1`\n"
                    + "(`id`,\n"
                    + "`name`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, name);
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FunctionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        FunctionDBContext f = new FunctionDBContext();
    }
}
