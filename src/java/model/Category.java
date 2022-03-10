/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Yankee
 */
public class Category {

    private int category_id;
    private String category_name;
    private String category_value;

    public Category() {
    }

    public Category(int category_id, String category_name, String category_value) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_value = category_value;
    }
    public Category(int category_id, String category_value) {
        this.category_id = category_id;
        this.category_value = category_value;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_value() {
        return category_value;
    }

    public void setCategory_value(String category_value) {
        this.category_value = category_value;
    }

}
