package com.booksys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Accessors(chain = true)
@Table(name = "t_reader")
public class Reader {
    @Id
    @Column(name = "rid")
    private int rid;
    @Column(name="account")
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "time")
    private String time;
    @Column(name = "condi")
    private int condi;

    public int getRid() {
        return rid;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getTime() {
        return time;
    }

    public int getCondi() {
        return condi;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCondi(int condi) {
        this.condi = condi;
    }

    public Reader() {
    }

    public Reader(String account, String password, String name, String sex, String time, int condi) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.time = time;
        this.condi = condi;
    }
}
