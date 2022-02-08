/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jutil.test.database;

import dal.AccountDAO;
import model.Account;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ducky
 */
public class AccountDBContextTest {
    
    private AccountDAO adb = new AccountDAO();
    
    public AccountDBContextTest() {
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
    public void getAdminAccountTest(){
        Account account = adb.getAccount("admin", "admin123");
        assertNotNull("Null Account", account);
        Object[] excepted = {"admin", "admin123"};
        Object[] actuals = {account.getUsername(), account.getPassword()};
        assertArrayEquals("Get admin account failed", excepted, actuals);
        System.out.println(account.getUsername() + " " + account.getPassword());
    }
    
//    @Test
//    public void insertAccount(){
//        Account account = new Account("test1", "123");
//        Account account = new Account("test1", "123", "ACTIVE");
//        assertTrue("Insert failed", adb.insertAccount(account));
//        System.out.println(account.getUsername() + " " + account.getPassword());
//    }
}
