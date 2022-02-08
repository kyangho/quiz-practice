/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jutil.test.database;

import dal.SettingDAO;
import java.util.ArrayList;
import model.Setting;
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
public class SettingDBContextTest {
    SettingDAO sdb = new SettingDAO();
    
    public SettingDBContextTest() {
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
    public void getSettingTest(){
//        ArrayList<Setting> settings = sdb.getSettings();
//        assertNotEquals("Get setting database failed", 0, settings.size());
//        for(Setting s : settings){
//            System.out.println(s.toString());
//        }
    }
    
    @Test
    @Ignore
    public void insertSettingTest(){
        Setting setting = new Setting(0, "b", "b", "b", "b", "DEACTIVE");
        assertTrue("Insert failed!", sdb.insertSetting(setting));
        System.out.println(setting.toString());
    }
    
    @Test
    @Ignore
    public void updateSettingTest(){
        Setting setting = new Setting(1, "c", "b", "b", "b", "ACTIVE");
        assertTrue("Update failed!", sdb.updateSetting(setting));
//        System.out.println(setting.toString());
    }
    
    @Test
    @Ignore
    public void deactiveSettingTest(){
        int settingId = 1;
        assertTrue("Deactive failed", sdb.deactiveSetting(settingId));
//        for(Setting s : sdb.getSettings()){
//            System.out.println(s.toString());
//        }
    }
    
    @Test
    public void activeSettingTest(){
        int settingId = 1;
        assertTrue("Active failed", sdb.activeSetting(settingId));
//        for(Setting s : sdb.getSettings()){
//            System.out.println(s.toString());
//        }
    }
}
