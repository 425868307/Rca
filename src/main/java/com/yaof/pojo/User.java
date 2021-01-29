package com.yaof.pojo;

public class User {

    private int id;        //id
    private String account;        //账号
    private String password;    //密码
    private String power;    //权限0-超级管理员；1-系统管理员；9-普通用户
    private String name;    //用户名字
    private String age;        //年龄
    private String sex;        //性别
    private String birthdate;    //生日
    private String addr;    //地址
    private String phone;    //电话
    private String email;    //邮箱
    private String createAccount;    //创建账号
    private String updateAccount;    //修改账号

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAddr() {
        return addr;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public String getUpdateAccount() {
        return updateAccount;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
