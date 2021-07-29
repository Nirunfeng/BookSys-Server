package com.booksys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Accessors(chain = true)
@Table(name="t_book")
public class Book {
    @Id
    @Column(name = "bid")
    private  int bid;
    @Column(name = "userid")
    private int userid;
    @Column(name = "btid")
    private int btid;
    @Column(name = "bookname")
    private String bookname;
    @Column(name = "bookpic")
    private String bookpic;
    @Column(name = "description")
    private String description;
    @Column(name = "condi")
    private int condi;
    @Column(name = "time")
    private String time;

    public Book() {
    }

    public int getBid() {
        return bid;
    }

    public int getUserid() {
        return userid;
    }

    public int getBtid() {
        return btid;
    }

    public String getBookname() {
        return bookname;
    }

    public String getBookpic() {
        return bookpic;
    }

    public String getDescription() {
        return description;
    }

    public int getCondi() {
        return condi;
    }

    public String getTime() {
        return time;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setBtid(int btid) {
        this.btid = btid;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setBookpic(String bookpic) {
        this.bookpic = bookpic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCondi(int condi) {
        this.condi = condi;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Book(int bid, int userid, int btid, String bookname, String bookpic, String description, int condi, String time) {
        this.bid = bid;
        this.userid = userid;
        this.btid = btid;
        this.bookname = bookname;
        this.bookpic = bookpic;
        this.description = description;
        this.condi = condi;
        this.time = time;
    }
}
