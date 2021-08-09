package com.booksys;

import com.booksys.pojo.Booktype;
import com.booksys.util.RedisUtil;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookSysServerApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void RedisTest(){
        List<Booktype> list=new ArrayList<>();
        list.add(new Booktype(1,"2","3"));
        list.add(new Booktype(1,"2","3"));
        list.add(new Booktype(1,"2","3"));

        /*将list存入集合*/
        redisUtil.setValue("list",list);

        System.out.println(redisUtil.getValue("list"));
    }

}
