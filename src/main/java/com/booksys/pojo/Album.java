package com.booksys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;


@Accessors(chain = true)
@Table(name = "t_album")
public class Album {
    @Id
    @Column(name = "aid")
    private int aid;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publishtime")
    private String publishtime;
    @Column(name = "num")
    private int num;
    @Column(name = "descri")
    private String descri;
    @Column(name = "time")
    private String time;
    /*内联数据*/
    @Transient
    private List<Subalbum> subalbums;

    public Album() {
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSubalbums(List<Subalbum> subalbums) {
        this.subalbums = subalbums;
    }

    public int getAid() {
        return aid;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public int getNum() {
        return num;
    }

    public String getDescri() {
        return descri;
    }

    public String getTime() {
        return time;
    }

    public List<Subalbum> getSubalbums() {
        return subalbums;
    }

    public Album(String title, String author, String publisher, String publishtime, int num, String descri, String time) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishtime = publishtime;
        this.num = num;
        this.descri = descri;
        this.time = time;
    }
}
