/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jutil.test.database;

import dal.FunctionDBContext;
import java.util.ArrayList;
import model.Function;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ducky
 */
public class CRUDFunctionTest {
    
    public CRUDFunctionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void updateFunctionTest() {
        FunctionDBContext fdb = new FunctionDBContext();
        ArrayList<Function> functions = new ArrayList<>();
        functions.add(new Function(1, "c", "c"));
        
        assertTrue("update failed!", fdb.updateFunction(functions));
    }
    
    @Test
    public void insertFunctionTest(){
        FunctionDBContext fdb = new FunctionDBContext();
        ArrayList<Function> functions = new ArrayList<>();
        functions.add(new Function(1, "d", "d"));
        
        assertTrue("insert failed!", fdb.insertFunction(functions));
    }
    
    @Test
    public void getFunctionTest(){
        FunctionDBContext fdb = new FunctionDBContext();
        ArrayList<Function> functions = fdb.getFunctions();
        assertNotNull("not null", functions);
        for (Function f :  functions){
            System.out.println(f.getId() + " " + f.getName() + " " + f.getUrl());
        }
    }
}
