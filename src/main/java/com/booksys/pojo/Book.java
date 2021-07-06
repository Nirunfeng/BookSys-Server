package com.booksys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
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
