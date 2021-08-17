package com.booksys.controller;

import com.booksys.mapper.AlbumMapper;
import com.booksys.mapper.BorrowrecordMapper;
import com.booksys.mapper.SubalbumMapper;
import com.booksys.pojo.Album;
import com.booksys.pojo.Borrowrecord;
import com.booksys.pojo.Subalbum;
import com.booksys.util.DateTimeUtil;
import com.booksys.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图书管理Controller
 */
@RestController
@RequestMapping("/book")
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private SubalbumMapper subalbumMapper;

    @Autowired
    private BorrowrecordMapper borrowrecordMapper;

    @Autowired
    private  RedisUtil redisUtil;

    /**
     * 获取书目集合
     * @param title
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getAllAlbums",method = RequestMethod.POST)
    public Map<String,Object> getAllAlbums(@RequestParam("title")String title, @RequestParam("currentPage")int currentPage){
        List<Album> list=new ArrayList<>();
        /*声明返回值对象*/
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        /*查询缓存*/
        if(redisUtil.getValue("list")==null){
            /*查询数据库*/
            list=albumMapper.selectByTitle(title);
            /*存入缓存*/
            redisUtil.setValue("list",list);

            /*将list存入map返回*/
            PageInfo<Album> pageInfo=new PageInfo<>(list);
            map.put("pageInfo",pageInfo);
            map.put("albums",list);
            return map;
        }else{
            /*将缓存放入list*/
            List<Album> redisUtilValue=(List<Album>) redisUtil.getValue("list");

            /*查询某本书籍*/
            if(title!=""){
                //TODO
                for(int i=0;i<redisUtilValue.size();i++){
                    if(redisUtilValue.get(i).getTitle().equals(title)){
                        list.add(redisUtilValue.get(i));
                    }
                }

                /*将list存入map返回*/
                PageInfo<Album> pageInfo=new PageInfo<>(list);
                map.put("pageInfo",pageInfo);
            }else{

                list=redisUtilValue;
                /*将list存入map返回*/
                PageInfo<Album> pageInfo=new PageInfo<>(list);
                map.put("pageInfo",pageInfo);
            }
            map.put("albums",list);
            return map;
        }
    }

    /**
     * 添加书籍
     * @param title
     * @param author
     * @param publisher
     * @param publishtime
     * @param descri
     * @return
     */
    @RequestMapping(value = "/addAlbum",method = RequestMethod.POST)
    public Map<String,Object> addAlbum(@RequestParam("title")String title,@RequestParam("author")String author,@RequestParam("publisher")String publisher,@RequestParam("publishtime")String publishtime,@RequestParam("descri")String descri){
        Map<String,Object> map=new HashMap<>();
        albumMapper.insert(new Album(title,author,publisher,publishtime,0,descri, DateTimeUtil.getDate()));
        map.put("status","ok");
        return map;
    }

    /**
     * 添加图书编号
     * @param aid
     * @param number
     * @return
     */
    @RequestMapping(value = "/addSubAlbum",method = RequestMethod.POST)
    public Map<String,Object> addSubAlbim(@RequestParam("aid")int aid,@RequestParam("number")String number){
        Map<String,Object> map=new HashMap<>();
        if(subalbumMapper.selectByNumber(number)!=null){
            map.put("status","no");
        }
        else{
            Album album=albumMapper.selectById(aid);
            album.setNum(album.getNum()+1);
            albumMapper.updateByPrimaryKey(album);
            subalbumMapper.insert(new Subalbum(aid,number,0,DateTimeUtil.getDate()));
            map.put("status","ok");
        }
        return map;
    }

    /**
     * 借阅图书
     * @param aid
     * @param rid
     * @param raccount
     * @return
     */
    @RequestMapping(value = "/borrow",method = RequestMethod.POST)
    public Map<String,Object> borrow(@RequestParam("aid")int aid,@RequestParam("rid")int rid,@RequestParam("raccount")String raccount){
        Map<String,Object> map=new HashMap<>();
        if(rid!=0){
            Album album=albumMapper.selectById(aid);
            int count=0;
            //找到可借的那本书编号id
            int enableborrowSAid=0;
            for(int i=0,len=album.getSubalbums().size();i<len;i++){
                if(album.getSubalbums().get(i).getCondi()==0){
                    enableborrowSAid=album.getSubalbums().get(i).getSid();
                    count++;
                }
            }
            if(count!=0){
                map.put("status","ok");
                Borrowrecord borrowrecord=new Borrowrecord(rid,raccount,aid,enableborrowSAid,DateTimeUtil.getDate(),DateTimeUtil.getDateAfter15(),DateTimeUtil.getDateNumber());
                borrowrecordMapper.insert(borrowrecord);
                Subalbum subalbum=subalbumMapper.selectById(enableborrowSAid);
                subalbum.setCondi(1);
                subalbumMapper.updateByPrimaryKey(subalbum);
            }else{
                map.put("status","no");
            }
        }
        return map;
    }

}
