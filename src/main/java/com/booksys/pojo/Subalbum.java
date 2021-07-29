package com.booksys.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Accessors(chain = true)
@Table(name = "t_subalbum")
public class Subalbum {
    @Id
    @Column(name = "sid")
    private int sid;
    @Column(name = "aid")
    private int aid;
    @Column(name = "number")
    private String number;
    @Column(name = "condi")
    private int condi;
    @Column(name = "time")
    private String time;
    @Transient
    private Album album;

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCondi(int condi) {
        this.condi = condi;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getAid() {
        return aid;
    }

    public String getNumber() {
        return number;
    }

    public int getCondi() {
        return condi;
    }

    public String getTime() {
        return time;
    }

    public Album getAlbum() {
        return album;
    }

    public int getSid() {
        return sid;
    }

    public Subalbum() {
    }

    public Subalbum(int aid, String number, int condi, String time) {
        this.aid = aid;
        this.number = number;
        this.condi = condi;
        this.time = time;
    }
}
