package com.Shoots.domain;

public class BusinessUser {
    private int business_idx;
    private String business_id;
    private String password;
    private String business_name;
    private long business_number;
    private String tel;
    private String email;
    private String post;
    private String address;
    private String description;
    private String business_file;
    private String register_date;
    private String login_status;

    public int getBusiness_idx() {
        return business_idx;
    }

    public void setBusiness_idx(int business_idx) {
        this.business_idx = business_idx;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public long getBusiness_number() {
        return business_number;
    }

    public void setBusiness_number(long business_number) {
        this.business_number = business_number;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusiness_file() {
        return business_file;
    }

    public void setBusiness_file(String business_file) {
        this.business_file = business_file;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }
}
