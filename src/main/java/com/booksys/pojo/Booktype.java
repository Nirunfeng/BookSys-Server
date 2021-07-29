package com.booksys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Accessors(chain = true)
@Table(name = "t_booktype")
public class Booktype {
    @Id
    @Column(name = "btid")
    private int btid;
    @Column(name = "name")
    private String name;
    @Column(name = "time")
    private String time;

    public Booktype(int btid, String name, String time) {
        this.btid = btid;
        this.name = name;
        this.time = time;
    }

    public Booktype() {
    }

    public int getBtid() {
        return btid;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setBtid(int btid) {
        this.btid = btid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
