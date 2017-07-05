package com.example.win.a2vent;

/**
 * Created by win on 2017-07-03.
 */

public class owner_com_item {

    String com_number=null;
    String com_name=null;
    String com_addr=null;
    String com_category=null;
    String com_manager=null;
    String com_URI=null;
    String id=null;


    public owner_com_item(String com_number, String com_name, String com_addr, String com_category, String com_manager, String com_URI, String id){
        this.com_number=com_number;
        this.com_name=com_name;
        this.com_addr=com_addr;
        this.com_category=com_category;
        this.com_manager=com_manager;
        this.com_URI=com_URI;
        this.id=id;
    }

    public String getCom_number() {
        return com_number;
    }

    public String getCom_name() {
        return com_name;
    }

    public String getCom_addr() {
        return com_addr;
    }

    public String getCom_category() {
        return com_category;
    }

    public String getCom_manager() {
        return com_manager;
    }

    public String getCom_URI() {
        return com_URI;
    }

    public String getId() {
        return id;
    }

    public void setCom_number(String com_number) {
        this.com_number = com_number;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public void setCom_addr(String com_addr) {
        this.com_addr = com_addr;
    }

    public void setCom_category(String com_category) {
        this.com_category = com_category;
    }

    public void setCom_manager(String com_manager) {
        this.com_manager = com_manager;
    }

    public void setCom_URI(String com_URI) {
        this.com_URI = com_URI;
    }

    public void setId(String id) {
        this.id = id;
    }
}
