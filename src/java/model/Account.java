/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Vu Duc Tien
 */
public class Account {

       private int id;
       private String username;
       private String password;
       private String email;
       private String phone;
       private String fullname;
       private String address;

       public Account() {
       }

       public Account(String username, String password, String email, String phone, String fullname, String address) {
              this.username = username;
              this.password = password;
              this.email = email;
              this.phone = phone;
              this.fullname = fullname;
              this.address = address;
       }

       public Account(int id, String username, String password, String email, String phone, String fullname, String address) {
              this.id = id;
              this.username = username;
              this.password = password;
              this.email = email;
              this.phone = phone;
              this.fullname = fullname;
              this.address = address;
       }

       public int getId() {
              return id;
       }

       public void setId(int id) {
              this.id = id;
       }

       public String getUsername() {
              return username;
       }

       public void setUsername(String username) {
              this.username = username;
       }

       public String getPassword() {
              return password;
       }

       public void setPassword(String password) {
              this.password = password;
       }

       public String getEmail() {
              return email;
       }

       public void setEmail(String email) {
              this.email = email;
       }

       public String getPhone() {
              return phone;
       }

       public void setPhone(String phone) {
              this.phone = phone;
       }

       public String getFullname() {
              return fullname;
       }

       public void setFullname(String fullname) {
              this.fullname = fullname;
       }

       public String getAddress() {
              return address;
       }

       public void setAddress(String address) {
              this.address = address;
       }

       @Override
       public String toString() {
              return "Account{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", fullname=" + fullname + ", address=" + address + '}';
       }

}
