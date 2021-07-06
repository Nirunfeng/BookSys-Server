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
@Table(name = "t_message")
public class Message {
    @Id
    @Column(name = "mid")
    private int mid;
    @Column(name = "fromuserid")
    private int fromuserid;
    @Column(name = "touserid")
    private int touserid;
    @Column(name = "content")
    private String content;
    @Column(name = "condi")
    private int condi;
    @Column(name = "time")
    private String time;

    public Message(int mid, int fromuserid, int touserid, String content, int condi, String time) {
        this.mid = mid;
        this.fromuserid = fromuserid;
        this.touserid = touserid;
        this.content = content;
        this.condi = condi;
        this.time = time;
    }
}
