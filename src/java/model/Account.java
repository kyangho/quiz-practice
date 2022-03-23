/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
import java.util.ArrayList;

/**
 *
 * @author Vu Duc Tien
 */
public class Account {

    private int id;
    private String username;
    private String password;
    private String status;
    private String email;
    private String phone;
    private String fullname;
    private boolean gender;
    private Blob avatar;
    private ArrayList<Role> role = new ArrayList<>();

    public Account() {
    }

    public Account(int id, String username, String password, 
            String email, String phone, String fullname, boolean gender,
            String status, ArrayList<Role> role, Blob avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
        this.gender = gender;
        this.status = status;
        this.role = role;
        this.avatar = avatar;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Role> getRole() {
        return role;
    }

    public void setRole(ArrayList<Role> role) {
        this.role = role;
    }

    public boolean checkRoleEqual(Role ro) {
        for (Role r : this.role) {
            if (r.getId() == ro.getId() || r.getRoleName().equalsIgnoreCase(ro.getRoleName())) {
                return true;
            }
        }
        return false;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }
}
