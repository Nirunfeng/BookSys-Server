package com.booksys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Accessors(chain = true)
@Table(name = "t_position")
public class Position {
    @Id
    @Column(name = "pid")
    private int pid;
    @Column(name = "userid")
    private int userid;
    @Column(name = "address")
    private String address;
    @Column(name = "time")
    private String time;

    public Position() {
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPid() {
        return pid;
    }

    public int getUserid() {
        return userid;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public Position(int pid, int userid, String address, String time) {
        this.pid = pid;
        this.userid = userid;
        this.address = address;
        this.time = time;
    }
}
